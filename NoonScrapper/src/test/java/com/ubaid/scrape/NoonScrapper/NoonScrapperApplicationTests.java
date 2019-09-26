package com.ubaid.scrape.NoonScrapper;

import static org.junit.Assert.assertNotNull;

import java.io.IOException;
import java.util.Arrays;

import org.jsoup.Connection.Method;
import org.jsoup.Jsoup;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.ubaid.scrape.NoonScrapper.entity.PostBody;

@RunWith(SpringRunner.class)
@SpringBootTest
public class NoonScrapperApplicationTests {

	private static final String USER_AGENT = "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/70.0.3538.77 Safari/537.36";
	private static final String REFERRAL = "http://www.google.com";

	
	@Test
	public void contextLoads()
	{
		try
		{
			ObjectMapper mapper = new ObjectMapper();
			mapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
			PostBody postBody = new PostBody();
			postBody.setLimit(100);
			postBody.setPage(1);
			postBody.setBrand(Arrays.asList("huawei"));
			postBody.setCategory(Arrays.asList("electronics-and-mobiles/mobiles-and-accessories/mobiles-20905"));

			JsonNode node = mapper.convertValue(postBody, JsonNode.class);
			
			assertNotNull(node);
			
			String eng_response = Jsoup.connect("https://www.noon.com/_svc/catalog/api/search")
					.ignoreContentType(true)
					.maxBodySize(0)
					.userAgent(USER_AGENT)
					.referrer(REFERRAL)
					.method(Method.POST)
					.requestBody(mapper.writeValueAsString(postBody))
					.execute()
					.body();
			
			assertNotNull(eng_response);
			
			JsonNode response = mapper.readTree(eng_response);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		
		
	}

}
