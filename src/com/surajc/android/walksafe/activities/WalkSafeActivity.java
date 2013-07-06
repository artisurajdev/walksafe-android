package com.surajc.android.walksafe.activities;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import com.surajc.android.walksafe.R;
import com.surajc.android.walksafe.fragments.CircleFragment;

public class WalkSafeActivity extends FragmentActivity {
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_walksafe);
		swapToCicleFragment();
	}
	
	private void swapToCicleFragment() {
		FragmentManager fragmentManager = getSupportFragmentManager();
		fragmentManager.beginTransaction()
				.replace(R.id.walksafe_activity_fragment_holder, new CircleFragment())
				.commit();
	}
}
