package com.thoughtworks.twinout.test.instrumentation;

import android.app.Activity;
import android.app.Instrumentation;
import android.content.Intent;

import com.thoughtworks.twinout.MainActivity;

public class InstrumentationHelper {
	
	private Instrumentation instrumentation;
	
	public InstrumentationHelper(Instrumentation instrumentation) {
		this.instrumentation = instrumentation;
	}

	public Instrumentation.ActivityMonitor createMonitor(Class<? extends Activity> clazz) {
		return instrumentation.addMonitor(clazz.getName(), null, false);
	}
	
	public void startActivitySync() {
		Intent intent = new Intent(Intent.ACTION_MAIN);
		intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		intent.setClassName(instrumentation.getTargetContext(), MainActivity.class.getName());
		instrumentation.startActivitySync(intent);
	}
	
	public Activity getCurrentActivity(Instrumentation.ActivityMonitor monitor) {
		return instrumentation.waitForMonitorWithTimeout(monitor, 1000);
	}
	
	public Instrumentation.ActivityMonitor changeMonitor(Instrumentation.ActivityMonitor monitor, Class<? extends Activity> clazz) {
		removeMonitor(monitor);
		return createMonitor(clazz);
	}
	
	private void removeMonitor(Instrumentation.ActivityMonitor monitor) {
		instrumentation.removeMonitor(monitor);
	}

}
