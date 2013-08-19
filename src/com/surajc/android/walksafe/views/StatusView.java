package com.surajc.android.walksafe.views;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.surajc.android.walksafe.R;

public class StatusView extends LinearLayout {

	private TextView mStatusMessage;

	public StatusView(Context context) {
		super(context);
		initViews(context);
	}

	public StatusView(Context context, AttributeSet attrs) {
		super(context, attrs);
		initViews(context);
	}

	public StatusView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		initViews(context);
	}

	private void initViews(Context context){

		LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		inflater.inflate(R.layout.status_view, this, true);
		mStatusMessage = (TextView)findViewById(R.id.status_message);
	}

	public void setStatusMessage(String statusMessage){
		mStatusMessage.setText(statusMessage);
	}

}