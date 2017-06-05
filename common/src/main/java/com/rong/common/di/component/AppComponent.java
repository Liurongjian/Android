package com.rong.common.di.component;

import android.app.Application;

import com.arms.rong.router.IRouter;
import com.rong.common.arms.lifecycle.ActivityLifecycle;
import com.rong.common.arms.lifecycle.AppManager;
import com.rong.common.di.module.AppModule;
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
@Component(modules = {AppModule.class, HttpModule.class, UtilModule.class})
public interface AppComponent {
	Application Application();
	AppManager appManager();
	ActivityLifecycle activityLifecycle();
	EventBus eventBus();
	ProgressUtil progressUtil();
	//路由
	IRouter router();

}
