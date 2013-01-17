package com.thoughtworks.twinout.test.db;

import java.util.Calendar;
import java.util.Date;

import android.content.Context;
import android.test.AndroidTestCase;

import com.thoughtworks.twinout.db.DBHelper;
import com.thoughtworks.twinout.db.DateTimeDataSource;

public class DateTimeDatasourceTest extends AndroidTestCase{

	DBHelper dbHelper;
	
	@Override
	protected void setUp() throws Exception {
		super.setUp();
		dbHelper = new DBHelper(getContext());
		dbHelper.open();
	}
	
	@Override
	protected void tearDown() throws Exception {
		super.tearDown();
		dbHelper.close();
	}
	
	public void testShouldSave() {
		Context context = getContext();
		DBHelper dbHelper = new DBHelper(context);
		DateTimeDataSource dataSource = new DateTimeDataSource(dbHelper);
		Date currentDate = Calendar.getInstance().getTime();
		
		boolean success = dataSource.save(currentDate);
		Date inDate = dataSource.getLastInDate();
		
		assertTrue(success);
		assertEquals(currentDate.toString(), inDate.toString());
	}
	
}
