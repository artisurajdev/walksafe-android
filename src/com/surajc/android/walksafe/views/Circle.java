package com.surajc.android.walksafe.views;

import java.io.InputStream;
import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Shader;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import com.surajc.android.walksafe.R;
import com.surajc.android.walksafe.listeners.CircleClickListener;
import com.surajc.android.walksafe.utils.CircleConstants;
import com.surajc.android.walksafe.utils.CircleUtils;

public class Circle extends View {

	private int mCircleIndex;
	private Paint mPaintBorder;
	private Paint mPaintFill;
	private Paint mPaintSelectedBorder;
	private Bitmap mPhoto;
	private ArrayList<CircleClickListener> mListeners;

	public Circle(Context context, int circleIndex) {
		this(context);
		mCircleIndex = circleIndex;

		Bitmap photo = BitmapFactory.decodeResource(getResources(), R.drawable.add_contact);
		Bitmap scaledPhoto = Bitmap.createScaledBitmap(photo, getRadius() * 2, getRadius() * 2, true);
		photo.recycle();
		mPhoto = CircleUtils.getCirclePhoto(scaledPhoto, getRadius());
		scaledPhoto.recycle();
	}

	public Circle(Context context) {
		super(context);

		mPaintBorder = new Paint();
		mPaintBorder.setStyle(Paint.Style.STROKE);
		mPaintBorder.setStrokeCap(Paint.Cap.SQUARE);
		mPaintBorder.setStrokeWidth(15);
		mPaintBorder.setColor(getResources().getColor(R.color.cool_black));
		mPaintBorder.setAntiAlias(true);

		mPaintFill = new Paint(Paint.FILTER_BITMAP_FLAG);

		mPaintSelectedBorder = new Paint();
		mPaintSelectedBorder.setStyle(Paint.Style.STROKE);
		mPaintSelectedBorder.setStrokeCap(Paint.Cap.SQUARE);
		mPaintSelectedBorder.setStrokeWidth(15);
		mPaintSelectedBorder.setColor(getResources().getColor(R.color.android_green));
		mPaintSelectedBorder.setAntiAlias(true);
		
		mCircleIndex = 0;

		mListeners = new ArrayList<CircleClickListener>();
	}

	public Circle(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	public Circle(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
	}

	@Override
	protected void onDraw(Canvas canvas) {

		if (mCircleIndex > CircleConstants.MAX_ALLOWED_CIRCLES) {
			return;
		}

		int cx = getCenterX();
		int cy = getCenterY();
		int radius = getRadius();

		if (isSelected()) {
			canvas.drawCircle(cx, cy, radius, mPaintSelectedBorder);
		} else {
			canvas.drawCircle(cx, cy, radius, mPaintBorder);
		}

		mPaintFill.setAntiAlias(true);
		if (!isBigCircle()) {
			canvas.drawBitmap(mPhoto, cx - getRadius(), cy - getRadius(), mPaintFill);
		}

		super.onDraw(canvas);
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		Activity activity = getActivity();
		switch (event.getAction()) {
		case (MotionEvent.ACTION_DOWN):
			if (isTouchingCircle(activity, event.getX(), event.getY())) {
				this.setSelected(!isSelected());
				notifyListeners();
			}
			break;
		}
		return super.onTouchEvent(event);
	}

	private void notifyListeners() {
		if (mListeners != null) {
			for (CircleClickListener listener : mListeners) {
				listener.clicked(getIndex());
			}
		}
	}

	private boolean isTouchingCircle(Activity activity, float x, float y) {
		if (isBigCircle()) {
			return false;
		}
		int radius = getRadius();
		int centerX = getCenterX();
		int centerY = getCenterY();

		return (x > (centerX - radius)) && (x < (centerX + radius)) && (y > (centerY - radius)) && (y < (centerY + radius));
	}

	private int getRadius() {
		Activity activity = getActivity();
		if (isBigCircle()) {
			return CircleUtils.getRadiusOfBigCirclePx(activity);
		} else {
			return CircleUtils.getRadiusOfSmallCirclePx(activity);
		}
	}

	private int getCenterX() {
		Activity activity = getActivity();
		if (isBigCircle()) {
			return CircleUtils.getCenterXForBigCircle(activity);
		} else {
			return CircleUtils.getCenterXForSmallCircle(activity, mCircleIndex);
		}
	}

	private int getCenterY() {
		Activity activity = getActivity();
		if (isBigCircle()) {
			return CircleUtils.getCenterYForBigCircle(activity);
		} else {
			return CircleUtils.getCenterYForSmallCircle(activity, mCircleIndex);
		}
	}

	private boolean isBigCircle() {
		return mCircleIndex == CircleConstants.BIG_CIRCLE_INDEX;
	}

	public int getIndex() {
		return mCircleIndex;
	}

	public void setListener(CircleClickListener listener) {
		if (mListeners != null) {
			mListeners.add(listener);
		}
	}

	public void setPhoto(InputStream inStream) {
		if (inStream != null) {
			Bitmap photo = BitmapFactory.decodeStream(inStream);
			Bitmap scaledPhoto = Bitmap.createScaledBitmap(photo, getRadius() * 2, getRadius() * 2, true);
			photo.recycle();
			mPhoto = CircleUtils.getCirclePhoto(scaledPhoto, getRadius());
			scaledPhoto.recycle();
			invalidate();
		}
	}

	private int getDiameter() {
		return (getRadius() * 2);
	}

	private Activity getActivity() {
		Activity activity = (Activity) getContext();
		return activity;
	}

}
