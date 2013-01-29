package com.thoughtworks.twinout.test.activities;

import java.util.Calendar;

import android.app.Activity;
import android.widget.Button;

import com.thoughtworks.twinout.MainActivity;
import com.thoughtworks.twinout.R;
import com.thoughtworks.twinout.TimeCard;
import com.thoughtworks.twinout.TimeCardType;
import com.thoughtworks.twinout.TimeInfoActivity;
import com.thoughtworks.twinout.db.TimeCardDataSource;
import com.thoughtworks.twinout.test.activities.pages.TimeInfoPage;

public class TimeInfoActivityTest extends BaseActivityIntegrationTest<TimeInfoActivity> {

	TimeCardDataSource dataSource;
	TimeInfoPage timeInfoPage;
	
	@Override public void setUp() throws Exception {
		super.setUp();
		timeInfoPage = new TimeInfoPage(solo).onDate(2013, Calendar.JANUARY, 2).atTime(9, 0);
		dataSource = new TimeCardDataSource(getInstrumentation().getTargetContext());
		dataSource.open();
	}

	@Override public void tearDown() throws Exception {
		dataSource.deleteAll();
		dataSource.close();
		super.tearDown();
	}
	
	public TimeInfoActivityTest() {
		super(TimeInfoActivity.class);
	}

	public void testShouldAddNewTimeCard() {
		timeInfoPage.confirm();
		
		TimeCard timeCard = dataSource.getLastInDate();
		assertPresenceOf(timeCard);
	}
	
	public void testTransitionOfTheTimeCardInfoToMainScreen() {
		timeInfoPage.confirm();
		solo.assertCurrentActivity(null, MainActivity.class);
		
		Activity activity = solo.getCurrentActivity();
		Button buttonEntry = (Button) activity.findViewById(R.id.buttonEntry);
		
		String expectedLabel = solo.getCurrentActivity().getResources().getText(R.string.label_button_out).toString();
		assertEquals(expectedLabel, buttonEntry.getText().toString());
	}

	private void assertPresenceOf(TimeCard timeCard) {
		assertNotNull(timeCard);
		assertEquals(timeInfoPage.getInputDate(), timeCard.getDateTime());
		assertEquals(TimeCardType.IN, timeCard.getType());
	}
	
}
