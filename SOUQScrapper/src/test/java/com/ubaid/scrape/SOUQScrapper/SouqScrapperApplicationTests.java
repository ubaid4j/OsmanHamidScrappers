package com.ubaid.scrape.SOUQScrapper;

import static org.junit.Assert.assertNotNull;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SouqScrapperApplicationTests
{
	String userAgent = "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/70.0.3538.77 Safari/537.36";
	String referral = "http://www.google.com";
	String link = "https://saudi.souq.com/sa-en/women/dresses-465/a-t/s/?ref=nav&page=43&section=2&action=grid&_=1569421336531";
	
	
	@Test
	public void contextLoads()
	{
		try {
			String response = Jsoup.connect(link).ignoreContentType(true).maxBodySize(0).userAgent(userAgent)
					.referrer(referral).execute().body();
			
			ObjectMapper mapper = new ObjectMapper();
			JsonNode node = mapper.readTree(response);
			
			JsonNode units = node.get("jsonData");
			
			JsonNode actual_units = units.get("units");
			
			assertNotNull(actual_units);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
}
