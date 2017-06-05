package com.arms.rong.routermoduleb;

import android.app.Application;

import com.rong.common.arms.lifecycle.ApplicationLifecycleCallbacks;
import com.rong.common.di.component.AppComponent;

/**
 * Created by rong on 2017/6/6.
 */

public class ApplicationDelegate implements ApplicationLifecycleCallbacks {

	private static ApplicationDelegate INSTANCE;
	private AppComponent appComponent;

	private ApplicationDelegate() {

	}

	public static ApplicationDelegate getInstance() {
		if(INSTANCE == null) {
			INSTANCE = new ApplicationDelegate();
		}

		return INSTANCE;
	}

	@Override
	public void onCreate(Application application) {

	}

	@Override
	public void onTerminate() {

	}

	@Override
	public void initAppComponent(AppComponent appComponent) {
		this.appComponent = appComponent;
		this.appComponent.router().inject(RouterModule.class);
	}

	public AppComponent getAppComponent() {
		return appComponent;
	}

}
