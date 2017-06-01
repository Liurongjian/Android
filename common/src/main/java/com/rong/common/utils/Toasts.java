package com.rong.common.utils;

import android.content.Context;
import android.widget.Toast;

import es.dmoral.toasty.Toasty;

/**
 * 显示Toast工具类
 *
 * @author TomkeyZhang
 *         <p>
 *         改进Toast连续显示多个的情况  2015/09/30 modify by MT
 */
public class Toasts {

	public static void shortWarn(Context context, String msg) {
		Toasty.warning(context, msg).show();
	}

	public static void shortSuccess(Context context, String msg) {
		Toasty.success(context, msg).show();
	}

	public static void shortNormal(Context context, String msg) {

		Toasty.normal(context, msg).show();
	}

	public static void shortError(Context cont, String msg) {
		Toasty.error(cont, msg).show();
	}

	public static void longWarn(Context context, String msg) {

		Toasty.warning(context, msg, Toast.LENGTH_LONG).show();
	}

	public static void longSuccess(Context context, String msg) {

		Toasty.success(context, msg, Toast.LENGTH_LONG).show();
	}

	public static void longNormal(Context context, String msg) {

		Toasty.normal(context, msg, Toast.LENGTH_LONG).show();
	}

	public static void longError(Context cont, String msg) {
		Toasty.error(cont, msg, Toast.LENGTH_LONG).show();
	}

}
