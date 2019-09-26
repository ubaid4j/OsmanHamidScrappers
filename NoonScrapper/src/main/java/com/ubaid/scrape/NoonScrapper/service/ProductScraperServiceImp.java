package com.ubaid.scrape.NoonScrapper.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.JsonNode;
import com.ubaid.scrape.NoonScrapper.entity.EnArNodes;
import com.ubaid.scrape.NoonScrapper.entity.Product;

@Service
public class ProductScraperServiceImp implements ProductScrapeService
{

	@Override
	public List<Product> getAllProducts(EnArNodes nodes)
	{
		JsonNode enNode = nodes.getEnNode();
		JsonNode arNode = nodes.getArNode();
		
		int size = enNode.size();
		
		List<Product> products = new ArrayList<Product>(size);
		
		for(int i = 0; i < size; i++)
		{
			JsonNode enNodeT = enNode.get(i);
			JsonNode arNodeT = arNode.get(i);
			
			String nameEnglish = enNodeT.get("title").asText();
			String imageURL = enNodeT.get("image_url").asText();
			String primaryLink = enNodeT.get("primary_link").asText();
			String price = enNodeT.get("price_formatted").asText();
			String type = enNodeT.get("item_type_label").asText();
			String brand = enNodeT.get("manufacturer").asText();
			
			String name = arNodeT.get("title").asText();
			
			Product product = new Product(name, nameEnglish, primaryLink, imageURL, price, type, brand);
			products.add(product);
			
		}
		
		return products;
	}

}
