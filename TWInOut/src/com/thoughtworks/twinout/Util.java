package com.thoughtworks.twinout;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class Util {

	private static final SimpleDateFormat SIMPLE_DATE_FORMAT = new SimpleDateFormat("yyyyMMdd HH:mm:ss", Locale.US);

	public static String format(Date date) {
		SimpleDateFormat formatedDate = SIMPLE_DATE_FORMAT;
		return formatedDate.format(date);
	}

	public static Date parse(String date) throws ParseException {
		SimpleDateFormat formatedDate = SIMPLE_DATE_FORMAT;
		return formatedDate.parse(date);
	}
}
