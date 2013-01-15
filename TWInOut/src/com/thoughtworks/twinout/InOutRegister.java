package com.thoughtworks.twinout;

import java.util.Date;

public class InOutRegister {

	public static boolean registerInAt(Date currentDate) {
		DBHelper.save(currentDate);
		return true;
	}
}
