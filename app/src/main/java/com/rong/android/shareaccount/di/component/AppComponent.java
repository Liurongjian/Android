package com.rong.android.shareaccount.di.component;

import android.app.Application;

import com.rong.android.shareaccount.BaseApplication;
import com.rong.android.shareaccount.di.module.ApiModule;
import com.rong.android.shareaccount.di.module.AppModule;
import com.rong.android.shareaccount.http.UserService;
import com.rong.common.arms.lifecycle.ActivityLifecycle;
import com.rong.common.arms.lifecycle.AppManager;
import com.rong.common.di.module.HttpModule;
import com.rong.common.di.module.UtilModule;
import com.rong.common.utils.ProgressUtil;

import org.simple.eventbus.EventBus;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by rong on 2017/5/22.
 */

@Singleton
@Component(modules = {AppModule.class, HttpModule.class, ApiModule.class, UtilModule.class})
public interface AppComponent {
	Application Application();
	UserService userService();
	AppManager appManager();
	ActivityLifecycle activityLifecycle();
	EventBus eventBus();
	ProgressUtil progressUtil();

	void inject(BaseApplication app);
}
