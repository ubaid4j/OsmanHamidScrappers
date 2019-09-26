package com.ubaid.scrape.NoonScrapper.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ubaid.scrape.NoonScrapper.entity.EnArNodes;
import com.ubaid.scrape.NoonScrapper.entity.EnArUrls;
import com.ubaid.scrape.NoonScrapper.entity.Product;
import com.ubaid.scrape.NoonScrapper.entity.URL;

@Service
public class RunApplicationService
{
	@Autowired
	WebScrapeService service;
	
	@Autowired
	URLService urlService;
	
	@Autowired
	ProductScrapeService productScrapeService;
	
	@Autowired
	ProductCService cService;

	
	public void run(URL url)
	{
		List<EnArUrls> urlLists = urlService.getURLs(url);
				

		for(EnArUrls urlSet : urlLists)
		{
			try
			{
				EnArNodes nodes = service.getAllUnits(urlSet);
				List<Product> products = productScrapeService.getAllProducts(nodes);
				for(Product product : products)
				{
					try
					{
						cService.save(product);
						
					}
					catch(Exception exp)
					{
						
					}
				}
				
			}
			catch(Exception exp)
			{
				
			}
		}
	}
}
