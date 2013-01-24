package com.thoughtworks.twinout;

import java.util.Date;

import org.apache.commons.lang.StringUtils;

import android.widget.DatePicker;
import android.widget.TimePicker;

public class ViewHelper {

	public static Date getInputDate(DatePicker datePicker, TimePicker timePicker) {
		return Parser.parse(datePicker.getYear()
				+ completeWith0Left(datePicker.getMonth() + 1)
				+ completeWith0Left(datePicker.getDayOfMonth()) + " "
				+ completeWith0Left(timePicker.getCurrentHour())
				+ completeWith0Left(timePicker.getCurrentMinute()));
	}
	
	private static String completeWith0Left(Integer value) {
		return StringUtils.leftPad(value.toString() , 2, '0');
	}
}
