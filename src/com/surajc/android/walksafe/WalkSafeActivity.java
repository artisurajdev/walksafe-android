package com.surajc.android.walksafe;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

public class WalkSafeActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_walk_safe);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.walk_safe, menu);
		return true;
	}

}
