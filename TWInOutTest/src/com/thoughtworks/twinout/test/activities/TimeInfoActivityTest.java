package com.thoughtworks.twinout.test.activities;

import java.util.Calendar;

import android.test.ActivityInstrumentationTestCase2;

import com.thoughtworks.twinout.TimeCard;
import com.thoughtworks.twinout.TimeCardType;
import com.thoughtworks.twinout.TimeInfoActivity;
import com.thoughtworks.twinout.db.TimeCardDataSource;
import com.thoughtworks.twinout.test.pages.MainPage;
import com.thoughtworks.twinout.test.pages.TimeInfoPage;

public class TimeInfoActivityTest extends ActivityInstrumentationTestCase2<TimeInfoActivity> {

	private TimeCardDataSource dataSource;

	@Override
	protected void setUp() throws Exception {
		super.setUp();
		dataSource = new TimeCardDataSource(getInstrumentation().getTargetContext());
		dataSource.open();
	}

	@Override
	protected void tearDown() throws Exception {
		dataSource.deleteAll();
		dataSource.close();
		super.tearDown();
	}
	
	public TimeInfoActivityTest() {
		super(TimeInfoActivity.class);
	}

	public void testShouldAddNewTimeCard() {
		MainPage mainPage = MainPage.create(getInstrumentation());
		setActivity(mainPage.getActivity());
		
		TimeInfoPage timeInfoPage = mainPage.goToTimeInfoPage().onDate(2013, Calendar.JANUARY, 2).atTime(9, 0);
		setActivity(timeInfoPage.getActivity());
		
		timeInfoPage.confirm();
		
		TimeCard timeCard = dataSource.getLastInDate();
		assertPresenceOf(timeCard, timeInfoPage);
	}
	
//	public void testTransitionOfTheTimeCardInfoToMainScreen() {
//		goToTimeInfoPageAndClickConfirmButton();
//		
//		Button buttonEntry = (Button) mainActivity.findViewById(R.id.buttonEntry);
//		
//		Activity currentActivity = getCurrentActivity(monitor);
//
//		assertTrue(currentActivity instanceof MainActivity);
//		assertEquals(mainActivity.getResources().getText(R.string.label_button_out), buttonEntry.getText());
//	}

	private void assertPresenceOf(TimeCard timeCard, TimeInfoPage timeInfoPage) {
		assertNotNull(timeCard);
		assertEquals(timeInfoPage.getInputDate(), timeCard.getDateTime());
		assertEquals(TimeCardType.IN, timeCard.getType());
	}
	
}
