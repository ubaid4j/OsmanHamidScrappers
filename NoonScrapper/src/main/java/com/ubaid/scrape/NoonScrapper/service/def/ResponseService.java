package com.ubaid.scrape.NoonScrapper.service.def;

import com.ubaid.scrape.NoonScrapper.entity.EnArNodes;

public interface ResponseService {

	public EnArNodes get(String url, int pageNumber);
	public int lastPage(String url);
}
