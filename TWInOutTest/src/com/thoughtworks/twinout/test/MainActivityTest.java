package com.thoughtworks.twinout.test;

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
		Button button= (Button) activity.findViewById(R.id.buttonIn);
		assertEquals("IN", button.getText());
	}
	
	// public void testRegisteringIN() throws Exception {
	// Button button = (Button)activity.findViewById(R.id.inButton);
	// button.performClick();
	// String message = activity.
	// }
}
