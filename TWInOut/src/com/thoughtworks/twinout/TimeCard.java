package com.thoughtworks.twinout;

import java.util.Date;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

public class TimeCard {

	private Long id;
	private Date dateTime;
	private TimeCardType type;

	public TimeCard() {
	}

	public TimeCard(Long id, Date dateTime, TimeCardType type) {
		this.id = id;
		//Removing the miliseconds
		this.dateTime = Util.parse(Util.format(dateTime));
		this.type = type;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}


	public Date getDateTime() {
		return dateTime;
	}

	public TimeCardType getType() {
		return type;
	}

	public void setType(TimeCardType type) {
		this.type = type;
	}

	@Override
	public boolean equals(Object pObject) {
		boolean result = false;

		if (this == pObject) {
			result = true;
		} else if (pObject != null && this.getClass() == pObject.getClass()) {
			TimeCard timeCard = (TimeCard) pObject;
	        return new EqualsBuilder()
	        	.append(this.getId(), timeCard.getId())
	        	.append(this.getDateTime(), timeCard.getDateTime())
	        	.append(this.getType().getCode(), timeCard.getType().getCode())
	            .isEquals();
	    }
	    return result;	
	 }

	@Override
	public int hashCode() {
		return HashCodeBuilder.reflectionHashCode(this);
	}
	
	@Override
	public String toString() {
		
		return "Id -> " + id + "\tDateTime -> " + dateTime + "\tType -> " + type;
	}
}
