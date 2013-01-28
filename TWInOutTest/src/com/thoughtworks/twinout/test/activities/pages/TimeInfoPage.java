package com.thoughtworks.twinout.test.activities.pages;

import java.util.Date;

import android.widget.DatePicker;
import android.widget.TimePicker;

import com.jayway.android.robotium.solo.Solo;
import com.thoughtworks.twinout.R;
import com.thoughtworks.twinout.TimeInfoActivity;
import com.thoughtworks.twinout.ViewHelper;

public class TimeInfoPage {

	private DatePicker datePicker;
	private TimePicker timePicker;
	
	private final Solo solo;
	
	public TimeInfoPage(Solo solo) {
		this.solo = solo;
		TimeInfoActivity activity = (TimeInfoActivity) this.solo.getCurrentActivity();
		datePicker = (DatePicker) activity.findViewById(R.id.datePicker);
		timePicker = (TimePicker) activity.findViewById(R.id.timePicker);
	}
	
	public DatePicker getDatePicker() {
		return datePicker;
	}

	public TimePicker getTimePicker() {
		return timePicker;
	}
	
	public TimeInfoPage onDate(int year, int monthOfYear, int dayOfYear) {
		this.solo.setDatePicker(this.datePicker, year, monthOfYear, dayOfYear);
		return this;
	}
	
	public TimeInfoPage atTime(int hour, int minute) {
		this.solo.setTimePicker(this.timePicker, hour, minute);
		return this;
	}
	
	public Date getInputDate() {
		return ViewHelper.getInputDate(datePicker, timePicker);
	}
	
	public void confirm() {
		this.solo.clickOnText(this.solo.getString(R.string.confirm));
	}

}
