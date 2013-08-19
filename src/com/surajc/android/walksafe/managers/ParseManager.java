package com.surajc.android.walksafe.managers;

import android.content.Context;

import com.parse.Parse;
import com.surajc.android.walksafe.R;

public class ParseManager {
	
	public static void initializeParse(Context context) {
		String applicationId = context.getResources().getString(R.string.parse_application_id);
		String clientKey = context.getResources().getString(R.string.parse_client_key);
		Parse.initialize(context, applicationId, clientKey);
	}
}
