package com.codemao.android.downmanager;

import java.io.File;

/**
 * Created by rong on 2017/7/12.
 */

public interface DownloadListener {

	void onStart(DownloadTask task);
	void onProgressChanged(DownloadTask task, int progress);
	void onPause(DownloadTask task);
	void onResume(DownloadTask task);
	void onCancel(DownloadTask task);
	void onFinish(DownloadTask task, File file);
	/**
	 * 校验下载文件的完整性
	 * 方法在io线程中执行
	 */
	boolean onCheckFile(DownloadTask task, File file);
	void onFail(DownloadTask task, int errorCode);
}
