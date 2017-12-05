package com.library.dto;

public class DataDto {
	private String message;
	private int statusCode;
	private Object value;
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public int getStatusCode() {
		return statusCode;
	}
	public void setStatusCode(int statusCode) {
		this.statusCode = statusCode;
	}
	public Object getValue() {
		return value;
	}
	public void setValue(Object value) {
		this.value = value;
	}
	@Override
	public String toString() {
		return "DataDto [message=" + message + ", statusCode=" + statusCode + ", value=" + value + "]";
	}
	
	
	
}
