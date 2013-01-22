package com.thoughtworks.twinout.test.db;

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

	private TimeCardDataSource createDataSourceMock() {
		TimeCardDataSource dataSource = mock(TimeCardDataSource.class);
		when(dataSource.save((Date) anyObject(), (TimeCardType) anyObject())).thenReturn(new TimeCard());
		return dataSource;
	}
}
