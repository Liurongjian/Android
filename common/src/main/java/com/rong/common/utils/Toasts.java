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

//	public Toast shortNetWarn(Context context, String msg) {
//		int DEFAULT_TEXT_COLOR = Color.parseColor("#FFFFFF");
//		int textSize = 16;
//		Typeface LOADED_TOAST_TYPEFACE = Typeface.create("sans-serif-condensed", 0);
//
//		Toast currentToast = new Toast(context);
//		View toastLayout = View.inflate(context, R.layout.base_progress_dialog, null);
//		ImageView toastIcon = (ImageView)toastLayout.findViewById(es.dmoral.toasty.R.id.toast_icon);
//		TextView toastTextView = (TextView)toastLayout.findViewById(es.dmoral.toasty.R.id.toast_text);
//		Drawable drawableFrame;
//		if(shouldTint) {
//			drawableFrame = ToastyUtils.tint9PatchDrawableFrame(context, tintColor);
//		} else {
//			drawableFrame = ToastyUtils.getDrawable(context, es.dmoral.toasty.R.drawable.toast_frame);
//		}
//
//		ToastyUtils.setBackground(toastLayout, drawableFrame);
//		if(withIcon) {
//			if(icon == null) {
//				throw new IllegalArgumentException("Avoid passing \'icon\' as null if \'withIcon\' is set to true");
//			}
//
//			if(tintIcon) {
//				icon = ToastyUtils.tintIcon(icon, DEFAULT_TEXT_COLOR);
//			}
//
//			ToastyUtils.setBackground(toastIcon, icon);
//		} else {
//			toastIcon.setVisibility(View.GONE);
//		}
//
//		toastTextView.setTextColor(DEFAULT_TEXT_COLOR);
//		toastTextView.setText(msg);
//		toastTextView.setTypeface(LOADED_TOAST_TYPEFACE);
//		toastTextView.setTextSize(2, (float)textSize);
//		currentToast.setView(toastLayout);
//		currentToast.setDuration(Toast.LENGTH_SHORT);
//		return currentToast;
//
//	}
}
