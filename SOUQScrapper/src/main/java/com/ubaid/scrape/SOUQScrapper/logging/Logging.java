package com.ubaid.scrape.SOUQScrapper.logging;

import org.aspectj.lang.annotation.Pointcut;

public abstract class Logging
{
	protected static final String error= "[Error: com.ubaid.scrape.SOUQScrapper.dao.WebScrapper]: ";
	protected static final String info = "[INFO: com.ubaid.scrape.SOUQScrapper.dao.WebScrapper]: ";
	
	protected static final String cServiceInfo = "[INFO: com.ubaid.scrape.SOUQScrapper.service.ProductCServiceImp]: ";
	
	@Pointcut("execution(* com.ubaid.scrape.SOUQScrapper.dao.*.getAllUnits(..))")
	protected void getUnits() {}
	
	@Pointcut("execution(* com.ubaid.scrape.SOUQScrapper.service.ProductCServiceImp.save(..))")
	protected void saveProduct() {}
}
