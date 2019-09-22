package com.ubaid.app;


import org.jsoup.Connection.Response;
import org.jsoup.Jsoup;

public class Test 
{
	public static void main(String [] args)
	{
		try
		{
			Test app = new Test();	
			app.app();
		}
		catch(Exception exp)
		{
			exp.printStackTrace();
		}
		
	}
	
	@SuppressWarnings("unused")
	public void app()
	{
		try
		{
			Response doc = Jsoup.connect("https://uae.souq.com/ae-ar/intel-ÙØ­Ø¯Ø©-ÙØ¹Ø§ÙØ¬Ø©-ÙØ±ÙØ²ÙØ©-2-2-10-bx80660e52630-v4-Ø§ÙÙÙØ¨ÙÙØªØ±-27748570/i/").followRedirects(true).execute();

			
//			Element title_div = doc.getElementsByClass("product-title").first();
//			Element h1 = title_div.getElementsByTag("h1").first();
//			String name = h1.text();
			
		}
		catch(Exception exp)
		{
			exp.printStackTrace();
			app();
		}
	}
}
