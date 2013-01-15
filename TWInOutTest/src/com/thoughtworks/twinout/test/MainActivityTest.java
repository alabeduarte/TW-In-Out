package com.thoughtworks.twinout.test;

import android.test.ActivityInstrumentationTestCase2;
import android.widget.TextView;

import com.thoughtworks.twinout.MainActivity;
import com.thoughtworks.twinout.R;

public class MainActivityTest extends
		ActivityInstrumentationTestCase2<MainActivity> {

	public MainActivityTest() {
		super(MainActivity.class);
	}

	public void testPreferenceIsSaved() throws Exception {
		MainActivity activity = getActivity();
		TextView view = (TextView) activity.findViewById(R.id.hello);
		assertEquals("Hello world! Ronualdo", view.getText());
	}

	// public void testRegisteringIN() throws Exception {
	// Button button = (Button)activity.findViewById(R.id.inButton);
	// button.performClick();
	// String message = activity.
	// }
}
