package com.thoughtworks.twinout.test;

import java.util.Calendar;

import android.app.Activity;
import android.app.Instrumentation;
import android.widget.Button;

import com.thoughtworks.twinout.MainActivity;
import com.thoughtworks.twinout.R;
import com.thoughtworks.twinout.TimeCard;
import com.thoughtworks.twinout.TimeCardType;
import com.thoughtworks.twinout.TimeInfoActivity;
import com.thoughtworks.twinout.db.TimeCardDataSource;

public class TimeInfoActivityTest extends ActivityTest<TimeInfoActivity> {

	private TimeCardDataSource dataSource;
	Activity mainActivity;
	Instrumentation.ActivityMonitor monitor;

	@Override
	protected void setUp() throws Exception {
		super.setUp();
		setToMainActivity();
		dataSource = new TimeCardDataSource(getTargetContext());
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

	public void testTransitionOfFirstScreenToConfirmationScreen() throws Exception {
		TimeInfoPage timeInfoPage = goToTimeInfoPage();
		assertNotNull(timeInfoPage.getButtonConfirmation());
		assertNotNull(timeInfoPage.getDatePicker());
		assertNotNull(timeInfoPage.getTimePicker());
	}

	public void testShouldAddNewTimeCard() {
		TimeInfoPage timeInfoPage = goToTimeInfoPageAndClickConfirmButton();
		
		TimeCard timeCard = dataSource.getLastInDate();

		assertNotNull(timeCard);
		assertEquals(timeInfoPage.getInputDate(), timeCard.getDateTime());
		assertEquals(TimeCardType.IN, timeCard.getType());
	}
	
	public void testShouldReturnToMainScreenWithOutButton() {
		goToTimeInfoPageAndClickConfirmButton();
		
		Button buttonEntry = (Button) mainActivity.findViewById(R.id.buttonEntry);
		assertEquals(mainActivity.getResources().getText(R.string.label_button_out), buttonEntry.getText());
	}

	private TimeInfoPage goToTimeInfoPageAndClickConfirmButton() {
		TimeInfoPage timeInfoPage = goToTimeInfoPage();

		timeInfoPage.getDatePicker().init(2013, Calendar.JANUARY, 2, null);
		timeInfoPage.getTimePicker().setCurrentHour(9);
		timeInfoPage.getTimePicker().setCurrentMinute(0);

		timeInfoPage.getButtonConfirmation().performClick();
		return timeInfoPage;
	}

	private void setToMainActivity() {
		if (mainActivity == null) {
			monitor = createMonitor(MainActivity.class);
			startActivitySync();
			mainActivity = getCurrentActivity(monitor);
		}
		setActivity(mainActivity);
	}
	
	private TimeInfoPage goToTimeInfoPage() {
		assertNotNull(mainActivity);

		final Button buttonEntry = (Button) mainActivity.findViewById(R.id.buttonEntry);

		monitor = changeMonitor(monitor, TimeInfoActivity.class);

		click(mainActivity, buttonEntry);

		Activity timeInfoActivity = getCurrentActivity(monitor);
		timeInfoActivity.finish();
		return new TimeInfoPage(timeInfoActivity);
	}

	private void click(Activity activity, final Button buttonIn) {
		activity.runOnUiThread(new Runnable() {
			
			@Override
			public void run() {
				assertTrue(buttonIn.performClick());
			}
		});
	}
}
