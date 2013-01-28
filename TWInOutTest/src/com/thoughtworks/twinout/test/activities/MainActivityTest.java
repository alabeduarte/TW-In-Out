package com.thoughtworks.twinout.test.activities;

import android.widget.Button;

import com.thoughtworks.twinout.MainActivity;
import com.thoughtworks.twinout.R;

public class MainActivityTest extends BaseActivityIntegrationTest<MainActivity> {

	public MainActivityTest() {
		super(MainActivity.class);
	}

	public void testEntryButtonName() throws Exception {
		MainActivity activity = (MainActivity) solo.getCurrentActivity();
		Button buttonEntry = (Button) activity.findViewById(R.id.buttonEntry);
		
		assertEquals(activity.getResources().getText(R.string.label_button_in), buttonEntry.getText());
	}
	
	public void testTransitionToTimeInfo() throws InterruptedException {
		mainPage.goToTimeInfoPage();
	}
	
}
