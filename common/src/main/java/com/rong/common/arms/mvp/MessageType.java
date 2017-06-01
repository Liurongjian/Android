package com.rong.common.arms.mvp;

import android.support.annotation.IntDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Created by rong on 2017/5/23.
 */

public interface MessageType {

	int TOAST_SUCCESS = 0x01;
	int TOAST_WARNING = 0x02;
	int TOAST_NORMAL = 0x04;
	int TOAST_ERROR = 0x08;

	@IntDef({TOAST_SUCCESS, TOAST_WARNING, TOAST_NORMAL, TOAST_ERROR})
	@Retention(RetentionPolicy.SOURCE)
	@interface ParamMessageType {

	}
}
