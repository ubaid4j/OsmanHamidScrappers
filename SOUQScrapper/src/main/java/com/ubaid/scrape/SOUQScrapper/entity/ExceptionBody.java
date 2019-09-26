package com.ubaid.scrape.SOUQScrapper.entity;

public class ExceptionBody
{
	private String detail;
	private String message;
	private String type;
	
	public String getDetail() {
		return detail;
	}
	public void setDetail(String detail) {
		this.detail = detail;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public ExceptionBody(String detail, String message, String type) {
		super();
		this.detail = detail;
		this.message = message;
		this.type = type;
	}
	public ExceptionBody() {
		super();
	}
	
	@Override
	public String toString() {
		return "ExceptionBody [detail=" + detail + ", message=" + message + ", type=" + type + "]";
	}
}
