package com.thoughtworks.twinout.test;

import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TimePicker;

public class TimeInfoPage {

	private Button buttonConfirmation;
	private DatePicker datePicker;
	private TimePicker timePicker;
	
	public TimeInfoPage(Button buttonConfirmation, DatePicker datePicker, TimePicker timePicker) {
		this.buttonConfirmation = buttonConfirmation;
		this.datePicker = datePicker;
		this.timePicker = timePicker;
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
	
	
}
