package com.thoughtworks.twinout.test.db;

import java.util.Calendar;
import java.util.Date;

import android.test.AndroidTestCase;

import com.thoughtworks.twinout.TimeCard;
import com.thoughtworks.twinout.TimeCardType;
import com.thoughtworks.twinout.db.TimeCardDataSource;

public class DateTimeDatasourceTest extends AndroidTestCase{

	private TimeCardDataSource dataSource;
	
	@Override
	protected void setUp() throws Exception {
		super.setUp();
		dataSource = new TimeCardDataSource(getContext());
		dataSource.open();
	}
	
	@Override
	protected void tearDown() throws Exception {
		super.tearDown();
		dataSource.deleteAll();
		dataSource.close();
	}
	
	public void testShouldSaveIn() {
		Date currentDate = Calendar.getInstance().getTime();
		
		TimeCard timeCard = new TimeCard(null, currentDate, TimeCardType.IN);

		timeCard = dataSource.save(timeCard);
		TimeCard timeCardSaved = dataSource.getLastInDate();
		
		assertEquals(timeCardSaved, timeCard);
	}
	
	public void testShouldSaveOut(){
		Date currentDate = Calendar.getInstance().getTime();
		
		TimeCard timeCard = new TimeCard(null, currentDate, TimeCardType.OUT);
		
		timeCard = dataSource.save(timeCard);
		TimeCard timeCardSaved = dataSource.getLastInDate();
		
		assertEquals(timeCardSaved, timeCard);
	}
	
}
