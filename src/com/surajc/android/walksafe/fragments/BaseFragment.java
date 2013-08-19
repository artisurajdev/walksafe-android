package com.surajc.android.walksafe.fragments;

import com.surajc.android.walksafe.providers.BusProvider;

import android.support.v4.app.Fragment;

public class BaseFragment extends Fragment {
	
	@Override
	public void onResume() {
		super.onResume();
		BusProvider.getInstance().register(this);
	}
	
	@Override
	public void onPause() {
		super.onPause();
		BusProvider.getInstance().unregister(this);
	}
	
	protected void postEvent(Object event) {
		BusProvider.getInstance().post(event);
	}

}
