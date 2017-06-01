package com.rong.common.utils;

import com.google.gson.Gson;

/**
 * Created by rong on 2017/5/23.
 */

public class JsonUtil {

	private static Gson gsonClient = new Gson();

	public static String toJson(Object src) {
		return gsonClient.toJson(src);
	}

	public static <T> T fromJson(String str, Class<T> target) {
		return gsonClient.fromJson(str, target);
	}
}
