package com.thoughtworks.twinout.test.activities.pages;

import java.util.Date;

import android.widget.DatePicker;
import android.widget.TimePicker;

import com.jayway.android.robotium.solo.Solo;
import com.thoughtworks.twinout.R;
import com.thoughtworks.twinout.TimeInfoActivity;
import com.thoughtworks.twinout.ViewHelper;

public class TimeInfoPage {

	private final Solo solo;
	
	public TimeInfoPage(Solo solo) {
		this.solo = solo;
	}
	
	public DatePicker getDatePicker() {
		TimeInfoActivity activity = (TimeInfoActivity) this.solo.getCurrentActivity();
		return (DatePicker) activity.findViewById(R.id.datePicker);
	}

	public TimePicker getTimePicker() {
		TimeInfoActivity activity = (TimeInfoActivity) this.solo.getCurrentActivity();
		return (TimePicker) activity.findViewById(R.id.timePicker);
	}
	
	public TimeInfoPage onDate(int year, int monthOfYear, int dayOfYear) {
		this.solo.setDatePicker(this.getDatePicker(), year, monthOfYear, dayOfYear);
		return this;
	}
	
	public TimeInfoPage atTime(int hour, int minute) {
		this.solo.setTimePicker(this.getTimePicker(), hour, minute);
		return this;
	}
	
	public Date getInputDate() {
		return ViewHelper.getInputDate(getDatePicker(), getTimePicker());
	}
	
	public void confirm() {
		this.solo.clickOnText(this.solo.getString(R.string.confirm));
	}

}
