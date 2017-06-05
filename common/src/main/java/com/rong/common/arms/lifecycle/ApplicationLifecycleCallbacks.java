package com.rong.common.arms.lifecycle;

import android.app.Application;

import com.rong.common.di.component.AppComponent;

/**
 * Created by rong on 2017/6/6.
 */

public interface ApplicationLifecycleCallbacks {

	void onCreate(Application application);
	void onTerminate();
	void initAppComponent(AppComponent appComponent);
}
