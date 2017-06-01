package com.rong.common.di.module;

import android.app.Application;

import com.rong.common.http.DnsClient;
import com.rong.common.http.IRepositoryManager;
import com.rong.common.http.RepositoryManager;
import com.rong.common.http.Retrofit2AndroidLog;
import com.rong.common.utils.DevUtil;

import java.util.concurrent.TimeUnit;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by jessyan on 2016/3/14.
 */
@Module
public class HttpModule {
    private static final int TIME_OUT = 10;

    @Singleton
    @Provides
    Retrofit.Builder provideRetrofitBuilder(@Named("private_retrofit_builder") Retrofit.Builder builder, OkHttpClient okHttpClient){

        builder
                .client(okHttpClient)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())//使用rxjava
                .addConverterFactory(GsonConverterFactory.create());//使用Gson

        return builder;
    }
    /**
     * 提供OkhttpClient
     *
     * @return
     */
    @Singleton
    @Provides
    OkHttpClient provideClient(Application application, OkHttpClient.Builder builder, DnsClient dns, HttpLoggingInterceptor logger) {

        builder
                .dns(dns)
                .addInterceptor(logger)
                .connectTimeout(TIME_OUT, TimeUnit.SECONDS)
                .readTimeout(TIME_OUT, TimeUnit.SECONDS);

        return builder.build();
    }

    @Singleton
    @Provides
    HttpLoggingInterceptor provideLoggerIntercepter() {
        return new HttpLoggingInterceptor(new Retrofit2AndroidLog("http-client")).setLevel(DevUtil.isDebug() ?
                HttpLoggingInterceptor.Level.BODY : HttpLoggingInterceptor.Level.NONE);
    }

    @Singleton
    @Provides
    @Named("private_retrofit_builder")
    Retrofit.Builder providePRetrofitBuilder() {
        return new Retrofit.Builder();
    }


    @Singleton
    @Provides
    OkHttpClient.Builder provideClientBuilder() {
        return new OkHttpClient.Builder();
    }

    @Singleton
    @Provides
    DnsClient provideDnsClient() {
        return new DnsClient();
    }

    @Singleton
    @Provides
    IRepositoryManager provideRepositoryManager() {
        return new RepositoryManager();
    }

}
