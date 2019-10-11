package com.ubaid.scrape.NoonScrapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.ubaid.scrape.NoonScrapper.service.RunApplicationService;

@SpringBootApplication
public class NoonScrapperApplication implements CommandLineRunner
{
	@Autowired
	RunApplicationService service;
	
	public static void main(String[] args)
	{
		SpringApplication.run(NoonScrapperApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception
	{
		service.run();
	}

	
	@Bean
	public Integer getSize()
	{
		return 1;
	}
}
