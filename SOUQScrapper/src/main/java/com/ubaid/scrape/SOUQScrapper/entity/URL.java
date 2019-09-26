package com.ubaid.scrape.SOUQScrapper.entity;

public class URL
{
	public String host;
	public String language;
	public int page;
	public String category;
	public String internalPart;
	public String urlWithoutPage;
	
	
	
	public URL(String urlWithoutPage) {
		super();
		this.urlWithoutPage = urlWithoutPage;
	}
	
	public String getUrlWithoutPage() {
		return urlWithoutPage;
	}
	public void setUrlWithoutPage(String urlWithoutPage) {
		this.urlWithoutPage = urlWithoutPage;
	}
	public String getHost() {
		return host;
	}
	public void setHost(String host) {
		this.host = host;
	}
	public String getLanguage() {
		return language;
	}
	public void setLanguage(String language) {
		this.language = language;
	}
	public int getPage() {
		return page;
	}
	public void setPage(int page) {
		this.page = page;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getInternalPart() {
		return internalPart;
	}
	public void setInternalPart(String internalPart) {
		this.internalPart = internalPart;
	}
	public URL(String host, String language, int page, String category, String internalPart) {
		super();
		this.host = host;
		this.language = language;
		this.page = page;
		this.category = category;
		this.internalPart = internalPart;
	}
	public URL() {
		super();
	}
	
	@Override
	public String toString() {
		return String.format("%s/%s/%s&page=%d", getHost(), getLanguage(), getInternalPart(), getPage());
	}
}
