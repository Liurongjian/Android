package com.arms.rong.router;

import android.content.Context;
import android.content.Intent;

import java.util.Map;

/**
 * Created by rong on 2017/6/2.
 */

public interface IRouter {
	void inject(Class module);
	Intent findIntent(Context cont, String path, Map<String, Object> params);
	void setDebug(boolean isDebug);
}
