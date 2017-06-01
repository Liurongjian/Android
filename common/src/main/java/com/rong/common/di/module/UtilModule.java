package com.rong.common.di.module;

import org.simple.eventbus.EventBus;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by rong on 2017/5/23.
 */
@Module
public class UtilModule {

	@Singleton
	@Provides
	public EventBus provideEventbus() {
		return EventBus.getDefault();
	}


}
