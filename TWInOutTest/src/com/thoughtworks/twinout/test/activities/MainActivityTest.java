package com.thoughtworks.twinout.test.activities;

import android.test.ActivityInstrumentationTestCase2;
import android.widget.Button;

import com.thoughtworks.twinout.MainActivity;
import com.thoughtworks.twinout.R;

public class MainActivityTest extends
		ActivityInstrumentationTestCase2<MainActivity> {

	public MainActivityTest() {
		super(MainActivity.class);
	}

	public void testEntryButtonName() throws Exception {
		MainActivity activity = getActivity();
		Button buttonEntry= (Button) activity.findViewById(R.id.buttonEntry);
		assertEquals(getActivity().getResources().getText(R.string.label_button_in), buttonEntry.getText());
	}
	
}