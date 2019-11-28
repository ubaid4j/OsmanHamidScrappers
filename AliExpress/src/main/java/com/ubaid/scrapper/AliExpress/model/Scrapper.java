package com.ubaid.scrapper.AliExpress.model;

import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class Scrapper
{
	public Scrapper()
	{
		System.setProperty("webdriver.chrome.driver","lib\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		driver.get("https://www.alibaba.com/catalog/industrial-controls_cid205775507?spm=a2700.galleryofferlist.scGlobalHomeHeader.550.3be31790ygojP9");		
		List<WebElement> listOfElements = driver.findElements(By.cssSelector("div.organic-gallery-offer-outter.J-offer-wrapper"));
		for (WebElement element : listOfElements)
		{
			WebElement anchorTag = element.findElement(By.cssSelector("a"));
			assert(anchorTag != null);
		}
		
	}	
}
