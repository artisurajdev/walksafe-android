package com.surajc.android.walksafe.utils;

import android.app.Activity;
import android.util.DisplayMetrics;

public class DisplayUtils {
	
	public static int getScreenHeightDp(Activity activity) {
		int heightInPx = getScreenHeightPx(activity);
		int heightInDp = getDpFromPx(activity, heightInPx);
		return heightInDp;
	}
	
	public static int getScreenWidthDp(Activity activity) {
		int widthInPx = getScreenWidthPx(activity);
		int widthInDp = getDpFromPx(activity, widthInPx);
		return widthInDp;
	}
	
	public static int getScreenHeightPx(Activity activity) {
		int heightInPx = getDisplayMetrics(activity).heightPixels;
		return heightInPx;
	}
	
	public static int getScreenWidthPx(Activity activity) {
		int widthInPx = getDisplayMetrics(activity).widthPixels;
		return widthInPx;
	}
	
	public static int getDpFromPx(Activity activity, int px) {
		final DisplayMetrics metrics = getDisplayMetrics(activity);
		int dp = (int) (px / metrics.density + 0.5); // add 0.5 to round to nearest whole number
		return dp;
	}

	private static DisplayMetrics getDisplayMetrics(Activity activity) {
		final DisplayMetrics metrics = new DisplayMetrics();
		activity.getWindowManager().getDefaultDisplay().getMetrics(metrics);
		return metrics;
	}

}
