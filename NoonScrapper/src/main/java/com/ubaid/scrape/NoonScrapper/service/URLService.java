package com.ubaid.scrape.NoonScrapper.service;

import java.util.List;

import com.ubaid.scrape.NoonScrapper.entity.EnArUrls;
import com.ubaid.scrape.NoonScrapper.entity.URL;

public interface URLService
{
	/**
	 * 
	 * @param url
	 * @return return 50 page URLs
	 */
	public List<EnArUrls> getURLs(URL url);
}
