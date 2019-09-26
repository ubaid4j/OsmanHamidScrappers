package com.ubaid.scrape.NoonScrapper.scrapper;


import java.io.IOException;

import org.jsoup.Jsoup;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ubaid.scrape.NoonScrapper.entity.EnArNodes;
import com.ubaid.scrape.NoonScrapper.entity.EnArUrls;
import com.ubaid.scrape.NoonScrapper.exceptions.ConnectionIsBroked;
import com.ubaid.scrape.NoonScrapper.exceptions.ResponseIsNull;

@Component
public class Scrapper
{
	
	private static final String USER_AGENT = "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/70.0.3538.77 Safari/537.36";
	private static final String REFERRAL = "http://www.google.com";

	
	/**
	 * 
	 * @param url
	 * @return all units
	 */
	public EnArNodes getAllUnits(EnArUrls urls) throws RuntimeException
	{

		try
		{
						
			String eng_response = Jsoup.connect(urls.getEnURL()).ignoreContentType(true).maxBodySize(0).userAgent(USER_AGENT).referrer(REFERRAL).execute().body();
			if(eng_response == null)
				throw new ResponseIsNull("The response from url " + urls.getEnURL() + " is null");

			String ar_response = Jsoup.connect(urls.getArURL()).ignoreContentType(true).maxBodySize(0).userAgent(USER_AGENT).referrer(REFERRAL).execute().body();
			if(ar_response == null)
				throw new ResponseIsNull("The response from url " + urls.getArURL() + " is null");

			
			ObjectMapper eng_mapper = new ObjectMapper();
			JsonNode eng_node = eng_mapper.readTree(eng_response);
			
			JsonNode eng_data = eng_node.get("jsonData");

			JsonNode eng_units = eng_data.get("units");
			
//			JsonNode eng_units = eng_node.get("data");

			
			ObjectMapper ar_mapper = new ObjectMapper();
			JsonNode ar_node = ar_mapper.readTree(ar_response);
			
			JsonNode ar_data = ar_node.get("jsonData");

			JsonNode ar_units = ar_data.get("units");

//			JsonNode ar_units = ar_node.get("data");

			
			EnArNodes nodes = new EnArNodes(eng_units, ar_units);
			
			return nodes;
		}
		catch (IOException e)
		{
			throw new ConnectionIsBroked("The url " + urls.getEnURL() + " is raising exception");
		}
		
	}
}
