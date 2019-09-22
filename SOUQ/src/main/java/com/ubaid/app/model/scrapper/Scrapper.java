package com.ubaid.app.model.scrapper;

import java.util.ArrayList;
import java.util.List;



import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.ubaid.app.controller.Controller;
import com.ubaid.app.model.logger.Queue;
import com.ubaid.app.model.object.NewProducts;
import com.ubaid.app.model.object.Products;

public class Scrapper implements ScrapperI
{

	private List<Products> products;
	int index = 0;
	WebDriver driver;
	
	public Scrapper(Document document, Queue q) throws Exception
	{		
		products = new ArrayList<>();
		try
		{
			try
			{
				driver  = new FirefoxDriver();		

			}
			catch(WebDriverException exp)
			{
				driver = new ChromeDriver();

			}
			
			Elements product_divs = document.getElementsByClass("single-item");
			
			
			for(Element product : product_divs)
			{
				while(Controller.isPause())
				{
					Thread.sleep(2000);
				}
				String name = "Not Available";
				String img_link = "Not Available";
				String product_link = "Not Available";
				String product_price = "Not Available";
				String product_name_in_english = "Not Available";
				
				try
				{
					product_name_in_english = product.attr("data-name");					
				}
				catch(Exception exp)
				{
					
				}
				
				try
				{
					Element _img_link = product.getElementsByTag("img").first();
					img_link = _img_link.absUrl("src");
					

//					assert(img_link.contains("blank.gif"));
					
					if(img_link.contains("blank.gif"))
					{
						img_link = _img_link.attr("data-src");
					}
				}
				catch(Exception exp)
				{
					
				}
				
				try
				{
					Element _product_link = product.getElementsByClass("itemlink").first();
					product_link = _product_link.absUrl("href");
				}
				catch(Exception exp)
				{
					
				}
				
				try
				{
					Element _product_price = product.getElementsByClass("itemPrice").first();
					product_price = _product_price.text();
				}
				catch(Exception exp)
				{
					
				}
				
				try
				{
					name = getArabicName(product_link, 0);
				}
				catch(Exception exp)
				{
					exp.printStackTrace();
				}
				
				String type = "BakeWare";
				
					
				
				
				Products product_ = new NewProducts(name, product_name_in_english, product_link, img_link, product_price, type);
				q.setIndex(product_.toString());
				Controller.getList().add(product_);
				products.add(product_);
				
				//adding in the SOUQ database
			}
			
			
		}
		catch(Exception exp)
		{
			System.out.println(document.head());
			exp.printStackTrace();
			
		}
		finally
		{
			driver.close();
		}

	}
	
	@Override
	public List<Products> getProducts()
	{
		return products;
	}
		
	
	private String getArabicName(String url, int counter)
	{
		String name = "Not Available";
		
		try
		{
			Document doc = Jsoup.connect(url).get();
		
			Element region = doc.select("a.lang-link").first();
			String arabic_link = region.absUrl("href");
			name = arabic_name(arabic_link, 0);
		}
		catch(Exception exp)
		{
			try
			{
				Thread.sleep(1000);
			}
			catch(InterruptedException e)
			{
				e.printStackTrace();
			}
			
			exp.printStackTrace();
			if(counter++ == 2)
				return name;
			name = getArabicName(url, counter);
		}

		return name;
	}

	private String arabic_name(String url, int time)
	{
		String name = "Not Available";
		
		
		try
		{
			if(driver == null)
			{
				try
				{
					driver.close();
					try
					{
						driver = new FirefoxDriver();					

					}
					catch(WebDriverException exp)
					{
						driver = new ChromeDriver();

					}

				}
				catch(Exception exp)
				{
					driver = new FirefoxDriver();					
					
				}
			}
			
			if(index++ == 50)
			{
				driver.close();
				try
				{
					driver = new FirefoxDriver();	
				}
				catch(WebDriverException exp)
				{
					driver = new ChromeDriver();

				}
			}
			
			
			try
			{
				driver.get(url);
				WebElement title_div = driver.findElement(By.className("product-title"));
				WebElement title = title_div.findElement(By.tagName("h1"));
				name = title.getText();
				
			}
			catch(Exception exp)
			{
				try
				{
					driver.close();
					try
					{
						driver = new FirefoxDriver();					

					}
					catch(WebDriverException exp2)
					{
						driver = new ChromeDriver();

					}

				}
				catch(Exception exp3)
				{
					driver = new FirefoxDriver();					
					
				}

				return name;
			}
		}
		catch(IllegalArgumentException exp)
		{
			return name;
		}
		catch(Exception exp)
		{
			try
			{
				Thread.sleep(1000);
			}
			catch(InterruptedException e)
			{
				e.printStackTrace();
			}
			
			exp.printStackTrace();
		}

		return name;	
	}
}
