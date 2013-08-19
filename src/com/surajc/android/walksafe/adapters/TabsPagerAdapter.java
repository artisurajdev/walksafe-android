package com.surajc.android.walksafe.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import com.surajc.android.walksafe.fragments.CircleFragment;
import com.surajc.android.walksafe.fragments.SafeguardFragment;

public class TabsPagerAdapter extends FragmentPagerAdapter {

	public TabsPagerAdapter(FragmentManager fm) {
		super(fm);
	}

	@Override
	public Fragment getItem(int position) {
		if (position == 0) {
			return new CircleFragment();
		} else {
			return new SafeguardFragment();
		}
	}

	@Override
	public int getCount() {
		return 2;
	}

	@Override
	public CharSequence getPageTitle(int position) {
		switch (position) {
		case 0:
			return "WalkSafe";
		case 1:
			return "Walk With";
		}
		return null;
	}
}
