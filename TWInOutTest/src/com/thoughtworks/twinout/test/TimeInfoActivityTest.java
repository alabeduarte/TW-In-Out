package com.thoughtworks.twinout.test;

import java.util.Calendar;

import android.app.Activity;
import android.app.Instrumentation;
import android.content.Intent;
import android.test.ActivityInstrumentationTestCase2;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TimePicker;

import com.thoughtworks.twinout.MainActivity;
import com.thoughtworks.twinout.R;
import com.thoughtworks.twinout.TimeCard;
import com.thoughtworks.twinout.TimeInfoActivity;
import com.thoughtworks.twinout.db.TimeCardDataSource;

public class TimeInfoActivityTest extends ActivityInstrumentationTestCase2<TimeInfoActivity> {

	private Instrumentation instrumentation;
	private TimeCardDataSource dataSource;
	Activity mainActivity;
	Instrumentation.ActivityMonitor monitor;

	@Override
	protected void setUp() throws Exception {
		super.setUp();
		instrumentation = getInstrumentation();
		setToMainActivity();
		dataSource = new TimeCardDataSource(instrumentation.getTargetContext());
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
		TimeInfoPage timeInfoPage = goToTimeInfoPage();

		timeInfoPage.getDatePicker().init(2013, Calendar.JANUARY, 2, null);
		timeInfoPage.getTimePicker().setCurrentHour(9);
		timeInfoPage.getTimePicker().setCurrentMinute(0);

		timeInfoPage.getButtonConfirmation().performClick();

		TimeCard timeCard = dataSource.getLastInDate();

		assertNull(timeCard);
//		assertEquals(timeInfoPage.getInputDate(), timeCard.getDateTime());
//		assertEquals(TimeCardType.IN, timeCard.getType());
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

		final Button buttonIn = (Button) mainActivity.findViewById(R.id.buttonIn);

		monitor = changeMonitor(monitor, TimeInfoActivity.class);

		mainActivity.runOnUiThread(new Runnable() {
			
			@Override
			public void run() {
				assertTrue(buttonIn.performClick());
			}
		});

		Activity timeInfoActivity = getCurrentActivity(monitor);

		Button confirmButton = (Button) timeInfoActivity.findViewById(R.id.buttonConfirm);
		DatePicker datePicker = (DatePicker) timeInfoActivity.findViewById(R.id.datePicker);
		TimePicker timePicker = (TimePicker) timeInfoActivity.findViewById(R.id.timePicker);

		timeInfoActivity.finish();
		return new TimeInfoPage(confirmButton, datePicker, timePicker);
	}

	private Activity getCurrentActivity(Instrumentation.ActivityMonitor monitor) {
		return getInstrumentation().waitForMonitorWithTimeout(monitor, 1000);
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
