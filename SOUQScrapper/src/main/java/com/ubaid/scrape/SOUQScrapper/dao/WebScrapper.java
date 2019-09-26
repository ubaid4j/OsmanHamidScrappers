package com.ubaid.scrape.SOUQScrapper.dao;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ubaid.scrape.SOUQScrapper.entity.EnArNodes;
import com.ubaid.scrape.SOUQScrapper.entity.EnArUrls;
import com.ubaid.scrape.SOUQScrapper.scrapper.Scrapper;

@Repository
public class WebScrapper implements WebScrapperDAO {

	@Autowired
	Scrapper scrapper;
	
	@Override
	public EnArNodes getAllUnits(EnArUrls urls)
	{
		return scrapper.getAllUnits(urls);
	}

}
