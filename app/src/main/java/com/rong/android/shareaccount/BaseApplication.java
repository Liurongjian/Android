package com.rong.android.shareaccount;

import android.app.Application;

import com.rong.android.shareaccount.di.component.AppComponent;
import com.rong.android.shareaccount.di.component.DaggerAppComponent;
import com.rong.android.shareaccount.di.module.AppModule;
import com.rong.common.utils.DevUtil;


/**
 * Created by rong on 2017/5/22.
 */

public class BaseApplication extends Application {

	private AppComponent appComponent;
	@Override
	public void onCreate() {
		super.onCreate();
		initAppComponent(this);
		DevUtil.init(this, BuildConfig.DEBUG);

		registerLifecycle();
	}

	//注册页面生命周期
	private void registerLifecycle() {
		this.registerActivityLifecycleCallbacks(appComponent.activityLifecycle());
	}

	private void initAppComponent(Application application) {
		appComponent = DaggerAppComponent.builder().appModule(new AppModule(application)).build();
	}

	public AppComponent getAppComponent() {
		return appComponent;
	}

	@Override
	public void onTerminate() {
		super.onTerminate();
		appComponent.appManager().release();
		this.unregisterActivityLifecycleCallbacks(appComponent.activityLifecycle());
	}
}
