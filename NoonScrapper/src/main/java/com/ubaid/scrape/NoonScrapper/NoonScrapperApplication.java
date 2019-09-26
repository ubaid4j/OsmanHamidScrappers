package com.ubaid.scrape.NoonScrapper;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class NoonScrapperApplication implements CommandLineRunner
{

	public static void main(String[] args)
	{
		SpringApplication.run(NoonScrapperApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception
	{
		
	}

	
	@Bean
	public Integer getSize()
	{
		return 1;
	}
}
