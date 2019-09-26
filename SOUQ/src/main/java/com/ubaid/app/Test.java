package com.ubaid.app;



import java.net.URL;

public class Test 
{
	public static void main(String [] args)
	{
		try
		{
			Test app = new Test();	
			app.app();
		}
		catch(Exception exp)
		{
			exp.printStackTrace();
		}
		
	}
	
	public void app()
	{
		try
		{
			URL url = getClass().getResource("View.fxml");
			System.err.println(url);
		}
		catch(Exception exp)
		{
			exp.printStackTrace();
		}
	}
}
