package com.thoughtworks.twinout.test.pages;

import java.util.Date;

import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TimePicker;

import com.thoughtworks.twinout.R;
import com.thoughtworks.twinout.TimeInfoActivity;
import com.thoughtworks.twinout.ViewHelper;

public class TimeInfoPage {

	private TimeInfoActivity activity;
	private Button buttonConfirmation;
	private DatePicker datePicker;
	private TimePicker timePicker;
	
	public TimeInfoPage(TimeInfoActivity activity) {
		this.activity = activity;
		buttonConfirmation = (Button) activity.findViewById(R.id.buttonConfirm);
		datePicker = (DatePicker) activity.findViewById(R.id.datePicker);
		timePicker = (TimePicker) activity.findViewById(R.id.timePicker);
	}
	
	public Button getButtonConfirmation() {
		return buttonConfirmation;
	}

	public DatePicker getDatePicker() {
		return datePicker;
	}

	public TimePicker getTimePicker() {
		return timePicker;
	}
	
	public TimeInfoPage onDate(int year, int monthOfYear, int dayOfYear) {
		this.datePicker.init(year, monthOfYear, dayOfYear, null);
		return this;
	}
	
	public TimeInfoPage atTime(int hour, int minute) {
		this.timePicker.setCurrentHour(hour);
		this.timePicker.setCurrentMinute(minute);
		return this;
	}
	
	public Date getInputDate() {
		return ViewHelper.getInputDate(datePicker, timePicker);
	}
	
	public void confirm() {
		this.buttonConfirmation.performClick();
	}

	public TimeInfoActivity getActivity() {
		return activity;
	}
	
}
