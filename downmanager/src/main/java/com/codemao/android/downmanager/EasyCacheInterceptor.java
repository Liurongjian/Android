package com.codemao.android.downmanager;


import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by yangy on 2017/2/15.
 */
public class EasyCacheInterceptor implements Interceptor {

	public EasyCacheInterceptor() {
	}

	@Override
	public Response intercept(Chain chain) throws IOException {
		final Request originalRequest = chain.request();
		String cacheHeaderValue = null;
		cacheHeaderValue = true
				? "public, max-age=" + EasyCacheTime.CACHE_TIME_SHORT
				: "public, only-if-cached, max-stale=86400";

		final Request finalRequest = originalRequest.newBuilder()
				.tag(originalRequest.url().toString())
				.build();

		Response response = chain.proceed(originalRequest);

		if (response.code() < 400) {
			return response.newBuilder()
					.removeHeader("Pragma")
					.removeHeader("Cache-Control")
					.addHeader("Cache-Control", cacheHeaderValue)
					.build();
		}
		return response;
	}
}
