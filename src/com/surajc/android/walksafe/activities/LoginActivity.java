package com.surajc.android.walksafe.activities;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import com.parse.Parse;
import com.squareup.otto.Subscribe;
import com.surajc.android.walksafe.R;
import com.surajc.android.walksafe.fragments.LoginFragment;
import com.surajc.android.walksafe.views.StatusView;

public class LoginActivity extends BaseActivity {
	
	private StatusView mStatusView;
	
	@Override
	protected void onCreate(Bundle arg0) {
		super.onCreate(arg0);
		setContentView(R.layout.activity_login);
		Parse.initialize(this, "Your App Id", "Your Client ID");
		
		FragmentManager fragmentManager = getSupportFragmentManager();
    	FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
    	fragmentTransaction.replace(R.id.login_container, LoginFragment.newInstance());
    	fragmentTransaction.commit();
	}
	
	@Override
	protected void onResume() {
		super.onResume();
	    mStatusView = (StatusView) findViewById(R.id.loginStatusView);
	}

	@Override
	protected StatusView getStatusView() {
		return mStatusView;
	}
	
	@Subscribe
	public void showProgressBar() {
		
	}

}
