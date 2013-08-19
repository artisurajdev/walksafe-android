package com.surajc.android.walksafe.activities;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.os.Build;
import android.support.v7.app.ActionBarActivity;
import android.view.View;

import com.surajc.android.walksafe.providers.BusProvider;
import com.surajc.android.walksafe.views.StatusView;

public abstract class BaseActivity extends ActionBarActivity {
	
	
	protected abstract StatusView getStatusView();
	
	@Override
	protected void onResume() {
		super.onResume();
		BusProvider.getInstance().register(this);
	}
	
	@Override
	protected void onPause() {
		super.onPause();
		BusProvider.getInstance().unregister(this);
	}
	
	protected void postEvent(Object event) {
		BusProvider.getInstance().post(event);
	}
	
	@TargetApi(Build.VERSION_CODES.HONEYCOMB_MR2)
	protected void showProgress(final boolean show, final String message) {
		final StatusView statusView = getStatusView();
		if (statusView == null) {
			return;
		}
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR2) {
            int shortAnimTime = getResources().getInteger(android.R.integer.config_shortAnimTime);

            statusView.setVisibility(View.VISIBLE);
            statusView.animate()
                    .setDuration(shortAnimTime)
                    .alpha(show ? 1 : 0)
                    .setListener(new AnimatorListenerAdapter() {
                        @Override
                        public void onAnimationEnd(Animator animation) {
                        	statusView.setVisibility(show ? View.VISIBLE : View.GONE);
                        }
                    });
        } else {
        	statusView.setVisibility(show ? View.VISIBLE : View.GONE);
        }
    }
	
}
