package com.ubaid.scrape.NoonScrapper.service;

import com.ubaid.scrape.NoonScrapper.entity.EnArNodes;
import com.ubaid.scrape.NoonScrapper.entity.EnArUrls;

public interface WebScrapeService
{
	/**
	 * 
	 * @param url
	 * @return all units of the product of this url
	 */
	public EnArNodes getAllUnits(EnArUrls url);
}
