package com.codemao.android.downmanager;

import android.app.Application;

import java.util.List;

/**
 * Created by rong on 2017/7/13.
 */

public class TaskDbUtils {
	private Application context;
	private DaoSession daoSession;
	
	public TaskDbUtils(Application context) {
		this.context = context;
	}
	private DaoSession getDaoMasterSession() {
		if(daoSession == null) {
			synchronized (this) {
				if(daoSession == null) {
					DaoMaster.DevOpenHelper devOpenHelper = new DaoMaster.DevOpenHelper(context, "down_manager.db", null);
					DaoMaster daoMaster = new DaoMaster(devOpenHelper.getWritableDb());
					daoSession = daoMaster.newSession();
				}
			}
		}
		return daoSession;
	}

	private DownloadTaskDao getTaskDao() {
		return getDaoMasterSession().getDownloadTaskDao();
	}

	void saveOrUpdate(DownloadTask task) {
		try {
			DownloadTask oldData = findTaskByUrl(task.getUrl());
			if (oldData != null) {
				task.set_id(oldData.get_id());
			}

			getTaskDao().insertOrReplace(task);
		} catch (Exception ex) {
			ex.printStackTrace();
		}

	}

	DownloadTask findTaskByUrl(String url) {
		try {
			List<DownloadTask> tasks = getTaskDao().queryBuilder().where(DownloadTaskDao.Properties.Url.eq(url)).list();
			return tasks == null || tasks.isEmpty() ? null : tasks.get(0);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return null;
	}

	void deleteTask(DownloadTask task) {
		if(task == null || task.get_id() <= 0) {
			return ;
		}

		getTaskDao().delete(task);
	}
}
