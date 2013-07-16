package com.surajc.android.walksafe.utils;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff.Mode;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;

import com.surajc.android.walksafe.R;

public class CircleUtils {

	public static int getRadiusOfSmallCirclePx(Activity activity) {
		int radiusOfBigCircle = getRadiusOfBigCirclePx(activity);
		return radiusOfBigCircle / CircleConstants.BIG_CIRLE_TO_SMALL_CIRCLE_RATIO;
	}

	public static int getRadiusOfBigCirclePx(Activity activity) {
		int screenWidth = DisplayUtils.getScreenWidthPx(activity);
		int padding = activity.getResources().getDimensionPixelSize(R.dimen.cirle_padding);
		return ((3 * (screenWidth - (2 * padding))) / 8);
	}

	public static int getCenterXForSmallCircle(Activity activity, int mCircleIndex) {

		int bigCircleRadius = CircleUtils.getRadiusOfBigCirclePx(activity);
		int bigCircleCenterX = getCenterXForBigCircle(activity);

		double angle = Math.toRadians(mCircleIndex * (360 / CircleConstants.MAX_ALLOWED_CIRCLES));
		double cosOfAngle = Math.cos(angle);
		int centerX = (int) (bigCircleCenterX + (bigCircleRadius * cosOfAngle));
		return centerX;
	}

	public static int getCenterYForSmallCircle(Activity activity, int mCircleIndex) {

		int bigCircleRadius = CircleUtils.getRadiusOfBigCirclePx(activity);
		int bigCircleCenterY = getCenterYForBigCircle(activity);

		double angle = Math.toRadians(mCircleIndex * (360 / CircleConstants.MAX_ALLOWED_CIRCLES));
		double sinOfAngle = Math.sin(angle);
		int centerY = (int) (bigCircleCenterY + (bigCircleRadius * sinOfAngle));
		return centerY;
	}

	public static int getCenterXForBigCircle(Activity activity) {
		return DisplayUtils.getScreenWidthPx(activity) / 2;
	}

	public static int getCenterYForBigCircle(Activity activity) {
		return DisplayUtils.getScreenHeightPx(activity) / 2;
	}
	
	
	public static Bitmap getCirclePhoto(Bitmap bitmap, int radius) {
		 Bitmap output = Bitmap.createBitmap(bitmap.getWidth(),
				 bitmap.getHeight(), Config.ARGB_8888);
		Canvas canvas = new Canvas(output);
		
		final int color = 0xff424242;
        final Paint paint = new Paint();
        final Rect rect = new Rect(0, 0, output.getWidth(), output.getHeight());
        final RectF rectF = new RectF(rect);
        final float roundPx = radius;

        paint.setAntiAlias(true);
        canvas.drawARGB(0, 0, 0, 0);
        paint.setColor(color);
        canvas.drawRoundRect(rectF, roundPx, roundPx, paint);

        paint.setXfermode(new PorterDuffXfermode(Mode.SRC_IN));
        canvas.drawBitmap(bitmap, rect, rect, paint);
        
        return output;
    }
	
}
