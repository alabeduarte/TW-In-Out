package com.thoughtworks.twinout.test.db;

import java.util.Calendar;
import java.util.Date;

import junit.framework.TestCase;

import com.thoughtworks.twinout.DBHelper;
import com.thoughtworks.twinout.InOutRegister;

public class InOutRegisterTest extends TestCase {
	
	public void testINRegister() throws Exception {
		Date currentDate = Calendar.getInstance().getTime();
		boolean success = InOutRegister.registerInAt(currentDate);
		Date inDate = DBHelper.getLastInDate();
		assertTrue(success);
		assertEquals(currentDate, inDate);
	}
}
