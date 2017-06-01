package com.rong.android.shareaccount.di.module;

import android.app.Application;

import org.simple.eventbus.EventBus;

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
	public EventBus provideEventbus() {
		return EventBus.getDefault();
	}
}
