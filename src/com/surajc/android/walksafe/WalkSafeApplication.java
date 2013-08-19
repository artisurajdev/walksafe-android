package com.surajc.android.walksafe;

import com.surajc.android.walksafe.managers.ParseManager;

import android.app.Application;

public class WalkSafeApplication extends Application {
	
	@Override
	public void onCreate() {
		super.onCreate();
		ParseManager.initializeParse(getApplicationContext());
	}

}
