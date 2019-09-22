package com.ubaid.app.model.googleTranslate;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import com.ubaid.app.controller.Controller;

public class OpenPage implements Runnable
{

	protected WebDriver driver;
	protected Controller controller;
	
	public OpenPage(Controller controller)
	{
		this.controller = controller;
	}
	
	@Override
	public void run()
	{
		//1. open google page
		driver = openGooglePage();
		
	}
	
	public synchronized String getTranslatedStuff(String str)
	{
		try
		{
			WebElement source = driver.findElement(By.id("source"));
			source.sendKeys(str);
			Thread.sleep(3000);
			WebElement target = driver.findElement(By.id("gt-res-dir-ctr"));
			Thread.sleep(100);
			String translated = target.getText();
			source.clear();
			Thread.sleep(100);
			controller.getQueue().setText(String.format("Translation: Arabic: %s\tEnglish: %s", str, translated), controller.getQueue().getDuplicationIndex());
			return translated;
		}
		catch(Exception exp)
		{
			exp.printStackTrace();
		}
		
		return null;
	}
	
	
	public WebDriver openGooglePage()
	{
		try
		{
			WebDriver driver = new ChromeDriver();		
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			driver.navigate().to("https://translate.google.com.pk/");
			return driver;
		}
		catch(Exception exp)
		{
			exp.printStackTrace();
		}

		return null;
	}
	
}
