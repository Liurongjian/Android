package com.rong.android.shareaccount;

import android.annotation.TargetApi;
import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.multidex.MultiDex;

import com.rong.common.di.component.AppComponent;
import com.rong.common.di.component.DaggerAppComponent;
import com.rong.common.di.module.AppModule;
import com.rong.common.utils.DevUtil;
import com.tencent.bugly.beta.Beta;
import com.tencent.tinker.loader.app.DefaultApplicationLike;


/**
 * Created by rong on 2017/5/22.
 */

public class BaseApplication extends DefaultApplicationLike {

	private AppComponent appComponent;
	private ApplicationDelegate applicationDelegate;
	private static BaseApplication instance;

	public BaseApplication(Application application, int tinkerFlags, boolean tinkerLoadVerifyFlag, long applicationStartElapsedTime, long applicationStartMillisTime, Intent tinkerResultIntent) {
		super(application, tinkerFlags, tinkerLoadVerifyFlag, applicationStartElapsedTime, applicationStartMillisTime, tinkerResultIntent);
		applicationDelegate = new ApplicationDelegate();
		instance = this;
	}


	@Override
	public void onCreate() {
		super.onCreate();
		initAppComponent(getApplication());
		DevUtil.init(getApplication(), BuildConfig.DEBUG);

		registerLifecycle();
		initRouterModule();
		initCrashReport();
		applicationDelegate.onCreate(getApplication());
	}

	//注册页面生命周期
	private void registerLifecycle() {
		getApplication().registerActivityLifecycleCallbacks(appComponent.activityLifecycle());
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

	private void initCrashReport() {
		new Thread(new Runnable() {
			@Override
			public void run() {
				appComponent.crashUtil().initCrashReport(getApplication(), DevUtil.isDebug(), "0001", null);
			}
		}).start();
	}

	@Override
	public void onTerminate() {
		super.onTerminate();
		appComponent.appManager().release();
		getApplication().unregisterActivityLifecycleCallbacks(appComponent.activityLifecycle());
		applicationDelegate.onTerminate();
	}

	public static BaseApplication getInstance() {
		return instance;
	}

	@TargetApi(Build.VERSION_CODES.ICE_CREAM_SANDWICH)
	@Override
	public void onBaseContextAttached(Context base) {
		super.onBaseContextAttached(base);
		// you must install multiDex whatever tinker is installed!
		MultiDex.install(base);
        // 安装tinker TinkerManager.installTinker(this);
		//替换成下面Bugly提供的方法
		Beta.installTinker(this);
	}
}
