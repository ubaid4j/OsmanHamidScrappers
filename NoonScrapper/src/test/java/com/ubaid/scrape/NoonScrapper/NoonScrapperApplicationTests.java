package com.ubaid.scrape.NoonScrapper;

import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.assertNotNull;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import org.jsoup.Connection.Method;
import org.jsoup.Connection.Response;
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
	private static final String NEW_USER_AGENT = "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/77.0.3865.90 Safari/537.36";
	private static final String REFERRAL = "http://www.google.com";

	
	@Test
	public void contextLoads()
	{
		try
		{
			ObjectMapper mapper = new ObjectMapper();
			mapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
			PostBody postBody = new PostBody();
			postBody.setLimit(150);
			postBody.setPage(1);
//			postBody.setBrand(Arrays.asList("huawei"));
			postBody.setCategory(Arrays.asList("electronics-and-mobiles/mobiles-and-accessories/mobiles-20905"));

			JsonNode node = mapper.convertValue(postBody, JsonNode.class);
			
			Map<String, String> cookies = getCookies();
			String _cookie = "next-i18next=en; _gcl_au=1.1.1263041777.1570800986; _scid=c4128bd6-7044-4207-a4c1-abdc484df0ad; nguest=";
			_cookie += cookies.get("nguest");
			_cookie += "; _sctr=1|1570734000000; __zlcmid=uiibTxXrHcCCwF";
			
			Map<String, String> headers = new HashMap<String, String>();
			headers.put("authority", "www.noon.com");
			headers.put("path", "/_svc/catalog/api/search");
			headers.put("scheme", "https");
			headers.put("accept", "application/json, text/plain, */*");
			headers.put("accept-encoding", "gzip, deflate, br");
			headers.put("accept-language", "en-US,en;q=0.9,ur;q=0.8,lb;q=0.7,ar;q=0.6");
			headers.put("cache-control", "no-cache, max-age=0, must-revalidate, no-store");
			headers.put("content-lenght", "171");
			headers.put("content-type", "application/json");
			headers.put("cookie", _cookie);
			headers.put("pragma", "no-cache");
			headers.put("referer", "https://www.noon.com/saudi-en/electronics-and-mobiles/mobiles-and-accessories/mobiles-20905?page=2");
			headers.put("sec-fetch-mode", "cors");
			headers.put("sec-fetch-site", "same-origin");
			headers.put("user-agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/77.0.3865.90 Safari/537.36");
			headers.put("x-cms", "v2");
			headers.put("x-content", "desktop");
			headers.put("x-locale", "en-sa");
			headers.put("x-platform", "web");

			
			assertNotNull(node);

		
			
			Response response = Jsoup.connect("https://www.noon.com/_svc/catalog/api/search")
					.ignoreContentType(true)
					.headers(headers)
					.requestBody(mapper.writeValueAsString(postBody))
					.method(Method.POST)
					.execute();

			
			
//			String eng_response = Jsoup.connect("https://www.noon.com/_svc/catalog/api/search")
//					.ignoreContentType(true)
//					.maxBodySize(0)
//					.userAgent(NEW_USER_AGENT)
//					.cookies(cookies)
//					.method(Method.POST)
//					.requestBody(mapper.writeValueAsString(postBody))
//					.execute()
//					.body();
			
			String body = response.body();
			
			assertNotNull(response);
			
//			JsonNode response = mapper.readTree(eng_response);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		
		
	}
	
	public Map<String, String> getCookies()
	{
		try
		{
			
			Map<String, String> cookies = new HashMap<String, String>();
			
			Map<String, String> headers = new HashMap<String, String>();
			headers.put("authority", "www.noon.com");
			headers.put("path", "/_svc/cart-v1/cart");
			headers.put("scheme", "https");
			headers.put("accept", "application/json, text/plain, */*");
			headers.put("accept-encoding", "gzip, deflate, br");
			headers.put("accept-language", "en-US,en;q=0.9,ur;q=0.8,lb;q=0.7,ar;q=0.6");
			headers.put("cache-control", "no-cache, max-age=0, must-revalidate, no-store");
			headers.put("cookie", "next-i18next=en; _gcl_au=1.1.1263041777.1570800986; _scid=c4128bd6-7044-4207-a4c1-abdc484df0ad");
			headers.put("pragma", "no-cache");
			headers.put("referer", "https://www.noon.com/saudi-en/electronics-and-mobiles/mobiles-and-accessories/mobiles-20905?page=2");
			headers.put("sec-fetch-mode", "cors");
			headers.put("sec-fetch-site", "same-origin");
			headers.put("user-agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/77.0.3865.90 Safari/537.36");
			headers.put("x-cms", "v2");
			headers.put("x-content", "desktop");
			headers.put("x-locale", "en-sa");
			headers.put("x-platform", "web");
			
			
			Response response = Jsoup.connect("https://www.noon.com/_svc/cart-v1/cart")
					.ignoreContentType(true)
					.headers(headers).method(Method.GET).execute();

			
			
			assertNotNull(response);
			
			return response.cookies();
			
		}
		catch (IOException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	
	}

}
