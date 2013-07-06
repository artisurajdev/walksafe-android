package com.surajc.android.walksafe.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.surajc.android.walksafe.R;
import com.surajc.android.walksafe.utils.CircleConstants;
import com.surajc.android.walksafe.views.Circle;

public class CircleFragment extends Fragment {
	
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_circle, container, false);
		return view;
	}
	
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		setupCircles();
	}

	private void setupCircles() {
		setupBigCircle();
		setupSmallCircles();
	}

	private void setupSmallCircles() {
		for (int circleIndex = 1; circleIndex <= CircleConstants.MAX_ALLOWED_CIRCLES; circleIndex++) {
			Circle circle = new Circle(getActivity(), circleIndex);
			circle.setId(circleIndex);
			((ViewGroup) getView()).addView(circle);
		}
		
	}

	private void setupBigCircle() {
		Circle circle = new Circle(getActivity(), CircleConstants.BIG_CIRCLE_INDEX);
		circle.setId(CircleConstants.BIG_CIRCLE_INDEX);
		((ViewGroup) getView()).addView(circle);
	}
}
