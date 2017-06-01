package com.rong.common.arms.mvp;

import android.content.Context;

import com.rong.common.utils.Toasts;

/**
 * Created by rong on 2017/5/23.
 */

public class MessageDelegate {

	private MessageDelegate() {

	}

	private static MessageDelegate INSTANCE;
	public static MessageDelegate getInstance() {
		if(INSTANCE == null) {
			INSTANCE = new MessageDelegate();
		}

		return INSTANCE;
	}

	public void showToastMessage(Context cont, String msg, int type) {
		switch (type) {
			case MessageType.TOAST_ERROR:
				Toasts.shortError(cont, msg);
				break;
			case MessageType.TOAST_NORMAL:
				Toasts.shortNormal(cont, msg);
				break;
			case MessageType.TOAST_SUCCESS:
				Toasts.shortSuccess(cont, msg);
				break;
			case MessageType.TOAST_WARNING:
				Toasts.shortWarn(cont, msg);
				break;
		}
	}
}
