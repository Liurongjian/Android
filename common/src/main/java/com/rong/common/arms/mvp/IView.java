package com.rong.common.arms.mvp;

import android.content.Context;
import android.content.Intent;

/**
 * Created by rong on 2017/5/23.
 */

public interface IView {
	/**
	 * 显示加载
	 */
	void showLoading();

	/**
	 * 隐藏加载
	 */
	void hideLoading();

	/**
	 * 显示信息
	 */
	void showMessage(String message, @MessageType.ParamMessageType int type);

	/**
	 * 跳转activity
	 */
	void launchActivity(Intent intent);

	/**
	 * 杀死自己
	 */
	void killMyself();

	/**
	 * 获取上下文
	 * @return
	 */
	Context getContext();


}
