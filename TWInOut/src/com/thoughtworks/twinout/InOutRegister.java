package com.thoughtworks.twinout;

import java.util.Date;

import com.thoughtworks.twinout.db.TimeCardDataSource;

public class InOutRegister {

	private TimeCardDataSource dataSource;
	
	public InOutRegister(TimeCardDataSource dataSource) {
		this.dataSource = dataSource;
	}
	public TimeCard registerInAt(Date currentDate) {
		return dataSource.save(currentDate, TimeCardType.IN);
	}
	public TimeCard registerOutAt(Date currentDate) {
		return dataSource.save(currentDate, TimeCardType.OUT);
	}
}
