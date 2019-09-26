package com.ubaid.scrape.NoonScrapper.exceptions;

public class ResponseIsNull extends RuntimeException
{
	private static final long serialVersionUID = 1L;

	public ResponseIsNull(String message)
	{
		super(message);
	}
	
}
