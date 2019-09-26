package com.ubaid.scrape.SOUQScrapper.service;

import com.ubaid.scrape.SOUQScrapper.entity.EnArNodes;
import com.ubaid.scrape.SOUQScrapper.entity.EnArUrls;

public interface WebScrapeService
{
	/**
	 * 
	 * @param url
	 * @return all units of the product of this url
	 */
	public EnArNodes getAllUnits(EnArUrls url);
}
