package com.thoughtworks.twinout.test.db;

import java.util.Calendar;
import java.util.Date;

import android.test.AndroidTestCase;

import com.thoughtworks.twinout.db.DateTimeDataSource;

public class DateTimeDatasourceTest extends AndroidTestCase{

	DateTimeDataSource dataSource;
	
	@Override
	protected void setUp() throws Exception {
		super.setUp();
		dataSource = new DateTimeDataSource(getContext());
		dataSource.open();
	}
	
	@Override
	protected void tearDown() throws Exception {
		super.tearDown();
		dataSource.close();
	}
	
	public void testShouldSave() {
		Date currentDate = Calendar.getInstance().getTime();
		
		dataSource.save(currentDate);
		Date inDate = dataSource.getLastInDate();
		
		assertEquals(currentDate.toString(), inDate.toString());
	}
	
}
