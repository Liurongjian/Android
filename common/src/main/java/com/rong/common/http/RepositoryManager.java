package com.rong.common.http;

import android.support.v4.util.ArrayMap;

import retrofit2.Retrofit;

/**
 * Created by rong on 2017/5/22.
 */

public class RepositoryManager implements IRepositoryManager {

	private ArrayMap<String, Object> mRetrofitServiceCache;

	public RepositoryManager() {
		mRetrofitServiceCache = new ArrayMap<>();
	}
	@Override
	public void injectRetrofitService(Retrofit retrofit, Class<?>... services) {
		for (Class<?> service : services) {
			if (mRetrofitServiceCache.containsKey(service.getName())) continue;
			mRetrofitServiceCache.put(service.getName(), retrofit.create(service));
		}
	}

	@Override
	public <T> T obtainRetrofitService(Retrofit retrofit, Class<T> service) {

		//如果没就生成
		if (!mRetrofitServiceCache.containsKey(service.getName())) {
			injectRetrofitService(retrofit, service);
		}

		return (T) mRetrofitServiceCache.get(service.getName());
	}

	@Override
	public <T> T obtainCacheService(Retrofit retrofit, Class<T> cache) {
		return null;
	}
}
