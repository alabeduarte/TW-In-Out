package com.thoughtworks.twinout;

public enum TimeCardType {

	IN(0), OUT(1);
	
	private Integer code;
	
	private TimeCardType(Integer code) {
		this.code = code;
	}
	
	public Integer getCode() {
		return code;
	}

	public static TimeCardType getType(int type) {
		TimeCardType result = null;
		for (TimeCardType timeCardType : values()) {
			if(type == timeCardType.getCode()) {
				result = timeCardType;
			}
		}
		return result;
	}
}
