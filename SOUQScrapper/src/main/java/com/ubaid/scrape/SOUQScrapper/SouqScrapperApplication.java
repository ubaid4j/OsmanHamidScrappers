package com.ubaid.scrape.SOUQScrapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
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
		url.setCategory("women");
		url.setInternalPart("dresses-465/a-t/s/?ref=nav&section=2&action=grid&_=1569421336531");
		url.setPage(1);
		
		service.run(url);
	}

}
