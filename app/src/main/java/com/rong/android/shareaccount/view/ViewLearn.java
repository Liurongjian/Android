package com.rong.android.shareaccount.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by rong on 2017/9/6.
 */

public class ViewLearn extends View {
	private Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
	public ViewLearn(Context context) {
		super(context);
	}

	public ViewLearn(Context context, @Nullable AttributeSet attrs) {
		super(context, attrs);
	}

	public ViewLearn(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
	}

	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);

		paint.setStyle(Paint.Style.STROKE);
		paint.setStrokeWidth(5);
		canvas.drawCircle(300, 300, 200, paint);
	}
}
