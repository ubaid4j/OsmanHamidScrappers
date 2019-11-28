package com.ubaid.scrape.NoonScrapper.service;


import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.jsoup.Jsoup;
import org.jsoup.Connection.Method;
import org.jsoup.Connection.Response;
import org.springframework.stereotype.Service;

import com.ubaid.scrape.NoonScrapper.service.def.CookieService;

@Service
public class CookieServiceImp implements CookieService
{

	public static String nguest = null;
	private String refferel = null;
	private Map<String, String> cookies;
	private String lang = "en";
	private String loc = "en-sa";
	
	@Override
	public void setCookie() {
		_setCookie();
	}

	@Override
	public void setArabic()
	{
		lang = "ar";
		loc = "ar-sa";
	}
	
	@Override
	public void setEnglish()
	{
		lang = "en";
		loc = "en-sa";
	}
	
	
	
	@Override
	public Map<String, String> getHeader() {
		Map<String, String> cookies = getCookies();
		String _cookie = "next-i18next=" + lang + "; _gcl_au=1.1.1263041777.1570800986; _scid=c4128bd6-7044-4207-a4c1-abdc484df0ad; nguest=";
		_cookie += cookies.get("nguest");
		_cookie += "; _sctr=1|1570734000000; __zlcmid=uiibTxXrHcCCwF";
		
		Map<String, String> headers = new HashMap<String, String>();
		headers.put("authority", "www.noon.com");
		headers.put("path", "/_svc/catalog/api/search");
		headers.put("scheme", "https");
		headers.put("accept", "application/json, text/plain, */*");
		headers.put("accept-encoding", "gzip, deflate, br");
		headers.put("accept-language", "en-US,en;q=0.9,ur;q=0.8,lb;q=0.7,ar;q=0.6");
		headers.put("cache-control", "no-cache, max-age=0, must-revalidate, no-store");
		headers.put("content-lenght", "171");
		headers.put("content-type", "application/json");
		headers.put("cookie", _cookie);
		headers.put("pragma", "no-cache");
		headers.put("referer", "https://www.noon.com/saudi-" + lang + "/" + refferel);
		headers.put("sec-fetch-mode", "cors");
		headers.put("sec-fetch-site", "same-origin");
		headers.put("user-agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/77.0.3865.90 Safari/537.36");
		headers.put("x-cms", "v2");
		headers.put("x-content", "desktop");
		headers.put("x-locale", loc);
		headers.put("x-platform", "web");


		return headers;
	}

	@Override
	public void setRefferal(String referal) {
		refferel = referal;
	}
	
	private void _setCookie()
	{
		Map<String, String> nGuest = getCookies();
		if(nGuest != null)
		{
			nguest = nGuest.get("nguest");
		}
	}
	
	@Override
	public void refreshCookie()
	{
		nguest = null;
		cookies = null;
		_setCookie();
	}
	
	private Map<String, String> getCookies()
	{
		if (cookies != null)
			return cookies;
		
		try
		{
			
			Map<String, String> headers = new HashMap<String, String>();
			headers.put("authority", "www.noon.com");
			headers.put("path", "/_svc/cart-v1/cart");
			headers.put("scheme", "https");
			headers.put("accept", "application/json, text/plain, */*");
			headers.put("accept-encoding", "gzip, deflate, br");
			headers.put("accept-language", "en-US,en;q=0.9,ur;q=0.8,lb;q=0.7,ar;q=0.6");
			headers.put("cache-control", "no-cache, max-age=0, must-revalidate, no-store");
			headers.put("cookie", "next-i18next=en; _gcl_au=1.1.1263041777.1570800986; _scid=c4128bd6-7044-4207-a4c1-abdc484df0ad");
			headers.put("pragma", "no-cache");
			headers.put("referer", "https://www.noon.com/saudi-en/" + refferel);
			headers.put("sec-fetch-mode", "cors");
			headers.put("sec-fetch-site", "same-origin");
			headers.put("user-agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/77.0.3865.90 Safari/537.36");
			headers.put("x-cms", "v2");
			headers.put("x-content", "desktop");
			headers.put("x-locale", "en-sa");
			headers.put("x-platform", "web");
			
			
			Response response = Jsoup.connect("https://www.noon.com/_svc/cart-v1/cart")
					.ignoreContentType(true)
					.headers(headers).method(Method.GET).execute();

					
			cookies = response.cookies();
			return cookies;
			
		}
		catch (IOException e) 
		{
			e.printStackTrace();
			cookies = null;
			return cookies;
		}
	
	}

	
}
