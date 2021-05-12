package com.bbjob.dto;

public class Result {
	
	public Result() {
		this.code=200;
	}
	public Result(Object data) {
		this.code=200;
		this.data=data;
	}
	public Result(int code,String message) {
		this.code=code;
		this.message=message;
	}
	private int code;
	private String message;
	private Object data;
	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public Object getData() {
		return data;
	}
	public void setData(Object data) {
		this.data = data;
	}
}
