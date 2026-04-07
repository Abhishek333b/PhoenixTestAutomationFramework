package com.api.constant;

public enum Warrenty_Status {
	IN_WARRANTY(1),
	OUT_WARRANTY(2);
	
	private int code;
	
	private Warrenty_Status(int code) {
		this.code=code;
	}
	
	public int getCode() {
		return code;
	}

}
