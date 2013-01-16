package com.thoughtworks.twinout;

import java.util.Date;

import com.thoughtworks.twinout.db.DBHelper;
import com.thoughtworks.twinout.db.DateTimeDataSource;

public class InOutRegister {

	private DateTimeDataSource dataSource;
	
	public InOutRegister(DBHelper dbHelper) {
		this.dataSource = new DateTimeDataSource(dbHelper);
	}
	public boolean registerInAt(Date currentDate) {
		return dataSource.save(currentDate);
	}
}
