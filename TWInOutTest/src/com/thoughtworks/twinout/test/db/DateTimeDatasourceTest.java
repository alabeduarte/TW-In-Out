package com.thoughtworks.twinout.test.db;

import java.util.Calendar;
import java.util.Date;

import android.test.AndroidTestCase;

import com.thoughtworks.twinout.TimeCard;
import com.thoughtworks.twinout.TimeCardType;
import com.thoughtworks.twinout.db.TimeCardDataSource;

public class DateTimeDatasourceTest extends AndroidTestCase{

	TimeCardDataSource dataSource;
	
	@Override
	protected void setUp() throws Exception {
		super.setUp();
		dataSource = new TimeCardDataSource(getContext());
		dataSource.open();
	}
	
	@Override
	protected void tearDown() throws Exception {
		super.tearDown();
		dataSource.close();
	}
	
	public void testShouldSaveIn() {
		Date currentDate = Calendar.getInstance().getTime();
		
		TimeCard timeCard = dataSource.save(currentDate, TimeCardType.IN);
		TimeCard timeCardSaved = dataSource.getLastInDate();
		
		assertTrue(timeCardSaved.equals(timeCard));
	}
	
}
