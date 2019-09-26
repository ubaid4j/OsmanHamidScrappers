package com.ubaid.scrape.NoonScrapper.entity;

public class EnArUrls
{
	private String enURL;
	private String arURL;
	public String getEnURL() {
		return enURL;
	}
	public void setEnURL(String enURL) {
		this.enURL = enURL;
	}
	public String getArURL() {
		return arURL;
	}
	public void setArURL(String arURL) {
		this.arURL = arURL;
	}
	public EnArUrls(String enURL, String arURL) {
		super();
		this.enURL = enURL;
		this.arURL = arURL;
	}
	public EnArUrls() {
		super();
	}
}
