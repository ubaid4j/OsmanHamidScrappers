package com.ubaid.scrapper.AliExpress.model;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class Scrapper
{
	public Scrapper()
	{
		System.setProperty("webdriver.chrome.driver","lib\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		WebDriver driver2 = new ChromeDriver();
		driver.get("https://campaign.aliexpress.com/wow/gf/upr-node?spm=a2g0o.tm31154.3988602820.13.693715278Swdzu&wh_pid=1111_Tools_Improvement_tab&wh_weex=true&_immersiveMode=true&wx_navbar_hidden=true&wx_navbar_transparent=true&wx_statusbar_hidden=true&topTabIds=7819047");
		
		List<WebElement> previousCollection = new ArrayList<WebElement>();
		try
		{
			Thread.sleep(6000);
		}
		catch(InterruptedException exp)
		{
			exp.printStackTrace();
		}
		List<WebElement> listOfElements = driver.findElements(By.cssSelector("a[target='_blank']"));

		int counter = 0;
		do
		{
			for (WebElement element : listOfElements)
			{
				String url = element.getAttribute("href");
				driver2.get(url);
				List<WebElement> metas = driver2.findElements(By.tagName("meta"));
				for (WebElement meta : metas)
				{
					String attribute = meta.getAttribute("property");
					String content = meta.getAttribute("content");
					
					if (attribute == null)
						continue;
					
					switch (attribute) {
					case "og:title":
						System.err.println(content);
						break;
					case "og:image":
						System.err.println(content);
						break;
					case "og:url":
						System.err.println(content);
						break;
					default:
						break;
					}
				}
			}
			
			previousCollection.addAll(listOfElements);
			List<WebElement> nextElements = getNextElements(driver);
			listOfElements = nextElements;
			
		} while(counter < 100);
		
			
		System.err.println(listOfElements.size());
//		List<WebElement> nextElements = getNextElements(driver);
//		System.err.println(nextElements.size());
//		nextElements = getNextElements(driver);
//		System.err.println(nextElements.size());
	}
	
	private List<WebElement> getNextElements(WebDriver driver)
	{
		try
		{
			JavascriptExecutor js = (JavascriptExecutor) driver;
			Thread.sleep(3000);
			js.executeScript("window.scrollBy(0,5000)");
			Thread.sleep(3000);
			return driver.findElements(By.cssSelector("a[target='_blank']"));

		}
		catch (InterruptedException e)
		{
			e.printStackTrace();
		};

		return null;
	}
	
}
