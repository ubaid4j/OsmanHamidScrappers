
package com.ubaid.scrapper.AliExpress.logging;


import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import com.ubaid.scrapper.AliExpress.product.Product;


//@Aspect
//@Component
public class Logger extends Logging
{
	
	
//	@Around("getUnits()")
	public Object getUnitsLogger(ProceedingJoinPoint proceedingJoinPoint) throws Throwable
	{
		Object result;
		
		try
		{
			result = proceedingJoinPoint.proceed();
//			String url = (String) proceedingJoinPoint.getArgs()[0];
//			int pageNumber = (int) proceedingJoinPoint.getArgs()[1];
//			EnArNodes nodes = (EnArNodes) result;
//			System.out.println(info + " got " + nodes.getEnNode().get("hits").size() + " products units from the " + url + " and page number " + pageNumber);
		}
		catch(Exception exp)
		{
			System.out.println(error + " " + exp.getMessage());
//			return new ExceptionBody(exp.getLocalizedMessage(), exp.getMessage(), exp.getClass().getSimpleName());
			return null;
		}
		
		return result;
	}
	
//	@AfterReturning(pointcut = "saveProduct()", returning = "product")
	public void saveProductLogger(JoinPoint joinPoint, Product product)
	{
		System.out.println(cServiceInfo + " " + product.toString() + " saved");
	}
}
