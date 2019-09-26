package com.ubaid.scrape.NoonScrapper.service;

import java.util.List;

import com.ubaid.scrape.NoonScrapper.entity.EnArNodes;
import com.ubaid.scrape.NoonScrapper.entity.Product;

public interface ProductScrapeService
{
	//JsonNode of units 
	//and you have to return 
	//list of products 
	List<Product> getAllProducts(EnArNodes nodes);
}
