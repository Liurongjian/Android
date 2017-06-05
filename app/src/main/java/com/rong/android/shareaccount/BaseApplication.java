package com.rong.android.shareaccount;

import android.app.Application;

import com.rong.common.di.component.AppComponent;
import com.rong.common.di.component.DaggerAppComponent;
import com.rong.common.di.module.AppModule;
import com.rong.common.utils.DevUtil;


/**
 * Created by rong on 2017/5/22.
 */

public class BaseApplication extends Application {

	private AppComponent appComponent;
	private ApplicationDelegate applicationDelegate;
	public BaseApplication() {
		super();
		applicationDelegate = new ApplicationDelegate();
	}

	@Override
	public void onCreate() {
		super.onCreate();
		initAppComponent(this);
		DevUtil.init(this, BuildConfig.DEBUG);

		registerLifecycle();
		initRouterModule();
		applicationDelegate.onCreate(this);
	}

	//注册页面生命周期
	private void registerLifecycle() {
		this.registerActivityLifecycleCallbacks(appComponent.activityLifecycle());
	}

	private void initAppComponent(Application application) {
		appComponent = DaggerAppComponent.builder().appModule(new AppModule(application)).build();
		applicationDelegate.initAppComponent(appComponent);
	}

	public AppComponent getAppComponent() {
		return appComponent;
	}

	//注册模块页面路由
	private void initRouterModule() {
		appComponent.router().setDebug(DevUtil.isDebug());
		appComponent.router().inject(RouterModule.class);
	}

	@Override
	public void onTerminate() {
		super.onTerminate();
		appComponent.appManager().release();
		this.unregisterActivityLifecycleCallbacks(appComponent.activityLifecycle());
		applicationDelegate.onTerminate();
	}
}
