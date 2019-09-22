package com.ubaid.app.model.automate;



import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

import com.ubaid.app.model.q.Q;

public class ScrollDown
{
	
	public ScrollDown(WebDriver driver, int loop, Q q)
	{
		
		for(int i = 0; i < loop; i++)
		{
			scroll(driver, 1248, q);	
			if(q.isBottom())
			{
				break;				
			}
		}
		
	}
	
	private void scroll(WebDriver driver, int height, Q q)
	{
		try
		{
			JavascriptExecutor jsx = (JavascriptExecutor)driver;
			jsx.executeScript("window.scrollBy(0, + " + height + ")", "");
			setNumber(driver, q);
			Thread.sleep(10000);
			
		}
		catch(Exception exp)
		{
			exp.printStackTrace();
		}
	}
	
	public void setNumber(WebDriver driver, Q q)
	{
		JavascriptExecutor executor = (JavascriptExecutor) driver;
		Long value = (Long) executor.executeScript("return window.pageYOffset;");
		q.add(value);
	}
}
