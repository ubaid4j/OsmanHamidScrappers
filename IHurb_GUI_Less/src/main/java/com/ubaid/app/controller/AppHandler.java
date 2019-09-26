package com.ubaid.app.controller;

import java.util.ArrayList;
import java.util.List;

import org.jsoup.nodes.Document;

import com.ubaid.app.model.document.Documents;
import com.ubaid.app.model.document.IDocument;
import com.ubaid.app.model.scrapper.Scrapper;



//app handler
public class AppHandler
{
	Controller controller;
	
	public AppHandler(Controller controller)
	{
		try
		{
			this.controller = controller;
			//getting list of urls
			List<String> urls = getURLList();
			

			for(int i = 0; i < urls.size(); i++)
			{
				controller.getQueue().setIndex(urls.get(i));
				new Scrapper_(controller, urls.get(i));
			}

			System.out.println("Scrapping Completed");
			System.exit(0);
			
			
			
		}
		catch(Exception exp)
		{
			controller.getQueue().setText(exp, controller.getQueue().getErrorIndex());
		}
	}
		
	private List<String> getURLList()
	{
		List<String> list = new ArrayList<>();
		list.add("https://www.iherb.com/c/pets");
		return list;		
	}
	
	private class Scrapper_
	{

		Controller controller;
		String url;
		
		public Scrapper_(Controller controller, String url)
		{
			this.controller = controller;
			this.url = url;
			run();
			
		}
		
		
		public void run()
		{
			try
			{
				//creating document 
				IDocument docs = new Documents(url, controller);
				
				//getting list of docs
				List<Document> docList = docs.getDocuments();
				
				
				for(int i = 0; i < docList.size(); i++)
				{
					new Scrapper(docList.get(i), controller);
				}	
			}
			catch(Exception exp)
			{
				controller.getQueue().setText(exp, controller.getQueue().getErrorIndex());
			}
		}
				
	}	
}
