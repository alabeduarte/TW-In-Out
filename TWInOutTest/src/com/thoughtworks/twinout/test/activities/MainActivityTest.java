package com.thoughtworks.twinout.test.activities;

import android.widget.Button;

import com.thoughtworks.twinout.MainActivity;
import com.thoughtworks.twinout.R;
import com.thoughtworks.twinout.test.activities.pages.MainPage;

public class MainActivityTest extends BaseActivityIntegrationTest<MainActivity> {

	MainPage mainPage;
	
	@Override public void setUp() throws Exception {
		super.setUp();
		mainPage = new MainPage(solo);
	}
	
	public MainActivityTest() {
		super(MainActivity.class);
	}

	public void testEntryButtonName() throws Exception {
		MainActivity activity = (MainActivity) solo.getCurrentActivity();
		Button buttonEntry = (Button) activity.findViewById(R.id.buttonEntry);
		
		String expectedLabel = solo.getCurrentActivity().getResources().getText(R.string.label_button_in).toString();
		assertEquals(expectedLabel, buttonEntry.getText().toString());
	}
	
	public void testTransitionToTimeInfo() throws InterruptedException {
		mainPage.goToTimeInfoPage();
	}
	
}
