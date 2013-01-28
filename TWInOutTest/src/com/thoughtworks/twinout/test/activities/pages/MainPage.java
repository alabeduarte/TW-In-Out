package com.thoughtworks.twinout.test.activities.pages;

import com.jayway.android.robotium.solo.Solo;
import com.thoughtworks.twinout.R;
import com.thoughtworks.twinout.TimeInfoActivity;

public class MainPage {

	private final Solo solo;
	
	public MainPage(Solo solo) {
		this.solo = solo;
	}
	
	public void goToTimeInfoPage() {
		solo.clickOnButton(solo.getString(R.string.label_button_in));
		solo.assertCurrentActivity(null, TimeInfoActivity.class);
	}
	
}
