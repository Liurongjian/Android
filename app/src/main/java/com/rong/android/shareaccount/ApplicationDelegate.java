package com.rong.android.shareaccount;

import android.app.Application;

import com.rong.common.arms.lifecycle.ApplicationLifecycleCallbacks;
import com.rong.common.di.component.AppComponent;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by rong on 2017/6/6.
 */

public class ApplicationDelegate implements ApplicationLifecycleCallbacks {
	private List<ApplicationLifecycleCallbacks> appLifeCallbacks;

	public ApplicationDelegate() {
		appLifeCallbacks = new ArrayList<>();
		appLifeCallbacks.add(com.arms.rong.remodule.ApplicationDelegate.getInstance());
		appLifeCallbacks.add(com.arms.rong.routermoduleb.ApplicationDelegate.getInstance());
	}
	@Override
	public void onCreate(Application application) {

		if(!appLifeCallbacks.isEmpty()) {
			for(ApplicationLifecycleCallbacks lifecycleCallback : appLifeCallbacks) {
				lifecycleCallback.onCreate(application);
			}
		}
	}

	@Override
	public void onTerminate() {
		if(!appLifeCallbacks.isEmpty()) {
			for(ApplicationLifecycleCallbacks lifecycleCallback : appLifeCallbacks) {
				lifecycleCallback.onTerminate();
			}
		}

		appLifeCallbacks.clear();
		appLifeCallbacks = null;
	}

	@Override
	public void initAppComponent(AppComponent appComponent) {
		if(!appLifeCallbacks.isEmpty()) {
			for(ApplicationLifecycleCallbacks lifecycleCallback : appLifeCallbacks) {
				lifecycleCallback.initAppComponent(appComponent);
			}
		}
	}
}
