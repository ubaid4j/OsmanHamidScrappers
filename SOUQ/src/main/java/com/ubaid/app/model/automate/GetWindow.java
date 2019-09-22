package com.ubaid.app.model.automate;

import java.util.ArrayList;
import java.util.List;

import org.jsoup.nodes.Document;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import com.ubaid.app.controller.Controller;
import com.ubaid.app.model.logger.Queue;

public class GetWindow
{
	
	private Document document;
	
	@SuppressWarnings("unused")
	private final int MAX_VALUE = 5555;
	
	public GetWindow(String url, Controller controller)
	{
		try
		{
			
		}
		catch(Exception exp)
		{
			controller.getQueue().setText(exp, controller.getQueue().getErrorIndex());
		}
		
	}
	
	public Document getDocument()
	{
		return document;
	}
	
	public void setDocument(Document document)
	{
		this.document = document;
	}

	
	@SuppressWarnings("unused")
	private int getTotalIteration(WebDriver driver, Queue q)
	{
		try
		{
			WebElement element = driver.findElement(By.cssSelector("header.page-title.category-title p.amount.alpha"));
			String text = element.getText();
			int number = getNumber(text);
			int iteration = number / 12;
			return iteration;
		}
		catch(Exception exp)
		{
			q.setText(exp, q.getErrorIndex());
			return -1;
		}
	}
	
	
	private int getNumber(String text) throws NumberFormatException
	{
		return getMax(getList(text));
	}
	
	private List<Integer> getList(String text) throws NumberFormatException
	{
		String[] array = text.split(" ");
		List<Integer> list = new ArrayList<>();
		for(int i = 0; i < array.length; i++)
		{
			if(isNumber(array[i]))
				list.add(Integer.parseInt(array[i]));
		}

		return list;
	}
	
	private int getMax(List<Integer> list)
	{
		int max = 0;
		for(int i = 0; i < list.size(); i++)
		{
			if(list.get(i) > max)
				max = list.get(i);
		}
		
		return max;
	}
	
	private boolean isNumber(String number)
	{
		char[] numbers = {'1', '2', '3', '4', '5', '6', '7', '8', '9', '0'};
		int size_of_text = number.length();
		char[] char_array = number.toCharArray();
		
		for(int i = 0; i < size_of_text; i++)
		{
			int count = 0;
			for(int j = 0; j < 10; j++)
			{
				if(char_array[i] == numbers[j])
					break;
				
				if(++count == 10)
					return false;
			}
		}
		
		return true;
	}	
	
	
	@SuppressWarnings("unused")
	private void scroll(WebDriver driver)
	{
		try
		{
			WebElement element = driver.findElement(By.className("srp-pagination"));
			((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
			Thread.sleep(500); 
		}
		catch(Exception exp)
		{
			
		}
	}
}
