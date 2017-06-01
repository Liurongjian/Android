package com.rong.android.shareaccount.di.module;

import com.rong.android.shareaccount.http.UserService;
import com.rong.common.http.IRepositoryManager;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.HttpUrl;
import retrofit2.Retrofit;

/**
 * Created by rong on 2017/5/22.
 */

@Module
public class ApiModule {

	private final String baseUrl = "http://api.juheapi.com";
	@Singleton
	@Provides
	HttpUrl provideBaseHttpUrl() {
		return HttpUrl.parse(baseUrl);
	}

	@Singleton
	@Provides
	@Named("api_retrofit")
	Retrofit provideRetrofit(Retrofit.Builder builder, HttpUrl httpUrl) {
		builder.baseUrl(httpUrl);
		return builder.build();
	}

	@Singleton
	@Provides
	UserService provideUserService(@Named("api_retrofit") Retrofit retrofit,
	                               IRepositoryManager repositoryManager) {

		return repositoryManager.obtainRetrofitService(retrofit, UserService.class);
	}
}
