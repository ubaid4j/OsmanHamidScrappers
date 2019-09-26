package com.ubaid.scrape.SOUQScrapper.exceptions;

public class ConnectionIsBroked extends RuntimeException
{
	private static final long serialVersionUID = 1L;

	public ConnectionIsBroked(String message)
	{
		super(message);
	}
}
