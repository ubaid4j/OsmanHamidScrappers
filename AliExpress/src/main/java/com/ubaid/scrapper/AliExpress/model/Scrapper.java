package com.ubaid.scrapper.AliExpress.model;

import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Scrapper
{
	public Scrapper()
	{
		System.setProperty("webdriver.chrome.driver","lib\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		driver.get("https://www.aliexpress.com/premium/category/708042.html?CatId=708042&spm=a2g0o.tm38094.104.5.510b2234MFO9OU");		
		List<WebElement> listOfElements = null;
		try
		{
			WebElement random = driver.findElement(By.xpath("/html/body/div[3]/div/div[1]/div/div/div[2]/div[1]/div/ul/li[2]/div"));
			String random_text = random.getText();
			if (random_text.equalsIgnoreCase("Sign In"))
			{

				WebDriverWait wait = new WebDriverWait(driver, 10);
				WebDriverWait wai2t = new WebDriverWait(driver, 20);

				wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.cssSelector("iframe#alibaba-login-box[src^='https://passport.aliexpress.com/mini_login.htm?']")));
				wai2t.until(ExpectedConditions.elementToBeClickable(By.cssSelector("input.fm-text#fm-login-id"))).sendKeys("ubaidshan007@gmail.com");
				wai2t.until(ExpectedConditions.elementToBeClickable(By.cssSelector("input.fm-text#fm-login-password"))).sendKeys("09021997Ubaid");
				driver.findElement(By.cssSelector("button")).click();
								
			}
			WebDriverWait wait = new WebDriverWait(driver, 300);
			wait.until(ExpectedConditions.elementToBeSelected(By.cssSelector("li.list-item")));
			
			listOfElements = driver.findElements(By.cssSelector("li.list-item"));			
		}
		catch(Exception noSuchElementException)
		{
			System.out.println(noSuchElementException.getMessage());
		}
		
		for (WebElement element : listOfElements)
		{
			try
			{
				WebElement anchorTag = element.findElement(By.cssSelector("div.product-img > a"));
				WebElement imgTag = anchorTag.findElement(By.tagName("img"));
				WebElement titleTag = element.findElement(By.cssSelector(".item-title"));
				WebElement priceTag = element.findElement(By.cssSelector(".price-current"));
				String url = anchorTag.getAttribute("href");
				String image_url = imgTag.getAttribute("src");
				String title = titleTag.getAttribute("title");
				String price = priceTag.getText();
				
				System.out.println(url);
				System.out.println(image_url);
				System.out.println(title);
				System.out.println(price);
				
			}
			catch(NullPointerException exp)
			{
				System.err.println(exp.getMessage());
			}
		}
		
	}	
}
