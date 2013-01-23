package com.thoughtworks.twinout.test;

import java.util.Calendar;
import java.util.Date;

import android.app.Activity;
import android.app.Instrumentation;
import android.content.Intent;
import android.test.ActivityInstrumentationTestCase2;
import android.test.TouchUtils;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TimePicker;

import com.thoughtworks.twinout.MainActivity;
import com.thoughtworks.twinout.R;
import com.thoughtworks.twinout.TimeCard;
import com.thoughtworks.twinout.TimeCardType;
import com.thoughtworks.twinout.TimeInfoActivity;
import com.thoughtworks.twinout.Util;
import com.thoughtworks.twinout.db.TimeCardDataSource;

public class TimeInfoActivityTest extends ActivityInstrumentationTestCase2<TimeInfoActivity> {

	private Instrumentation instrumentation;
	private TimeCardDataSource dataSource;

	@Override
	protected void setUp() throws Exception {
		super.setUp();
		instrumentation = getInstrumentation();
		dataSource = new TimeCardDataSource(instrumentation.getTargetContext());
		dataSource.open();
	}

	@Override
	protected void tearDown() throws Exception {
		super.tearDown();
		dataSource.deleteAll();
		dataSource.close();
	}
	
	public TimeInfoActivityTest(Class<TimeInfoActivity> activityClass) {
		super(activityClass);
	}

	public void testTransitionOfFirstScreenToConfirmationScreen() throws Exception {
		TimeInfoPage timeInfoPage = goToTimeInfoPage();
		assertNotNull(timeInfoPage.getButtonConfirmation());
		assertNotNull(timeInfoPage.getDatePicker());
		assertNotNull(timeInfoPage.getTimePicker());
	}

	public void testShouldAddNewTimeCard() {
		TimeInfoPage timeInfoPage = goToTimeInfoPage();

		timeInfoPage.getDatePicker().init(2013, Calendar.JANUARY, 2, null);
		timeInfoPage.getTimePicker().setCurrentHour(9);
		timeInfoPage.getTimePicker().setCurrentMinute(0);

		TouchUtils.clickView(this, timeInfoPage.getButtonConfirmation());
		instrumentation.waitForIdleSync();

		TimeCard timeCard = dataSource.getLastInDate();

		assertEquals(timeInfoPage.getInputDate(), timeCard.getDateTime());
		assertEquals(TimeCardType.IN, timeCard.getType());
	}

	private TimeInfoPage goToTimeInfoPage() {
		Instrumentation.ActivityMonitor monitor = createMonitor(MainActivity.class);
		startActivitySync();

		Activity currentActivity = getCurrentActivity(monitor);
		assertNotNull(currentActivity);

		View currentView = currentActivity.findViewById(R.id.buttonIn);

		monitor = changeMonitor(monitor, TimeInfoActivity.class);

		TouchUtils.clickView(this, currentView);
		instrumentation.waitForIdleSync();

		currentActivity = getCurrentActivity(monitor);

		Button confirmButton = (Button) currentActivity.findViewById(R.id.buttonConfirm);
		DatePicker datePicker = (DatePicker) currentActivity.findViewById(R.id.datePicker);
		TimePicker timePicker = (TimePicker) currentActivity.findViewById(R.id.timePicker);

		currentActivity.finish();
		return new TimeInfoPage(confirmButton, datePicker, timePicker);
	}

	private Activity getCurrentActivity(Instrumentation.ActivityMonitor monitor) {
		return getInstrumentation().waitForMonitorWithTimeout(monitor, 5);
	}

	private void startActivitySync() {
		Intent intent = new Intent(Intent.ACTION_MAIN);
		intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		intent.setClassName(instrumentation.getTargetContext(), MainActivity.class.getName());
		instrumentation.startActivitySync(intent);
	}

	private Instrumentation.ActivityMonitor createMonitor(
			Class<? extends Activity> clazz) {
		return instrumentation.addMonitor(clazz.getName(), null, false);
	}

	private void removeMonitor(Instrumentation.ActivityMonitor monitor) {
		instrumentation.removeMonitor(monitor);
	}

	private Instrumentation.ActivityMonitor changeMonitor(Instrumentation.ActivityMonitor monitor, Class<? extends Activity> clazz) {
		removeMonitor(monitor);
		return createMonitor(clazz);
	}

}
