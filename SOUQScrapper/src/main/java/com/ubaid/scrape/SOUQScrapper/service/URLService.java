package com.ubaid.scrape.SOUQScrapper.service;

import java.util.List;

import com.ubaid.scrape.SOUQScrapper.entity.EnArUrls;
import com.ubaid.scrape.SOUQScrapper.entity.URL;

public interface URLService
{
	/**
	 * 
	 * @param url
	 * @return return 50 page URLs
	 */
	public List<EnArUrls> getURLs(URL url);
}
