package com.thoughtworks.twinout.test.db;

import java.util.Calendar;
import java.util.Date;

import android.content.Context;
import android.test.AndroidTestCase;

import com.thoughtworks.twinout.InOutRegister;
import com.thoughtworks.twinout.db.DBHelper;
import com.thoughtworks.twinout.db.DateTimeDataSource;

public class InOutRegisterTest extends AndroidTestCase {
	
	public void testINRegister() throws Exception {
		Context context = getContext();
		DBHelper dbHelper = new DBHelper(context);
		DateTimeDataSource dataSource = new DateTimeDataSource(dbHelper);

		Date currentDate = Calendar.getInstance().getTime();
		InOutRegister register = new InOutRegister(new DBHelper(context));
		
		boolean success = register.registerInAt(currentDate);
		Date inDate = dataSource.getLastInDate();
		
		assertTrue(success);
		assertEquals(currentDate.toString(), inDate.toString());
	}
}
