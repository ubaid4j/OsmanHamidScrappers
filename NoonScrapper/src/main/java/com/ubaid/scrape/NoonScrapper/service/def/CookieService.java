package com.ubaid.scrape.NoonScrapper.service.def;

import java.util.Map;

public interface CookieService
{
	public void setCookie();
	public Map<String, String> getHeader();
	public void setRefferal(String referal);
	public void setArabic();
	public void setEnglish();
	public void refreshCookie();
}
