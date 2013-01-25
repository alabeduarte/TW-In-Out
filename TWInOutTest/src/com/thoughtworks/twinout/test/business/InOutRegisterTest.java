package com.thoughtworks.twinout.test.business;

import static org.mockito.Matchers.anyObject;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Calendar;
import java.util.Date;

import android.test.AndroidTestCase;

import com.thoughtworks.twinout.InOutRegister;
import com.thoughtworks.twinout.TimeCard;
import com.thoughtworks.twinout.TimeCardType;
import com.thoughtworks.twinout.db.TimeCardDataSource;

public class InOutRegisterTest extends AndroidTestCase {
	
	public void testFirstIn() throws Exception {
		TimeCardDataSource dataSource = createDataSourceMock();
		
		Date currentDate = Calendar.getInstance().getTime();
		InOutRegister register = new InOutRegister(dataSource);
		
		TimeCard timeCard = register.registerInAt(currentDate);
		
		assertNotNull(timeCard);
	}

	public void testFirstOut() throws Exception {
		TimeCardDataSource dataSource = createDataSourceMock();
		
		Date currentDate = Calendar.getInstance().getTime();
		InOutRegister register = new InOutRegister(dataSource);
		
		TimeCard timeCard = register.registerOutAt(currentDate);
		
		assertNotNull(timeCard);
	}
	
	public void testCantSaveDoubleIn() {
		TimeCardDataSource dataSource = createDataSourceMockDoubleInOut(TimeCardType.IN);
		Calendar calendar = Calendar.getInstance();
		Date inDate1 = calendar.getTime();
		
		calendar.add(Calendar.MINUTE, 1);
		Date inDate2 = calendar.getTime();
		
		InOutRegister register = new InOutRegister(dataSource);
		
		TimeCard firstTimeCard = register.registerInAt(inDate1);
		TimeCard secondTimeCard = register.registerInAt(inDate2);
		
		assertNotNull(firstTimeCard);
		assertNull(secondTimeCard);
		
	}

	public void testCantSaveDoubleOut() {
		TimeCardDataSource dataSource = createDataSourceMockDoubleInOut(TimeCardType.OUT);
		Calendar calendar = Calendar.getInstance();
		Date inDate1 = calendar.getTime();
		
		calendar.add(Calendar.MINUTE, 1);
		Date inDate2 = calendar.getTime();
		
		InOutRegister register = new InOutRegister(dataSource);
		
		TimeCard firstTimeCard = register.registerOutAt(inDate1);
		TimeCard secondTimeCard = register.registerOutAt(inDate2);
		
		assertNotNull(firstTimeCard);
		assertNull(secondTimeCard);
	}

	private TimeCardDataSource createDataSourceMock() {
		TimeCardDataSource dataSource = mock(TimeCardDataSource.class);
		when(dataSource.save((TimeCard) anyObject())).thenReturn(new TimeCard());
		return dataSource;
	}

	private TimeCardDataSource createDataSourceMockDoubleInOut(TimeCardType timeCardType) {
		TimeCardDataSource dataSource = mock(TimeCardDataSource.class);
		
		TimeCard timeCard = new TimeCard(1L, new Date(), timeCardType);
		
		when(dataSource.getLastInDate()).thenReturn(null).thenReturn(timeCard);
		when(dataSource.save((TimeCard) anyObject())).thenReturn(new TimeCard());
		return dataSource;
	}
}
