package com.arms.rong.routermoduleb.di.module;

import com.arms.rong.routermoduleb.di.scope.PreActivity;
import com.arms.rong.routermoduleb.pojo.User;

import dagger.Module;
import dagger.Provides;

/**
 * Created by rong on 2017/5/23.
 */

@Module
public class ActivityModule {

	@PreActivity
	@Provides
	User provideActivityUser() {
		return new User("activity");
	}


}
