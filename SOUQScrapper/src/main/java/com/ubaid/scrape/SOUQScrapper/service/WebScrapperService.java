package com.ubaid.scrape.SOUQScrapper.service;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ubaid.scrape.SOUQScrapper.dao.WebScrapper;
import com.ubaid.scrape.SOUQScrapper.entity.EnArNodes;
import com.ubaid.scrape.SOUQScrapper.entity.EnArUrls;

@Service
public class WebScrapperService implements WebScrapeService
{

	@Autowired
	private WebScrapper webScrapper;

	
	@Override
	public EnArNodes getAllUnits(EnArUrls urls)
	{
		return webScrapper.getAllUnits(urls);
	}

}
