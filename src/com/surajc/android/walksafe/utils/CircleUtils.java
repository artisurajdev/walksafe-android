package com.surajc.android.walksafe.utils;

import com.surajc.android.walksafe.R;

import android.app.Activity;

public class CircleUtils {

	public static int getRadiusOfSmallCirclePx(Activity activity) {
		int radiusOfBigCircle = getRadiusOfBigCirclePx(activity);
		return radiusOfBigCircle
				/ CircleConstants.BIG_CIRLE_TO_SMALL_CIRCLE_RATIO;
	}

	public static int getRadiusOfBigCirclePx(Activity activity) {
		int screenWidth = DisplayUtils.getScreenWidthPx(activity);
		int padding = activity.getResources().getDimensionPixelSize(
				R.dimen.cirle_padding);
		return ((3 * (screenWidth - (2 * padding))) / 8);
	}

	public static int getCenterXForSmallCircle(Activity activity,
			int mCircleIndex) {

		int bigCircleRadius = CircleUtils.getRadiusOfBigCirclePx(activity);
		int bigCircleCenterX = getCenterXForBigCircle(activity);

		double angle = Math.toRadians(mCircleIndex
				* (360 / CircleConstants.MAX_ALLOWED_CIRCLES));
		double cosOfAngle = Math.cos(angle);
		int centerX = (int) (bigCircleCenterX + (bigCircleRadius * cosOfAngle));
		return centerX;
	}

	public static int getCenterYForSmallCircle(Activity activity,
			int mCircleIndex) {
		
		int bigCircleRadius = CircleUtils.getRadiusOfBigCirclePx(activity);
		int bigCircleCenterY = getCenterYForBigCircle(activity);
		
		double angle = Math.toRadians(mCircleIndex
				* (360 / CircleConstants.MAX_ALLOWED_CIRCLES));
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
	
}
