package com.ubaid.scrape.SOUQScrapper;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

import com.ubaid.scrape.SOUQScrapper.entity.URL;
import com.ubaid.scrape.SOUQScrapper.service.RunApplicationService;

@SpringBootApplication
@EnableAspectJAutoProxy
@EnableAutoConfiguration
public class SouqScrapperApplication implements CommandLineRunner
{

	@Autowired
	RunApplicationService service;

	@Value("${scrape.urls}")
	List<String> urls;
	
	
	public static void main(String[] args)
	{
		SpringApplication.run(SouqScrapperApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception
	{

		URL url = new URL();
		url.setHost("https://saudi.souq.com");
		url.setLanguage("sa-en");
		url.setPage(1);
		
		int size = urls.size();
		for(int i = 0; i < size; i++)
		{
			String tempURL = urls.get(i);
			url.setPage(1);
			url.addURL(tempURL);
			service.run(url);
		}
		
			
//		service.run(url);
	}
	
	@Bean 
	public Integer getTotalURLs()
	{
		return 50;
	}

}
