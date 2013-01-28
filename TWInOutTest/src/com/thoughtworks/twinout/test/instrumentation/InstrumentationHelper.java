package com.thoughtworks.twinout.test.instrumentation;

import android.app.Activity;
import android.app.Instrumentation;
import android.content.Intent;

public class InstrumentationHelper {
	
	private Instrumentation instrumentation;
	
	public InstrumentationHelper(Instrumentation instrumentation) {
		this.instrumentation = instrumentation;
	}
	
	public Activity createActivityFor(Class<? extends Activity> clazz) {
		Instrumentation.ActivityMonitor monitor = this.createMonitor(clazz);
		this.startActivitySync(clazz);
		return instrumentation.waitForMonitorWithTimeout(monitor, 1000);
	}
	
	public Activity getActivityFor(Class<? extends Activity> clazz) {
		Instrumentation.ActivityMonitor monitor = this.createMonitor(clazz);
		return instrumentation.waitForMonitorWithTimeout(monitor, 1000);
	}

	private Instrumentation.ActivityMonitor createMonitor(Class<? extends Activity> clazz) {
		return instrumentation.addMonitor(clazz.getName(), null, false);
	}
	
	private void startActivitySync(Class<? extends Activity> clazz) {
		Intent intent = new Intent(Intent.ACTION_MAIN);
		intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		intent.setClassName(instrumentation.getTargetContext(), clazz.getName());
		instrumentation.startActivitySync(intent);
	}
	
}
