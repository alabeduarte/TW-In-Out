package com.thoughtworks.twinout.test.db;

import java.util.Calendar;
import java.util.Date;

import android.test.AndroidTestCase;

import com.thoughtworks.twinout.DBHelper;
import com.thoughtworks.twinout.InOutRegister;

public class InOutRegisterTest extends AndroidTestCase {
	
	public void testINRegister() throws Exception {
		DBHelper dbHelper = new DBHelper(getContext());
		Date currentDate = Calendar.getInstance().getTime();
		boolean success = InOutRegister.registerInAt(getContext(), currentDate);
		Date inDate = dbHelper.getLastInDate();
		assertTrue(success);
		assertEquals(currentDate.toString(), inDate.toString());
	}
}
