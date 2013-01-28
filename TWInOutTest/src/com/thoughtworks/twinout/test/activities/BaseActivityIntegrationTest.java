package com.thoughtworks.twinout.test.activities;

import android.app.Activity;
import android.test.ActivityInstrumentationTestCase2;

import com.jayway.android.robotium.solo.Solo;
import com.thoughtworks.twinout.test.activities.pages.MainPage;

public abstract class BaseActivityIntegrationTest<T extends Activity> extends ActivityInstrumentationTestCase2<T> {

	public Solo solo;
	public MainPage mainPage;
	
	public BaseActivityIntegrationTest(Class<T> activityClass) {
		super(activityClass);
	}
	
	@Override
    public void setUp() throws Exception {
        solo = new Solo(getInstrumentation(), getActivity());
        mainPage = new MainPage(solo);
        Thread.sleep(2000);
    }
	
	@Override
    public void tearDown() throws Exception {
        solo.finishOpenedActivities();
    }

}
