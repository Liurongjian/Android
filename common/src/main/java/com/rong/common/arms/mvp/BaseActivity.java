package com.rong.common.arms.mvp;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by rong on 2017/5/23.
 */

public class BaseActivity extends AppCompatActivity implements IView {

	private MessageDelegate msgDelegate;

	@Override
	protected void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		msgDelegate = MessageDelegate.getInstance();
	}

	@Override
	public void showLoading() {

	}

	@Override
	public void hideLoading() {

	}

	@Override
	public void showMessage(String message, @MessageType.ParamMessageType int type) {

		switch (type) {
			case MessageType.TOAST_ERROR:
			case MessageType.TOAST_NORMAL:
			case MessageType.TOAST_SUCCESS:
			case MessageType.TOAST_WARNING:
				msgDelegate.showToastMessage(this, message, type);
				break;
		}
	}

	@Override
	public void launchActivity(Intent intent) {

	}

	@Override
	public void killMyself() {
		finish();
	}

	@Override
	public Context getContext() {
		return this;
	}
}
