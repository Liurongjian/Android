package com.rong.common.utils;

import android.content.DialogInterface;
import android.graphics.Color;

import com.github.ybq.android.spinkit.style.DoubleBounce;
import com.rong.common.R;
import com.rong.common.arms.lifecycle.AppManager;
import com.rong.common.view.BaseProgressDialog;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Created by rong on 2017/6/1.
 */

@Singleton
public class ProgressUtil {

	private AppManager mAppManager;
	private BaseProgressDialog dialogProgress;

	@Inject
	public ProgressUtil(AppManager appManager) {
		this.mAppManager = appManager;
	}

	private BaseProgressDialog createProgress() {

		if(mAppManager.getCurrentActivity() != null) {
			if(dialogProgress == null || mAppManager.getCurrentActivity() != dialogProgress.getOwnerActivity()) {
				dismissProgress();
				dialogProgress = new BaseProgressDialog(mAppManager.getCurrentActivity(), R.style.base_progress_dialog_style);
				DoubleBounce indeterminateDrawable = new DoubleBounce();
				indeterminateDrawable.setBounds(0, 0, 100, 100);
				indeterminateDrawable.setColor(Color.parseColor("#1AAF5D"));
				dialogProgress.setIndeterminate(true);
				dialogProgress.setIndeterminateDrawable(indeterminateDrawable);
				dialogProgress.setOwnerActivity(mAppManager.getCurrentActivity());
				dialogProgress.setOnDismissListener(dismissListener);
			}
			return dialogProgress;
		} else {
			dismissProgress();
			return null;
		}
	}
	public void showProgress() {
		showProgress(true);
	}

	public void showProgress(boolean cancelable) {
		showProgress(cancelable, null);
	}

	public void showProgress(boolean cancelable, String msg) {
		showProgress(cancelable, msg, null);
	}

	private void showProgress(boolean cancelable, String msg, String title) {
		showProgress(cancelable, msg, title, null);
	}

	public void showProgress(boolean cancelable, String msg, String title, DialogInterface.OnCancelListener cancelListener) {
		if((dialogProgress = createProgress()) != null) {
			dialogProgress.setCancelable(cancelable);
			dialogProgress.setMessage(msg);
			dialogProgress.setTitle(title);
			dialogProgress.setOnCancelListener(cancelListener);
			dialogProgress.show();
		}
	}

	public BaseProgressDialog getDialogProgress() {
		return dialogProgress;
	}

	public void dismissProgress() {
		if(dialogProgress != null && dialogProgress.isShowing()) {
			dialogProgress.dismiss();
		}
	}

	private DialogInterface.OnDismissListener dismissListener = new DialogInterface.OnDismissListener() {
		@Override
		public void onDismiss(DialogInterface dialog) {
			dialogProgress = null;
		}
	};

}
