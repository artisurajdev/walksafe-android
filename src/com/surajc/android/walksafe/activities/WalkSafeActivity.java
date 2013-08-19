package com.surajc.android.walksafe.activities;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBar.Tab;

import com.surajc.android.walksafe.R;
import com.surajc.android.walksafe.adapters.TabsPagerAdapter;
import com.surajc.android.walksafe.views.StatusView;

public class WalkSafeActivity extends BaseActivity implements ActionBar.TabListener {
	
	private TabsPagerAdapter mTabsPagerAdapter;
	private ViewPager mViewPager;
	private StatusView mStatusView;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.activity_walksafe);
		
		final ActionBar actionBar = getSupportActionBar();
		actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
		
		mStatusView = (StatusView) findViewById(R.id.walksafeStatusView);
		
		mTabsPagerAdapter = new TabsPagerAdapter(getSupportFragmentManager());

		mViewPager = (ViewPager) findViewById(R.id.pager);
		mViewPager.setAdapter(mTabsPagerAdapter);
		mViewPager.setOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
			@Override
			public void onPageSelected(int position) {
				actionBar.setSelectedNavigationItem(position);
			}
		});

		// For each of the sections in the app, add a tab to the action bar.
		for (int i = 0; i < mTabsPagerAdapter.getCount(); i++) {
			// Create a tab with text corresponding to the page title defined by
			// the adapter. Also specify this Activity object, which implements
			// the TabListener interface, as the callback (listener) for when
			// this tab is selected.
			actionBar.addTab(actionBar.newTab().setText(mTabsPagerAdapter.getPageTitle(i)).setTabListener(this));
		}
	}

	@Override
	public void onTabSelected(Tab tab, FragmentTransaction ft) {
		mViewPager.setCurrentItem(tab.getPosition());		
	}

	@Override
	public void onTabReselected(Tab tab, FragmentTransaction ft) {
	}

	@Override
	public void onTabUnselected(Tab tab, FragmentTransaction ft) {
	}

	@Override
	protected StatusView getStatusView() {
		return mStatusView;
	}
}
