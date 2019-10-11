
package com.ubaid.scrape.NoonScrapper.service;

import java.io.IOException;
import java.util.Map;

import org.jsoup.Jsoup;
import org.jsoup.Connection.Method;
import org.jsoup.Connection.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ubaid.scrape.NoonScrapper.entity.EnArNodes;
import com.ubaid.scrape.NoonScrapper.model.PostBodyFactory;
import com.ubaid.scrape.NoonScrapper.service.def.CookieService;
import com.ubaid.scrape.NoonScrapper.service.def.ResponseService;

@Service
public class ResponseServiceImp implements ResponseService {

	@Autowired
	CookieService cookieService;
	
	@Autowired
	PostBodyFactory factory;
	
	@Override
	public EnArNodes get(String url, int pageNumber)
	{
		cookieService.setEnglish();
		cookieService.setRefferal(url + "?page=" + pageNumber);
		Map<String, String> headers = cookieService.getHeader();
		String postBody = factory.createPostBody(pageNumber, url);
		try
		{
			Response response = getResponse(headers, postBody);
			String body = response.body();
			ObjectMapper mapper = new ObjectMapper();
			JsonNode jsonEn = mapper.readTree(body);
			cookieService.setArabic();
			headers = cookieService.getHeader();
			response = getResponse(headers, postBody);
			body = response.body();
			JsonNode jsonAr = mapper.readTree(body);
			
			EnArNodes enArNodes = new EnArNodes(jsonEn, jsonAr);
			
			return enArNodes;

		}
		catch(IOException exp)
		{
			exp.printStackTrace();
			throw new RuntimeException();
		}
		
	}

	@Override
	public int lastPage(String url) {
		cookieService.setRefferal(url);
		Map<String, String> headers = cookieService.getHeader();
		String postBody = factory.createDefaultPostBody(url);
		try
		{
			Response response = getResponse(headers, postBody);
			String body = response.body();
			ObjectMapper mapper = new ObjectMapper();
			JsonNode json = mapper.readTree(body);
			JsonNode intNode = json.get("nbPages");
			int totalPages = intNode.asInt();
			return totalPages;
		}
		catch(IOException exp)
		{
			exp.printStackTrace();
			throw new RuntimeException();
		}

		
	}

	private Response getResponse(Map<String, String> headers, String postBody) throws IOException {
		return Jsoup.connect("https://www.noon.com/_svc/catalog/api/search")
				.ignoreContentType(true)
				.headers(headers)
				.requestBody(postBody)
				.method(Method.POST)
				.execute();
	}

}
