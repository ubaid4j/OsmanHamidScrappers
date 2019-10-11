package com.ubaid.scrape.NoonScrapper.logging;

import org.aspectj.lang.annotation.Pointcut;

//import org.aspectj.lang.annotation.Pointcut;

public abstract class Logging
{
	protected static final String error= "[Error: com.ubaid.scrape.SOUQScrapper.dao.WebScrapper]: ";
	protected static final String info = "[INFO: com.ubaid.scrape.SOUQScrapper.dao.WebScrapper]: ";
	
	protected static final String cServiceInfo = "[INFO: com.ubaid.scrape.SOUQScrapper.service.ProductCServiceImp]: ";
	
	@Pointcut("execution(* com.ubaid.scrape.NoonScrapper.service.ResponseServiceImp.get(..))")
	protected void getUnits() {}
	
	@Pointcut("execution(* com.ubaid.scrape.NoonScrapper.service.ProductCRUDServiceImp.save(..))")
	protected void saveProduct() {}
}
