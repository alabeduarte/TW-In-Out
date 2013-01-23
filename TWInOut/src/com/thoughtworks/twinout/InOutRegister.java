package com.thoughtworks.twinout;

import java.util.Date;

import com.thoughtworks.twinout.db.TimeCardDataSource;

public class InOutRegister {

	private TimeCardDataSource dataSource;
	
	public InOutRegister(TimeCardDataSource dataSource) {
		this.dataSource = dataSource;
	}
	public TimeCard registerInAt(Date currentDate) {
		return register(currentDate, TimeCardType.IN, TimeCardType.OUT);	}
	
	public TimeCard registerOutAt(Date currentDate) {
		return register(currentDate, TimeCardType.OUT, TimeCardType.IN);
	}
	
	private TimeCard register(Date currentDate, TimeCardType typeToSave, TimeCardType typeToCompare) {
		TimeCard result = null;
		
		TimeCard lastCard = dataSource.getLastInDate();
		if (lastCard == null || typeToCompare.equals(lastCard.getType())) {
			TimeCard timeCard = new TimeCard(null, currentDate, typeToSave);
			result = dataSource.save(timeCard);
		}
		return result;
	}
}
