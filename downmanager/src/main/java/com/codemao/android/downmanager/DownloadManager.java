package com.codemao.android.downmanager;

import android.app.Application;
import android.content.Context;
import android.os.Message;
import android.text.TextUtils;
import android.util.ArrayMap;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.InputStream;
import java.io.RandomAccessFile;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import okhttp3.Cache;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

/**
 * Created by rong on 2017/7/12.
 */

public class DownloadManager implements IDownloadManager {

	private ArrayMap<DownloadTask, Disposable> tasks = new ArrayMap<>();
	private final File DEFAULT_DOWNLOAD_SAVE_DIR;
	private final File DEFAULT_DOWNLOAD_CACHE_DIR;
	private OkHttpClient mClient;
	private static DownloadManager instance;
	private TaskDbUtils dbUtils;


	private DownloadManager(Application context) {
		DEFAULT_DOWNLOAD_SAVE_DIR = context.getDir("download_default", Context.MODE_PRIVATE);
		DEFAULT_DOWNLOAD_CACHE_DIR = context.getDir("download_cache", Context.MODE_PRIVATE);
		dbUtils = new TaskDbUtils(context);
	}

	public static DownloadManager getInstance(Application application){

		if(instance == null) {
			synchronized (DownloadManager.class) {
				if(instance == null && application != null) {
					instance = new DownloadManager(application);
					instance.initCacheShortOkHttpClient();
				}
			}
		}

		return instance;
	}

	/**
	 * 获得保存文件名, 名字中包含了url信息
	 * @param url
	 * @return
	 */
	public String getKeyName(String url) {
		return IOUtils.getFileNameFromUrl(url);
	}

