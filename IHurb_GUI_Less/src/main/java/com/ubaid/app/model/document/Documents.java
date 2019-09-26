package com.ubaid.app.model.document;

import java.io.IOException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.jsoup.HttpStatusException;
import org.jsoup.Jsoup;
import org.jsoup.Connection.Method;
import org.jsoup.Connection.Response;
import org.jsoup.nodes.Document;

import com.ubaid.app.controller.Controller;


public class Documents implements IDocument
{

	
	private List<Document> documents;
	protected Controller controller;
	String userAgent = "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/70.0.3538.77 Safari/537.36";
	String referral = "http://www.google.com";

	
	
	public Documents(String url, Controller controller)
	{
		this.controller = controller;
		documents = new LinkedList<Document>();
		url = getSpecialURL(url);
		controller.getQueue().setIndex("First URL: " + url);		
		int maxNumber = getMaxNumber(url);
		List<String> urlList = getURLs(url, maxNumber);
		controller.getQueue().setIndex("total number of URL: " + urlList.size());
		addDocuments(urlList);
	}
	
	private Map<String, String> getEnglishCookie(String url)
	{
		Response res = null;
		Map<String, String> cookie = null;
		try
		{
			res = Jsoup
				    .connect(url)
				    .method(Method.GET)
				    .execute();
			cookie = res.cookies();
			
			cookie.put("iher-pref1", Cookie.en_ln);

		}
		catch(HttpStatusException e1)
		{
			e1.printStackTrace();
			try
			{
				Thread.sleep(10000);
			}
			catch (InterruptedException e)
			{
				e.printStackTrace();
			}
			
			cookie = getEnglishCookie(url);
							
		}
		catch(SocketTimeoutException | UnknownHostException exp)
		{
			exp.printStackTrace();
			try
			{
				Thread.sleep(1000);
			}
			catch (InterruptedException e)
			{
				e.printStackTrace();
			}
			
			cookie = getEnglishCookie(url);
			
		}
		catch (IOException exp)
		{
			exp.printStackTrace();
			try
			{
				Thread.sleep(10000);
			}
			catch (InterruptedException e)
			{
				e.printStackTrace();
			}

			cookie = getEnglishCookie(url);

		}			
			
		return cookie;

	}
	

	
	
	private String getSpecialURL(String url)
	{
		return url;
	}
	
	private List<String> getURLs(String url, int maxNumber)
	{
		List<String> list = new ArrayList<>();
				
		for(int i = 1; i <= maxNumber; i++)
		{
			list.add(String.format("%s?p=%d", url, i));
		}
		
		return list;
	}

	@Override
	public List<Document> getDocuments()
	{
		return documents;
	}
	
	private int getMaxNumber(String url)
	{
		//total urls
		return 19;
	}
	
	private void addDocuments(List<String> list)
	{
		Random random = new Random();
		
		Map<String, String> cookie = getEnglishCookie(list.get(0));
		
		for(int i = 0; i < list.size(); i++)
		{
			try
			{
				Document document = Jsoup.connect(list.get(i)).maxBodySize(0).userAgent(userAgent).referrer(referral).cookies(cookie).get();
				documents.add(document);
				controller.getQueue().setIndex("Document added (" + (i + 1) + ")" + "URL(" + list.get(i) + ")");
				
				try
				{
					Thread.sleep(random.nextInt(1000));
				}
				catch(InterruptedException exp)
				{
					exp.printStackTrace();
				}
			}
			catch(HttpStatusException e1)
			{
				e1.printStackTrace();
				try
				{
					Thread.sleep(10000);
				}
				catch (InterruptedException e)
				{
					e.printStackTrace();
				}
				
				i--;
				
				
			}
			catch(SocketTimeoutException | UnknownHostException exp)
			{
				exp.printStackTrace();
				try
				{
					Thread.sleep(1000);
				}
				catch (InterruptedException e)
				{
					e.printStackTrace();
				}
				
				i--;
				
			}
			catch (IOException exp)
			{
				exp.printStackTrace();
				try
				{
					Thread.sleep(10000);
				}
				catch (InterruptedException e)
				{
					e.printStackTrace();
				}
				
				i--;
			}
		}
	}

}
