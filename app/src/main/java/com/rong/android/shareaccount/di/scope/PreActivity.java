package com.rong.android.shareaccount.di.scope;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;

import javax.inject.Scope;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * Created by rong on 2017/5/22.
 */
@Scope
@Documented
@Retention(RUNTIME)
public @interface PreActivity {}
