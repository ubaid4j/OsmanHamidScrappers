package com.ubaid.scrape.NoonScrapper.model;

import java.util.Arrays;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.ubaid.scrape.NoonScrapper.entity.PostBody;

@Component
public class PostBodyFactory
{
	
	public String createDefaultPostBody(String category)
	{
		ObjectMapper mapper = new ObjectMapper();
		mapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
		PostBody postBody = new PostBody();
		postBody.setLimit(150);
		postBody.setPage(1);
		postBody.setFilterKey(Arrays.asList(category));
		try
		{
			return mapper.writeValueAsString(postBody);
		}
		catch (JsonProcessingException e)
		{
			e.printStackTrace();
			throw new RuntimeException(e.getMessage());
		}
	}
	
	public String createPostBody(int pageNumber, String category)
	{
		ObjectMapper mapper = new ObjectMapper();
		mapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
		PostBody postBody = new PostBody();
		postBody.setLimit(150);
		postBody.setPage(pageNumber);
		postBody.setFilterKey(Arrays.asList(category));
		try
		{
			return mapper.writeValueAsString(postBody);
		}
		catch (JsonProcessingException e)
		{
			e.printStackTrace();
			throw new RuntimeException(e.getMessage());
		}
	}

}
