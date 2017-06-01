package com.rong.common.http;


import retrofit2.Retrofit;

/**
 * Created by jess on 17/03/2017 11:15
 * Contact with jess.yan.effort@gmail.com
 */

public interface IRepositoryManager {

    /**
     * 注入RetrofitService
     * @param services
     */
    void injectRetrofitService(Retrofit retrofit, Class<?>... services);

    /**
     * 根据传入的Class获取对应的Retrift service
     *
     * @param service
     * @param <T>
     * @return
     */
    <T> T obtainRetrofitService(Retrofit retrofit, Class<T> service);

    /**
     * 根据传入的Class获取对应的RxCache service
     *
     * @param cache
     * @param <T>
     * @return
     */
    <T> T obtainCacheService(Retrofit retrofit, Class<T> cache);

}
