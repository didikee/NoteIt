package com.inno.noteit.ui.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.Button;

import java.util.Timer;
import java.util.TimerTask;

public class HorizontalProgressButton extends Button {

	private Paint mPaint = new Paint();
	private int mProgress = 0;

	public HorizontalProgressButton(Context context) {
		super(context);
	}

	public HorizontalProgressButton(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	public HorizontalProgressButton(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
	}

	@SuppressLint("DrawAllocation")
	@Override
	protected void onDraw(Canvas canvas) {
		mPaint.setColor(getContext().getResources().getColor(android.R.color.holo_blue_bright));
		mPaint.setAntiAlias(true);
		mPaint.setAlpha(128);
		mPaint.setStrokeWidth(1.0f);
		Rect rect = new Rect();
		canvas.getClipBounds(rect);
		rect.left += getPaddingLeft();
		rect.top += getPaddingTop();
		rect.right = (rect.left - getPaddingLeft()) + (mProgress * getWidth() / 100) - getPaddingRight();
		rect.bottom -= getPaddingBottom();
		canvas.drawRoundRect(new RectF(rect), 8.0f, 8.0f, mPaint);
		super.onDraw(canvas);
	}

	private void updateProgress() {
		if (mProgress >= 0 && mProgress <= 100) {
			postInvalidate();
		} else {
			mProgress = 100;
			postInvalidate();
		}
	}

	/** 总共执行的时间 **/
	private int totalTime = 1000;
	

	/** 总为100单位 **/
	private final int startLenght = 62;
	private final int endLenght = 38;

	public int getProgress() {

		return mProgress;
	}

	private Timer mTimer;
	private TimerTask mTask;

	public void startAnimate() {
		mProgress=0;
		if (mTimer != null || mTask != null) {
			mTimer = null;
			mTask = null;
		}
		mTimer = new Timer();
		mTask = new TimerTask() {

			@Override
			public void run() {
				calculateProgress();
			}
		};
		mTimer.schedule(mTask, 0, 10);
	}

	private int timeCount = 1;

	private void calculateProgress() {
		if (mProgress < 62) {
			mProgress += (int) (Math.sin((Math.PI / 50) * timeCount) * 4.5);
			updateProgress();
			timeCount++;
			Log.e("mProgress", "##:" + mProgress);
		} else if (mProgress < 100) {
//			mProgress += (int) (Math.sin(Math.PI / 2 + (Math.PI / 50) * timeCount) * 3.16);
			mProgress +=(int)(Math.sin((Math.PI / 50) * timeCount) * 4.5-Math.sqrt(25-timeCount)/(26-timeCount));
			updateProgress();
			timeCount--;
			Log.e("mProgress", "##:" + mProgress);
		} else if (mProgress <= 0 || mProgress >= 100 || timeCount >= 26 || timeCount <= 0) {
			mProgress = 100;
			updateProgress();
			endAnimate();
		}

	}

	private void endAnimate() {
		if (mTimer != null) {
			mTimer.cancel();
		}
		mTimer = null;
		mTask = null;
		timeCount = 1;
	}
}
