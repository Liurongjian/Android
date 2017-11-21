package com.codemao.android.downmanager;

/**
 * Created by rong on 2017/7/12.
 */

public interface IDownloadManager {

	void submit(DownloadTask task);
	void remove(DownloadTask task);
	void pause(DownloadTask task);
	void resume(DownloadTask task);
}
