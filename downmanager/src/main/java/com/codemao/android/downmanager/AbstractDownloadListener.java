package com.codemao.android.downmanager;

import java.io.File;

/**
 * Created by rong on 2017/7/13.
 */

public abstract class AbstractDownloadListener implements DownloadListener {
	@Override
	public void onStart(DownloadTask task) {

	}

	@Override
	public void onPause(DownloadTask task) {

	}

	@Override
	public void onResume(DownloadTask task) {

	}

	@Override
	public void onCancel(DownloadTask task) {

	}


	@Override
	public boolean onCheckFile(DownloadTask task, File file) {
		return true;
	}

}
