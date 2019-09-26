package com.ubaid.scrape.NoonScrapper.dao;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ubaid.scrape.NoonScrapper.entity.EnArNodes;
import com.ubaid.scrape.NoonScrapper.entity.EnArUrls;
import com.ubaid.scrape.NoonScrapper.scrapper.Scrapper;

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
