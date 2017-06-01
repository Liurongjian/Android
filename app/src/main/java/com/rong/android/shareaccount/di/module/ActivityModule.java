package com.rong.android.shareaccount.di.module;

import com.rong.android.shareaccount.di.scope.PreActivity;
import com.rong.android.shareaccount.pojo.User;

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
