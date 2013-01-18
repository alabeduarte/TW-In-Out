package com.thoughtworks.twinout;

import java.util.Date;

import com.thoughtworks.twinout.db.DateTimeDataSource;

public class InOutRegister {

	private DateTimeDataSource dataSource;
	
	public InOutRegister(DateTimeDataSource dataSource) {
		this.dataSource = dataSource;
	}
	public boolean registerInAt(Date currentDate) {
		return dataSource.save(currentDate);
	}
	public boolean registerOutAt(Date currentDate) {
		return dataSource.save(currentDate);
	}
}
