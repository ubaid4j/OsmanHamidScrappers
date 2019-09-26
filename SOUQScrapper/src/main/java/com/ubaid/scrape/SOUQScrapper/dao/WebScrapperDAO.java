package com.ubaid.scrape.SOUQScrapper.dao;


import com.ubaid.scrape.SOUQScrapper.entity.EnArNodes;
import com.ubaid.scrape.SOUQScrapper.entity.EnArUrls;

public interface WebScrapperDAO
{
	/**
	 * 
	 * @param url
	 * @return all units of the product of this url
	 */
	public EnArNodes getAllUnits(EnArUrls urls);

}
