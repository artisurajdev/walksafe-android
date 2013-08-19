package com.surajc.android.walksafe.fragments;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.HashMap;

import android.app.Activity;
import android.content.ContentUris;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.provider.ContactsContract.Contacts;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.surajc.android.walksafe.R;
import com.surajc.android.walksafe.listeners.CircleClickListener;
import com.surajc.android.walksafe.utils.CircleConstants;
import com.surajc.android.walksafe.views.Circle;

public class CircleFragment extends BaseFragment {
	
	private HashMap<Integer, Circle> mCircles;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_circle, container, false);
		return view;
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		mCircles = new HashMap<Integer, Circle>();
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
			circle.setListener(getCircleClickListener());
			mCircles.put(circleIndex, circle);
		}

	}

	private void setupBigCircle() {
		Circle circle = new Circle(getActivity(), CircleConstants.BIG_CIRCLE_INDEX);
		circle.setId(CircleConstants.BIG_CIRCLE_INDEX);
		((ViewGroup) getView()).addView(circle);
	}

	private CircleClickListener getCircleClickListener() {
		CircleClickListener listener = new CircleClickListener() {

			@Override
			public void clicked(int index) {
				launchContactPicker(index);
			}

		};

		return listener;
	}

	public void launchContactPicker(int index) {
		Intent contactPickerIntent = new Intent(Intent.ACTION_PICK, Contacts.CONTENT_URI);
		startActivityForResult(contactPickerIntent, index);
	}

	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		if (resultCode == Activity.RESULT_OK) {
			Circle circle = getCircle(requestCode);
			if (circle != null) {
				Uri baseUri = data.getData();
				String id = baseUri.getLastPathSegment();
				Cursor cursor = null;

				cursor = getActivity().getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null, ContactsContract.CommonDataKinds.Phone.CONTACT_ID + "=?", new String[] { id }, null);

				if (cursor != null) {
					if (cursor.moveToFirst()) {
						int columnIndex = cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.CONTACT_ID);
						String contactIdString = cursor.getString(columnIndex);
						if (contactIdString != null) {
							long contactId = Long.parseLong(contactIdString);
							circle.setPhoto(openPhoto(contactId));
						}
					}
				}
			}
		}
	}

	public Circle getCircle(int index) {
		return mCircles.get(index);
	}

	public InputStream openPhoto(long contactId) {
		Uri contactUri = ContentUris.withAppendedId(Contacts.CONTENT_URI, contactId);
		Uri photoUri = Uri.withAppendedPath(contactUri, Contacts.Photo.CONTENT_DIRECTORY);
		Cursor cursor = getActivity().getContentResolver().query(photoUri, new String[] { Contacts.Photo.PHOTO }, null, null, null);
		if (cursor == null) {
			return null;
		}
		try {
			if (cursor.moveToFirst()) {
				byte[] data = cursor.getBlob(0);
				if (data != null) {
					return new ByteArrayInputStream(data);
				}
			}
		} finally {
			cursor.close();
		}
		return null;
	}

}