	@Override
	public void submit(final DownloadTask task) {
		Disposable subscribe = null;

		subscribe = tasks.get(task);
		if(subscribe != null && !subscribe.isDisposed()) {
			return;
		}

		subscribe = Observable.create(new ObservableOnSubscribe<DownloadMSG>() {
			@Override
			public void subscribe(@NonNull ObservableEmitter<DownloadMSG> e) throws Exception {
				if(TextUtils.isEmpty(task.getSaveFileDir())) {
					task.setSaveFileDir(DEFAULT_DOWNLOAD_SAVE_DIR.getAbsolutePath());
				}

				//如果出现未开始下载便被取消时，直接关闭返回
				if (task.getStatus() == DownloadTask.STATUS_CANCEL ||
						task.getStatus() == DownloadTask.STATUS_PAUSE) {
					e.onNext(new DownloadMSG(task.getStatus(), null));
					e.onComplete();
					return;
				}
				RandomAccessFile randomAccessFile = null;
				InputStream inputStream = null;
				InputStream bufferedInputStream = null;
				try {
					DownloadTask dbTask = dbUtils.findTaskByUrl(task.getUrl());
					if(dbTask != null && dbTask.getStatus() == DownloadTask.STATUS_FINISH) {
						File cacheFile = new File(dbTask.getSaveFileDir(), getKeyName(task.getUrl()));
						//发现缓存文件，直接复用
						if(cacheFile.exists() && cacheFile.length() > 0) {
							e.onNext(new DownloadMSG(DownloadTask.STATUS_FINISH, cacheFile));
							e.onComplete();
							return ;
						} else {
							//发现无效文件，删除记录
							dbUtils.deleteTask(dbTask);
						}
					}
					File saveFile = new File(task.getSaveFileDir(), getKeyName(task.getUrl()));
					randomAccessFile = new RandomAccessFile(saveFile, "rwd");
					if (saveFile.exists()) {
						task.setDownloadedSize(randomAccessFile.length());
					}

					//建立网络连接
					Request request = new Request.Builder()
							.url(task.getUrl())
							.header("RANGE", "bytes=" + task.getDownloadedSize() + "-")
							.build();

					randomAccessFile.seek(task.getDownloadedSize());
					Response response = mClient.newCall(request).execute();
					if (response.isSuccessful()) {
						//连接成功
						ResponseBody responseBody = response.body();
						e.onNext(new DownloadMSG(DownloadTask.STATUS_CONNECTING, null));
						if (responseBody != null) {
							if (task.getStatus() == DownloadTask.STATUS_CANCEL ||
									task.getStatus() == DownloadTask.STATUS_PAUSE) {
								// 任何步骤都可能插入暂停操作.
								IOUtils.close(randomAccessFile);
								e.onNext(new DownloadMSG(task.getStatus(), null));
								e.onComplete();
								return;
							} else {
								task.setStatus(DownloadTask.STATUS_CONNECTING);
								e.onNext(new DownloadMSG(DownloadTask.STATUS_RESUME, null));
							}

							long contentLength = responseBody.contentLength();
							if (contentLength == -1) {
								// 下载链接存在问题.
								task.setStatus(DownloadTask.STATUS_ERROR_LINK);
								e.onNext(new DownloadMSG(task.getStatus(), null));
								return;
							} else {
								task.setSize(contentLength + task.getDownloadedSize());
							}

							long updateSize = task.getSize() / 100;
							byte[] buffer = new byte[1024];
							int length;
							int buffOffset = 0;
							inputStream = responseBody.byteStream();
							bufferedInputStream = new BufferedInputStream(inputStream);

							//读数据
							while ((length = bufferedInputStream.read(buffer)) > 0
									&& task.getStatus() != DownloadTask.STATUS_CANCEL
									&& task.getStatus() != DownloadTask.STATUS_PAUSE) {

								randomAccessFile.write(buffer, 0, length);
								buffOffset += length;

								task.setDownloadedSize(task.getDownloadedSize() + length);
								if (buffOffset >= updateSize) {
									buffOffset = 0;
									//通知更新
									e.onNext(new DownloadMSG(DownloadTask.STATUS_PROGRESS_CHANGE,
											(int)(task.getDownloadedSize() * 100 / task.getSize())));
								}

								if (task.getDownloadedSize() >= task.getSize()) {
									//文件完整性校验
									if (task.getDownloadListener() != null) {
										if (task.getDownloadListener().onCheckFile(task, saveFile)) {
											//通知完成
											task.setStatus(DownloadTask.STATUS_FINISH);
											e.onNext(new DownloadMSG(task.getStatus(), saveFile));
											// 保存数据
											dbUtils.saveOrUpdate(task);
										} else {
											task.setStatus(DownloadTask.STATUS_ERROR_CHECK);
											saveFile.delete();
											e.onNext(new DownloadMSG(task.getStatus(), null));
										}
									} else {
										task.setStatus(DownloadTask.STATUS_FINISH);
										e.onNext(new DownloadMSG(task.getStatus(), saveFile));
										//保存数据
										dbUtils.saveOrUpdate(task);
									}
								}
							}
						} else if (response.code() == 403 || response.code() == 404) {
							task.setStatus(DownloadTask.STATUS_ERROR_LINK);
							e.onNext(new DownloadMSG(task.getStatus(), null));
						} else {
							task.setStatus(DownloadTask.STATUS_ERROR_NET);
							e.onNext(new DownloadMSG(task.getStatus(), null));
						}
					}
				} catch (Exception ex) {
					ex.printStackTrace();
				} finally {
					IOUtils.close(randomAccessFile, inputStream, bufferedInputStream);
					e.onComplete();
					remove(task);
				}
			}
		})
				.subscribeOn(Schedulers.io())
				.observeOn(AndroidSchedulers.mainThread())
				.subscribe(new Consumer<DownloadMSG>() {
					@Override
					public void accept(@NonNull DownloadMSG downloadMSG) throws Exception {
						if(task.getDownloadListener() == null) {
							return ;
						}

						DownloadListener listener = task.getDownloadListener();
						switch (downloadMSG.status) {
							//连接成功 onStart
							case DownloadTask.STATUS_CONNECTING:
								listener.onStart(task);
								break;
							//连接暂停 onPause
							case DownloadTask.STATUS_PAUSE:
								listener.onPause(task);
								break;
							//连接取消 onCancel
							case DownloadTask.STATUS_CANCEL:
								listener.onCancel(task);
								break;
							//进度更新
							case DownloadTask.STATUS_PROGRESS_CHANGE:
								listener.onProgressChanged(task, (Integer)downloadMSG.object);
								break;
							//成功
							case DownloadTask.STATUS_FINISH:
								listener.onFinish(task, (File)downloadMSG.object);
								break;
							//错误
							case DownloadTask.STATUS_ERROR_CHECK:
							case DownloadTask.STATUS_ERROR_LINK:
							case DownloadTask.STATUS_ERROR_NET:
								listener.onFail(task, task.getStatus());
								break;
							//
							case DownloadTask.STATUS_RESUME:
								listener.onResume(task);
								break;
						}
					}
				});

		tasks.put(task, subscribe);
	}

	private static class DownloadMSG {
		public int status;
		public Object object;
		public DownloadMSG(int status, Object object) {
			this.status = status;
			this.object = object;
		}
	}

	/**
	 * 获取短期缓存OkHttpClient.
	 *
	 * @return
	 */
	private static final long SHORT_CACHE_SIZE = 5 * 1024 * 1024;
	private void initCacheShortOkHttpClient() {
		if (mClient == null) {
			File httpCacheDirectory = new File(DEFAULT_DOWNLOAD_CACHE_DIR + "short");
			if (!httpCacheDirectory.exists()) {
				httpCacheDirectory.mkdirs();
			}
			Cache cache = new Cache(httpCacheDirectory, SHORT_CACHE_SIZE);
			EasyCacheInterceptor interceptor = new EasyCacheInterceptor();

			OkHttpClient.Builder builder = new OkHttpClient().newBuilder()
					.addNetworkInterceptor(interceptor)
					.cache(cache);
			mClient = builder.build();
		}

	}

	@Override
	public void remove(DownloadTask task) {
		Disposable subscribe = tasks.remove(task);
		if(subscribe != null) {
			subscribe.dispose();
			tasks.remove(task);
		}
	}

	@Override
	public void pause(DownloadTask task) {
		task.setStatus(DownloadTask.STATUS_PAUSE);
		remove(task);
	}

	@Override
	public void resume(DownloadTask task) {
		submit(task);
	}
}
