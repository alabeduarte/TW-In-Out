package com.thoughtworks.twinout;

import java.util.Date;

import android.content.Context;

public class InOutRegister {

	public static boolean registerInAt(Context context, Date currentDate) {
		DBHelper dbHelper = new DBHelper(context);
		return dbHelper.save(currentDate);
	}
}
