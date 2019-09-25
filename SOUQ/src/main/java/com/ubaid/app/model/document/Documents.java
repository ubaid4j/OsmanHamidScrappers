package com.ubaid.app.model.document;

import java.io.IOException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;


import com.ubaid.app.controller.Controller;


public class Documents implements IDocument
{

	
	private List<Document> documents;
	protected Controller controller;
	
	public Documents(String url, Controller controller)
	{
		this.controller = controller;
		documents = new ArrayList<>();
		url = getSpecialURL(url);
		controller.getQueue().setIndex("First URL: " + url);		
		int maxNumber = getMaxNumber(url);
		
		List<String> urlList = getURLs(url, maxNumber);
		controller.getQueue().setIndex("total number of URL: " + urlList.size());
		addDocuments(urlList);
	}
	
	private String getSpecialURL(String url)
	{
//		int index = url.indexOf("?");
//		String subString = url.substring(0, index);
		url = url.replace("&ref=nav", "");
		url = url.replace("ref=nav", "");
		url = url.replace("&page=1", "");
		url = url.replace("page=1", "");
		
		
		int len = url.length();
		if(url.contains("?"))
		{
			int index = url.indexOf("?");
			
			if(len == ++index)
				url += "ref=nav&section=2&page=1";
			else
				url += "&ref=nav&section=2&page=1";

		}
		else
		{
			url += "?ref=nav&section=2&page=1";			
		}
		
		return url;
	}
	
	private List<String> getURLs(String url, int maxNumber)
	{
		List<String> list = new ArrayList<>();
		for(int i = 0; i < maxNumber; i++)
		{
			list.add(url.substring(0, url.length() - 1) + Integer.toString(i + 1));
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
		int max = 0;

		try
		{

			Document doc = Jsoup.connect(url).get();
			
			try
			{
				Element total = doc.select("li.total").first();				
				String text = total.text(); 
				text = text.replace("(", "");
				text = text.replace(")", "");
				text = text.replace(",", "");
				text = text.trim();
				
				String[] words = text.split(" ");

				for(int i = 0; i < words.length; i++)
				{
					try
					{
						int number = Integer.parseInt(words[i]);
						max = (int) Math.ceil((double) number / 60);
						break;
					}
					catch(NumberFormatException exp)
					{
						
					}
				}


			}
			catch(NullPointerException exp)
			{
				
			}
			
			documents.add(doc);
			controller.getQueue().setIndex("Document added(0) " + url);			
			
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
			
			max = getMaxNumber(url);
		}
		catch(IOException exp)
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
			
			max = getMaxNumber(url);
			
		}
		catch(Exception exp)
		{
			exp.printStackTrace();
			return -1;
		}
		
		return max;
	}
	
	private void addDocuments(List<String> list)
	{
		for(int i = 1; i < list.size(); i++)
		{
			try
			{
				Document document = Jsoup.connect(list.get(i)).get();
				documents.add(document);
				controller.getQueue().setIndex("Document added (" + i + ")" + "URL(" + list.get(i) + ")");
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
					Thread.sleep(1000);
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
