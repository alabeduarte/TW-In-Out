package com.thoughtworks.twinout.test.db;

import static org.mockito.Matchers.anyObject;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Calendar;
import java.util.Date;

import android.test.AndroidTestCase;

import com.thoughtworks.twinout.InOutRegister;
import com.thoughtworks.twinout.db.DateTimeDataSource;

public class InOutRegisterTest extends AndroidTestCase {
	
	public void testINRegister() throws Exception {
		
		DateTimeDataSource dataSource = mock(DateTimeDataSource.class);
		Date anyDate = (Date) anyObject();
		when(dataSource.save(anyDate)).thenReturn(true);
		
		Date currentDate = Calendar.getInstance().getTime();
		InOutRegister register = new InOutRegister(dataSource);
		
		boolean success = register.registerInAt(currentDate);
		
		assertTrue(success);
	}
	
}
