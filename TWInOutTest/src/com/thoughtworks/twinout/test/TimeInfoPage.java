package com.thoughtworks.twinout.test;

import java.util.Date;

import android.app.Activity;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TimePicker;

import com.thoughtworks.twinout.R;
import com.thoughtworks.twinout.ViewHelper;

public class TimeInfoPage {

	private Button buttonConfirmation;
	private DatePicker datePicker;
	private TimePicker timePicker;
	
	public TimeInfoPage(Activity activity) {
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
	
	public Date getInputDate() {
		return ViewHelper.getInputDate(datePicker, timePicker);
	}

}
