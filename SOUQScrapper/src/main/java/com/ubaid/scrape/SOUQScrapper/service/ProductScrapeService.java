package com.ubaid.scrape.SOUQScrapper.service;

import java.util.List;

import com.ubaid.scrape.SOUQScrapper.entity.EnArNodes;
import com.ubaid.scrape.SOUQScrapper.entity.Product;

public interface ProductScrapeService
{
	//JsonNode of units 
	//and you have to return 
	//list of products 
	List<Product> getAllProducts(EnArNodes nodes);
}
