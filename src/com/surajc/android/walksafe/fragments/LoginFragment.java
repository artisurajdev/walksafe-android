package com.surajc.android.walksafe.fragments;

import android.widget.EditText;

public class LoginFragment extends BaseFragment {
	
	public static final String EXTRA_USERNAME = "com.surajc.android.walksafe.extra.USERNAME";
    public static final String EXTRA_PASSWORD = "com.surajc.android.walksafe.extra.PASSWORD";

    private EditText mUserNameEditText;
    private EditText mPasswordEditText;
    
    public static LoginFragment newInstance(){
    	return new LoginFragment();
    }
    

}
