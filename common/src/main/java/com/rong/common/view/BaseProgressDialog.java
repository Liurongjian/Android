package com.rong.common.view;

import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.StyleRes;
import android.text.TextUtils;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.rong.common.R;

import butterknife.ButterKnife;

/**
 * Created by rong on 2017/6/1.
 */

public class BaseProgressDialog extends Dialog {

	private ProgressBar mProgress;
	private TextView messageTv;

	private String message;
	private Drawable mIndeterminateDrawable;
	private boolean mIndeterminate;
	private int mMax = 100;
	private int mProgressVal;
	private boolean mHasStarted;

	public BaseProgressDialog(@NonNull Context context) {
		this(context, 0);
	}

	public BaseProgressDialog(@NonNull Context context, @StyleRes int themeResId) {
		super(context, themeResId);
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		View view = View.inflate(getContext(), R.layout.base_progress_dialog, null);
		setContentView(view);

		mProgress = ButterKnife.findById(view, R.id.progress_pb);
		messageTv = ButterKnife.findById(view, R.id.message_tv);
		if(message != null) {
			setMessage(message);
		}

		if (mProgressVal > 0) {
			setProgress(mProgressVal);
		}
		if (mIndeterminateDrawable != null) {
			setIndeterminateDrawable(mIndeterminateDrawable);
		}
		setIndeterminate(mIndeterminate);

		super.onCreate(savedInstanceState);
	}

	@Override
	public void onStart() {
		super.onStart();
		mHasStarted = true;
	}

	@Override
	protected void onStop() {
		super.onStop();
		mHasStarted = false;
	}

	public void setProgress(int value) {
		if (mHasStarted) {
			String msg = String.format("%d%", value * 100 / mMax);
			setMessage(msg);
		} else {
			mProgressVal = value;
		}
	}

	private void setMax(int mMax) {
		this.mMax = mMax;
	}

	public void setMessage(String message) {
		if(messageTv != null) {
			messageTv.setText(message);
			if(TextUtils.isEmpty(message)) {
				messageTv.setVisibility(View.GONE);
			} else {
				messageTv.setVisibility(View.VISIBLE);
			}
			this.message = null;
		} else {
			this.message = message;
		}
	}

	public void setIndeterminateDrawable(Drawable d) {
		if (mProgress != null) {
			mProgress.setIndeterminateDrawable(d);
		} else {
			mIndeterminateDrawable = d;
		}
	}

	public void setIndeterminate(boolean indeterminate) {
		if (mProgress != null) {
			mProgress.setIndeterminate(indeterminate);
		} else {
			mIndeterminate = indeterminate;
		}
	}

	public boolean isIndeterminate() {
		if (mProgress != null) {
			return mProgress.isIndeterminate();
		}
		return mIndeterminate;
	}
}
