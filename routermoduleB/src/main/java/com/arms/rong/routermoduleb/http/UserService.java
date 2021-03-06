package com.arms.rong.routermoduleb.http;


import com.arms.rong.routermoduleb.pojo.HistResult;
import com.arms.rong.routermoduleb.pojo.ResponseBody;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by rong on 2017/5/22.
 */

public interface UserService {
	String basePre = "";

	@GET(basePre + "/japi/toh")
	Observable<ResponseBody<List<HistResult>>> histDay(@Query("v") int version, @Query("month") int month, @Query("day") int day, @Query("key") String key);
}
