package com.rong.common.di.module;

import android.app.Application;

import com.arms.rong.router.IRouter;
import com.arms.rong.router.RouterImpl;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by rong on 2017/5/22.
 */

@Module
public class AppModule {
	private Application mApplication;

	public AppModule(Application application) {
		this.mApplication = application;
	}

	@Singleton
	@Provides
	public Application provideApplication() {
		return mApplication;
	}

	@Singleton
	@Provides
	public IRouter provideRouter(RouterImpl router) {
		return router;
	}

}
