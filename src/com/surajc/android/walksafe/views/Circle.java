package com.surajc.android.walksafe.views;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup.LayoutParams;

import com.surajc.android.walksafe.utils.CircleConstants;
import com.surajc.android.walksafe.utils.CircleUtils;
import com.surajc.android.walksafe.utils.DisplayUtils;

public class Circle extends View {

	private int mCircleIndex;
	private Paint mPaintBorder;
	private Paint mPaintFill;
	private Paint mPaintSelectedBorder;
	private Paint mPaintSelectedFill;

	public Circle(Context context, int circleIndex) {
		this(context);
		mCircleIndex = circleIndex;
	}

	public Circle(Context context) {
		super(context);
		mPaintBorder = new Paint();
		mPaintBorder.setStyle(Paint.Style.STROKE);
		mPaintBorder.setStrokeWidth(10);
		mPaintBorder.setARGB(255, 0, 0, 0);

		mPaintFill = new Paint();
		mPaintFill.setStyle(Paint.Style.FILL);
		mPaintFill.setARGB(255, 255, 255, 255);

		mPaintSelectedBorder = new Paint();
		mPaintSelectedBorder.setStyle(Paint.Style.STROKE);
		mPaintSelectedBorder.setStrokeWidth(15);
		mPaintSelectedBorder.setARGB(255, 31, 31, 231);

		mPaintSelectedFill = new Paint();
		mPaintSelectedFill.setStyle(Paint.Style.FILL);
		mPaintSelectedFill.setARGB(255, 255, 255, 255);

		mCircleIndex = 0;

	}

	public Circle(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	public Circle(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
	}
	
//	@Override
//	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
//		Activity activity = (Activity) getContext();
//		int radius;
//		if (mCircleIndex == CircleConstants.BIG_CIRCLE_INDEX) {
//			radius = CircleUtils.getRadiusOfBigCirclePx(activity);
//		} else {
//			radius = CircleUtils.getRadiusOfSmallCirclePx(activity);
//		}
//		
//		int specPx = radius * 2;
//		
//		setMeasuredDimension(specPx, specPx);
//
//	}
//
//	 @Override protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec)
//	    {
//		 Activity activity = (Activity) getContext();
//			int circleRadius;
//			if (mCircleIndex == CircleConstants.BIG_CIRCLE_INDEX) {
//				circleRadius = CircleUtils.getRadiusOfBigCirclePx(activity);
//			} else {
//				circleRadius = CircleUtils.getRadiusOfSmallCirclePx(activity);
//			}
//		 
//	        int measuredWidth = measureWidth(widthMeasureSpec, circleRadius);
//	        if(circleRadius == 0) // No radius specified.
//	        {                     // Lets see what we can make.
//	            // Check width size. Make radius half of available.
//	            circleRadius = measuredWidth / 2;
//	            int tempRadiusHeight = measureHeight(heightMeasureSpec, circleRadius) / 2;
//	            if(tempRadiusHeight < circleRadius)
//	                // Check height, if height is smaller than
//	                // width, then go half height as radius.
//	                circleRadius = tempRadiusHeight;
//	        }
//	        // Remove 2 pixels for the stroke.
//	        int circleDiameter = circleRadius * 2 - 2;
//	        // RectF(float left, float top, float right, float bottom)
//	        int measuredHeight = measureHeight(heightMeasureSpec, circleRadius);
//	        setMeasuredDimension(measuredWidth, measuredHeight);
//	    }
//
//	    private int measureHeight(int measureSpec, int circleRadius) {
//	        int specMode = MeasureSpec.getMode(measureSpec);
//	        int specSize = MeasureSpec.getSize(measureSpec);
//	        int result = 0;
//	        if (specMode == MeasureSpec.AT_MOST) {
//	            result = circleRadius * 2;
//	        } else if (specMode == MeasureSpec.EXACTLY) {
//	            result = specSize;
//	        }
//	        return result;
//	    }
//
//	    private int measureWidth(int measureSpec, int circleRadius) {
//	        int specMode = MeasureSpec.getMode(measureSpec);
//	        int specSize = MeasureSpec.getSize(measureSpec);
//	        int result = 0;
//	        if (specMode == MeasureSpec.AT_MOST) {
//	            result = specSize;
//	        } else if (specMode == MeasureSpec.EXACTLY) {
//	            result = specSize;
//	        }
//	         return result;
//	    }
//	
	
	@Override
	protected void onDraw(Canvas canvas) {

		if (mCircleIndex > CircleConstants.MAX_ALLOWED_CIRCLES) {
			return;
		}

		Activity activity = (Activity) getContext();
		int cx = getCenterX(activity);
		int cy = getCenterY(activity);
		int radius = getRadius(activity);
		
		if (isSelected()) {
			canvas.drawCircle(cx, cy, radius, mPaintSelectedBorder);
		} else {
			canvas.drawCircle(cx, cy, radius, mPaintBorder);
		}
		canvas.drawCircle(cx, cy, radius, mPaintFill);
		super.onDraw(canvas);
	}
	
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		Activity activity = (Activity) getContext();
		switch (event.getAction()) {
		case (MotionEvent.ACTION_DOWN):
			if (isTouchingCircle(activity, event.getX(),event.getY())){
				this.setSelected(!isSelected());
			}
			break;
		}
		return super.onTouchEvent(event);
		
		
	}

	private boolean isTouchingCircle(Activity activity, float x, float y) {
		if (mCircleIndex == CircleConstants.BIG_CIRCLE_INDEX) {
			return false;
		}
		int radius = getRadius(activity);
		int centerX = getCenterX(activity);
		int centerY = getCenterY(activity);
		
		return (x > (centerX - radius)) && (x < (centerX + radius))
				&& (y > (centerY - radius)) && (y < (centerY + radius));
	}

	
	private int getRadius(Activity activity) {
		if (mCircleIndex == CircleConstants.BIG_CIRCLE_INDEX) {
			return CircleUtils.getRadiusOfBigCirclePx(activity);
		} else {
			return CircleUtils.getRadiusOfSmallCirclePx(activity);
		}		
	}
	private int getCenterX(Activity activity) {
		if (mCircleIndex == CircleConstants.BIG_CIRCLE_INDEX) {
			return CircleUtils.getCenterXForBigCircle(activity);
		} else {
			return CircleUtils.getCenterXForSmallCircle(activity, mCircleIndex);
		}		
	}
	private int getCenterY(Activity activity) {
		if (mCircleIndex == CircleConstants.BIG_CIRCLE_INDEX) {
			return CircleUtils.getCenterYForBigCircle(activity);
		} else {
			return CircleUtils.getCenterYForSmallCircle(activity, mCircleIndex);
		}		
	}

}
