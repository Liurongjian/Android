package com.rong.common.arms.lifecycle;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Created by rong on 2017/6/1.
 */
@Singleton
public class ActivityLifecycle implements Application.ActivityLifecycleCallbacks {

	private AppManager mAppManager;
	private Application mApplication;

	@Inject
	public ActivityLifecycle(AppManager appManager, Application application) {
		this.mAppManager = appManager;
		this.mApplication = application;
	}
	@Override
	public void onActivityCreated(Activity activity, Bundle savedInstanceState) {
		boolean isNotAdd = false;
		if (activity.getIntent() != null)
			isNotAdd = activity.getIntent().getBooleanExtra(AppManager.IS_NOT_ADD_ACTIVITY_LIST, false);

		if (!isNotAdd)
			mAppManager.addActivity(activity);
	}

	@Override
	public void onActivityStarted(Activity activity) {

	}

	@Override
	public void onActivityResumed(Activity activity) {
		mAppManager.setCurrentActivity(activity);
	}

	@Override
	public void onActivityPaused(Activity activity) {
		if (mAppManager.getCurrentActivity() == activity) {
			mAppManager.setCurrentActivity(null);
		}
	}

	@Override
	public void onActivityStopped(Activity activity) {

	}

	@Override
	public void onActivitySaveInstanceState(Activity activity, Bundle outState) {

	}

	@Override
	public void onActivityDestroyed(Activity activity) {
		mAppManager.removeActivity(activity);
	}
}
