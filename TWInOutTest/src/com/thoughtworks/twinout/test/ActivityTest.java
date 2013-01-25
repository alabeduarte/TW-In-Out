package com.thoughtworks.twinout.test;

import android.app.Activity;
import android.app.Instrumentation;
import android.content.Context;
import android.content.Intent;
import android.test.ActivityInstrumentationTestCase2;

import com.thoughtworks.twinout.MainActivity;

public class ActivityTest<T extends Activity> extends ActivityInstrumentationTestCase2<T> {

	private Instrumentation instrumentation;

	@Override
	protected void setUp() throws Exception {
		super.setUp();
		instrumentation = getInstrumentation();
	}
	
	public ActivityTest(Class<T> activityClass) {
		super(activityClass);
	}

	protected Activity getCurrentActivity(Instrumentation.ActivityMonitor monitor) {
		return getInstrumentation().waitForMonitorWithTimeout(monitor, 1000);
	}

	protected void startActivitySync() {
		Intent intent = new Intent(Intent.ACTION_MAIN);
		intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		intent.setClassName(instrumentation.getTargetContext(), MainActivity.class.getName());
		instrumentation.startActivitySync(intent);
	}

	protected Instrumentation.ActivityMonitor createMonitor(
			Class<? extends Activity> clazz) {
		return instrumentation.addMonitor(clazz.getName(), null, false);
	}

	protected void removeMonitor(Instrumentation.ActivityMonitor monitor) {
		instrumentation.removeMonitor(monitor);
	}

	protected Instrumentation.ActivityMonitor changeMonitor(Instrumentation.ActivityMonitor monitor, Class<? extends Activity> clazz) {
		removeMonitor(monitor);
		return createMonitor(clazz);
	}

	protected Context getTargetContext() {
		return instrumentation.getTargetContext();
	}

}
