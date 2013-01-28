package com.thoughtworks.twinout.test.pages;

import android.app.Instrumentation;
import android.widget.Button;

import com.thoughtworks.twinout.MainActivity;
import com.thoughtworks.twinout.R;
import com.thoughtworks.twinout.TimeInfoActivity;
import com.thoughtworks.twinout.test.instrumentation.InstrumentationHelper;

public class MainPage {

	private InstrumentationHelper instrumentationHelper;
	private MainActivity activity;
	
	public static MainPage create(Instrumentation instrumentation) {
		return new MainPage(new InstrumentationHelper(instrumentation));
	}
	
	private MainPage(InstrumentationHelper instrumentationHelper) {
		this.instrumentationHelper = instrumentationHelper;
		this.activity = (MainActivity) this.instrumentationHelper.createActivityFor(MainActivity.class);
	}

	public MainActivity getActivity() {
		return activity;
	}

	public TimeInfoPage goToTimeInfoPage() {
		final Button buttonEntry = (Button) activity.findViewById(R.id.buttonEntry);
		click(buttonEntry);

		TimeInfoActivity timeInfoActivity = (TimeInfoActivity) this.instrumentationHelper.getActivityFor(TimeInfoActivity.class);
		return new TimeInfoPage(timeInfoActivity);
	}
	
	private void click(final Button buttonIn) {
		activity.runOnUiThread(new Runnable() {
			@Override public void run() {
				buttonIn.performClick();
			}
		});
	}
	
}
