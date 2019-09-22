package com.ubaid.app.model.scrapper;

import java.io.IOException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import java.nio.charset.Charset;
import java.util.Map;

import org.jsoup.HttpStatusException;
import org.jsoup.Jsoup;
import org.jsoup.Connection.Method;
import org.jsoup.Connection.Response;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.ubaid.app.controller.Controller;
import com.ubaid.app.model.document.Cookie;
import com.ubaid.app.model.object.NewProducts;
import com.ubaid.app.model.object.Products;

public class Scrapper implements ScrapperI
{

	int index = 0;
	String userAgent = "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/70.0.3538.77 Safari/537.36";
	String referral = "http://www.google.com";

	String img_link = "Not Available";
	
	String baseURL = "";
	Map<String, String> cookie;
	
	
	public Scrapper(Document document, Controller controller) throws Exception
	{		
		try
		{
			
			Elements elements = document.select(".product.ga-product");
			String baseURl = document.baseUri();
			cookie = getArabicCookie(baseURl);
			
			for(Element firstDiv : elements)
			{
				
				try
				{
					Elements _secondDivs = firstDiv.children();
					
					Element secondDiv = _secondDivs.select("div.product-inner").first();
					
					Element atag = secondDiv.getElementsByTag("a").first();
					
					String productLink = atag.absUrl("href");
					
					Element image = null;
					try
					{
						image = atag.getElementsByTag("img").first();						
					}
					catch(NullPointerException exp)
					{
					}
					
					
					String imageURL = null;
					
					try
					{
						imageURL = image.absUrl("src");						
					}
					catch (NullPointerException e)
					{
						try
						{
							Element imageDiv = atag.children().first();
							imageURL = imageDiv.attr("data-image-src");							
						}
						catch(NullPointerException exp)
						{
							
							try
							{
								Document newDoc = Jsoup.connect(productLink).maxBodySize(0).userAgent(userAgent)
										.referrer(referral).cookies(cookie).get();
								Elements tempElements = newDoc.select("meta[property=\"og:images\"]");
								Element tempElement = tempElements.first();
								imageURL = tempElement.attr("content");
//								imageURL = newDoc.select("meta[property='og:images']").first().attr("content");
								imageURL = imageURL.replace("/b/", "/v/");
								
							}
							catch(NullPointerException e3)
							{
								System.out.println("Null pointer exception");
							}
						}
					}
					
					Element _price;
					_price = secondDiv.getElementsByClass("price").first();
					String price;
					
					try
					{
						price = _price.text();							
					}
					catch(NullPointerException exp)
					{
						price = "Price Not Available";
					}
					
					String arabicName = "Name not available";
					
					try
					{
						arabicName = getArabicName(productLink, 0);
						
					}
					catch(Exception exp)
					{
						controller.getQueue().setText("Arabic Name Not found", controller.getQueue().getErrorIndex());
					}
					

					byte[] bytes = arabicName.getBytes(Charset.forName("UTF-8"));
					
					arabicName = new String(bytes, Charset.forName("UTF-8"));
					
					System.out.println(arabicName);
					
					
					//english name						
					String name = "Name not available";
					try
					{
						name = getName(productLink);
					}
					catch(Exception exp)
					{
						
					}
										
					Products product = new NewProducts(arabicName, name, productLink, imageURL, price, "pets", null);
					controller.getQueue().setIndex(product.toString());
					controller.setRecord(product);

					
				}
				catch(Exception exp)
				{
					controller.getQueue().setText(exp, controller.getQueue().getErrorIndex());
				}				
			}
		}
		catch(Exception exp)
		{
			System.out.println(document.head());
			exp.printStackTrace();
			
		}

	}
	
		
	public static void main(String [] args)
	{
		
	}
	
	private static String getName(String url)
	{
		String name = "Name not Available";
		
		try
		{
			String[] splitedData = url.split("/");
			name = splitedData[splitedData.length - 2];
		}
		catch(Exception exp)
		{
			//
		}
		
		return name;
	}

	
	
	private String getArabicName(String url, int counter)
	{
		String name = "Not Availabl"
				+ "";

		
		if(counter > 4)
			return "Not Available";

		try
		{
			Document doc = Jsoup.connect(url).maxBodySize(0).userAgent(userAgent).referrer(referral).cookies(cookie).get();
			
			try
			{
				Element _name = doc.select("#name").first();				
				name = _name.text();
			}
			catch(NullPointerException exp)
			{
				
			}
			
		}
		catch(HttpStatusException e1)
		{
			e1.printStackTrace();
			try
			{
				Thread.sleep(10000);
			}
			catch (InterruptedException e)
			{
				e.printStackTrace();
			}
			
			name = getArabicName(url, ++counter);
			
		}
		catch(SocketTimeoutException | UnknownHostException exp)
		{
			exp.printStackTrace();
			try
			{
				Thread.sleep(1000);
			}
			catch (InterruptedException e)
			{
				e.printStackTrace();
			}
			
			name = getArabicName(url, ++counter);
		}
		catch(IOException exp)
		{
			exp.printStackTrace();
			try
			{
				Thread.sleep(1000);
			}
			catch (InterruptedException e)
			{
				e.printStackTrace();
			}
			
			name = getArabicName(url, ++counter);
			
		}
		catch(Exception exp)
		{
			exp.printStackTrace();
			return name;
		}
		return name;
	}

		
	private Map<String, String> getArabicCookie(String url)
	{
		Response res = null;
		Map<String, String> cookie = null;
		try
		{
			res = Jsoup
				    .connect(url)
				    .method(Method.GET)
				    .execute();
			cookie = res.cookies();
			
			cookie.put("iher-pref1", Cookie.ar_ln);

		}
		catch(HttpStatusException e1)
		{
			e1.printStackTrace();
			try
			{
				Thread.sleep(10000);
			}
			catch (InterruptedException e)
			{
				e.printStackTrace();
			}
			
			cookie = getArabicCookie(url);
							
		}
		catch(SocketTimeoutException | UnknownHostException exp)
		{
			exp.printStackTrace();
			try
			{
				Thread.sleep(1000);
			}
			catch (InterruptedException e)
			{
				e.printStackTrace();
			}
			
			cookie = getArabicCookie(url);
			
		}
		catch (IOException exp)
		{
			exp.printStackTrace();
			try
			{
				Thread.sleep(10000);
			}
			catch (InterruptedException e)
			{
				e.printStackTrace();
			}

			cookie = getArabicCookie(url);

		}			
			
		return cookie;

	}

	
	
		
}
