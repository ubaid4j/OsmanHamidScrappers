package com.ubaid.scrape.NoonScrapper.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.JsonNode;
import com.ubaid.scrape.NoonScrapper.entity.EnArNodes;
import com.ubaid.scrape.NoonScrapper.entity.Product;
import com.ubaid.scrape.NoonScrapper.service.def.ProductCRUDService;
import com.ubaid.scrape.NoonScrapper.service.def.ProductScrapperService;

@Service
public class ProductScrapperServiceImp implements ProductScrapperService {

	@Autowired
	ProductCRUDService service;
	
	@Override
	public List<Product> getProducts(EnArNodes node) {
		List<Product> products = new ArrayList<Product>();
		JsonNode engNode = node.getEnNode();
		JsonNode arNode = node.getArNode();
		
		JsonNode meta = engNode.get("meta");
		
		
		JsonNode hitsArray = engNode.get("hits");
		JsonNode hitsArrayAr = arNode.get("hits");
		
		int size = hitsArray.size();
		
		for(int i = 0; i < size; i++)
		{
			try
			{
				JsonNode engP = hitsArray.get(i);
				JsonNode arP = hitsArrayAr.get(i);
				String brand = engP.get("brand").asText();
				String image_key = engP.get("image_key").asText();
				String name_en = engP.get("name").asText();
				String name_ar = arP.get("name").asText();
				String price = engP.get("price").asText();
				String url_ = engP.get("url").asText();
				
				String sku = engP.get("sku").asText();
				String offerCode = engP.get("offer_code").asText();
				
				String url = "https://www.noon.com/saudi-en/" + url_ + "/" + sku + "/p?o=" + offerCode;
				
				String imageURL = "https://k.nooncdn.com/t_desktop-pdp-v1/" + image_key + ".jpg";
				String type = meta.get("h1").asText();
				
				//1. product link
				//2. image link
				Product product = new Product(name_ar, name_en, url, imageURL, price, type, brand);
				service.save(product);
				
			}
			catch(NullPointerException exp)
			{
				System.out.println("[Error: ] + " + getClass().getSimpleName());
			}
		}
		
		return products;
	}

}
