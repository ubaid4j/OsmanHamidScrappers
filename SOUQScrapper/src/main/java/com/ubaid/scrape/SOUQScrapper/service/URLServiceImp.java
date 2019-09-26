package com.ubaid.scrape.SOUQScrapper.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ubaid.scrape.SOUQScrapper.entity.EnArUrls;
import com.ubaid.scrape.SOUQScrapper.entity.URL;

@Service
public class URLServiceImp implements URLService
{
	
	@Autowired
	Integer size;

	@Override
	public List<EnArUrls> getURLs(URL url)
	{
		
		List<EnArUrls> lists = new ArrayList<EnArUrls>();
		
		
		for(int i = 0; i <= size; i++)
		{
			EnArUrls set = new EnArUrls();
			set.setEnURL(url.toString());
			url.setLanguage("sa-ar");
			set.setArURL(url.toString());
			lists.add(set);
			url.setLanguage("sa-en");
			url.setPage(url.getPage() + 1);
		}
		
		return lists;
	}

}
