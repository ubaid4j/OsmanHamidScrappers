package com.ubaid.scrape.NoonScrapper.service;

import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ubaid.scrape.NoonScrapper.entity.EnArNodes;
import com.ubaid.scrape.NoonScrapper.service.def.CookieService;
import com.ubaid.scrape.NoonScrapper.service.def.FileService;
import com.ubaid.scrape.NoonScrapper.service.def.ProductScrapperService;
import com.ubaid.scrape.NoonScrapper.service.def.ResponseService;


@Service
public class RunApplicationService
{
	
	@Autowired
	FileService fileService;
	
	@Autowired
	CookieService cookieService;
	
	@Autowired
	ResponseService responseService;


	@Autowired
	ProductScrapperService productScrapperService;
	
	private final Random random = new Random();
	
	public void run()
	{
		//get all categories from the file
		List<String> categories = fileService.getAllCategories();
		
		int size = categories.size();

		for (int i = 0; i < size; i++)
		{
			//set cookie
			if (i == 0)
			{
				cookieService.setRefferal(categories.get(i));
				cookieService.setCookie();
			}
			
			int totalPages = responseService.lastPage(categories.get(i));
			
			for(int pageNumber = 1; pageNumber < totalPages; pageNumber++)
			{
				try
				{
					if (pageNumber % 3 == 0)
						cookieService.refreshCookie();
					
					EnArNodes node = responseService.get(categories.get(i), pageNumber + 1);
					productScrapperService.getProducts(node);
					
					Thread.sleep(random.nextInt(2000));
					
				}
				catch(Exception exp)
				{
					System.out.println("[Error:] " + getClass().getSimpleName());
				}
				
			}
		}
		
	}
	
	
	
	
}
