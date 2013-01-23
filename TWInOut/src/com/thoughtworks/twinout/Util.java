package com.thoughtworks.twinout;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class Util {

	private static final SimpleDateFormat SIMPLE_DATE_FORMAT = new SimpleDateFormat("yyyyMMdd HHmm", Locale.US);

	public static String format(Date date) {
		SimpleDateFormat formatedDate = SIMPLE_DATE_FORMAT;
		return formatedDate.format(date);
	}

	public static Date parse(String date) {
		Date result = null;
		SimpleDateFormat formatedDate = SIMPLE_DATE_FORMAT;
		try {
			result = formatedDate.parse(date);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		return result;
	}
}
