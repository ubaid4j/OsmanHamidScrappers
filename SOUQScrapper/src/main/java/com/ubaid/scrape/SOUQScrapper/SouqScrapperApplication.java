package com.ubaid.scrape.SOUQScrapper;

import org.springframework.beans.factory.annotation.Autowired;
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
	
	public static void main(String[] args)
	{
		SpringApplication.run(SouqScrapperApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception
	{

		URL url = new URL();
		url.setInternalPart("laptop/s/?utm_expid=59915904-44.Re0s6LLkSZOrUaGr1rFaYA.0&utm_referrer=http%3A%2F%2Fsaudi.souq.com%2Fsa-en%2Fsa-small-appliances%2Fc%2F&section=2&action=grid&_=1569504652263");
//		url.setInternalPart("television/televisions-10/a-t/s/?utm_expid=59915904-44.Re0s6LLkSZOrUaGr1rFaYA.0&utm_referrer=http%3A%2F%2Fsaudi.souq.com%2Fsa-en%2Fsamsung-galaxy-s7-edge-dual-sim-32gb-4g-lte-silver-10165600%2Fio%2F&section=2&action=grid&_=1569506622045");
		url.setHost("https://saudi.souq.com");
		url.setLanguage("sa-en");
		url.setPage(1);
		
		service.run(url);
	}
	
	@Bean 
	public Integer getTotalURLs()
	{
		return 100;
	}

}
