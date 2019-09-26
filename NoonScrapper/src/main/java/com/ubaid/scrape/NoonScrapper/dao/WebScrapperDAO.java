package com.ubaid.scrape.NoonScrapper.dao;


import com.ubaid.scrape.NoonScrapper.entity.EnArNodes;
import com.ubaid.scrape.NoonScrapper.entity.EnArUrls;

public interface WebScrapperDAO
{
	/**
	 * 
	 * @param url
	 * @return all units of the product of this url
	 */
	public EnArNodes getAllUnits(EnArUrls urls);

}
