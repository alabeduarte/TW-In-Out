package com.thoughtworks.twinout.test.activities;

import java.util.Calendar;

import com.thoughtworks.twinout.MainActivity;
import com.thoughtworks.twinout.TimeCard;
import com.thoughtworks.twinout.TimeCardType;
import com.thoughtworks.twinout.TimeInfoActivity;
import com.thoughtworks.twinout.db.TimeCardDataSource;
import com.thoughtworks.twinout.test.activities.pages.TimeInfoPage;

public class TimeInfoActivityTest extends BaseActivityIntegrationTest<TimeInfoActivity> {

	private TimeCardDataSource dataSource;

	@Override
	public void setUp() throws Exception {
		super.setUp();
		dataSource = new TimeCardDataSource(getInstrumentation().getTargetContext());
		dataSource.open();
	}

	@Override
	public void tearDown() throws Exception {
		dataSource.deleteAll();
		dataSource.close();
		super.tearDown();
	}
	
	public TimeInfoActivityTest() {
		super(TimeInfoActivity.class);
	}

	public void testShouldAddNewTimeCard() {
		TimeInfoPage timeInfoPage = new TimeInfoPage(solo).onDate(2013, Calendar.JANUARY, 2).atTime(9, 0);
		timeInfoPage.confirm();
		
		TimeCard timeCard = dataSource.getLastInDate();
		assertPresenceOf(timeCard, timeInfoPage);
	}
	
	public void testTransitionOfTheTimeCardInfoToMainScreen() {
		TimeInfoPage timeInfoPage = new TimeInfoPage(solo).onDate(2013, Calendar.JANUARY, 2).atTime(9, 0);
		timeInfoPage.confirm();
		solo.assertCurrentActivity(null, MainActivity.class);
	}

	private void assertPresenceOf(TimeCard timeCard, TimeInfoPage timeInfoPage) {
		assertNotNull(timeCard);
		assertEquals(timeInfoPage.getInputDate(), timeCard.getDateTime());
		assertEquals(TimeCardType.IN, timeCard.getType());
	}
	
}
