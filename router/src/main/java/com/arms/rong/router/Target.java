package com.arms.rong.router;

import android.app.Activity;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Created by rong on 2017/6/2.
 */
@java.lang.annotation.Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface Target {
	Class< ? extends Activity> value();
}
