package com.ubaid.app;


import com.ubaid.app.controller.Controller;
import com.ubaid.app.model.logger.Queue;



public class IHURB_SCRAPPER_GUI_LESS
{
	public static void main(String [] args)
	{
		Queue queue = new Queue();
		try
		{
			new Controller(queue);			
		}
		catch(Exception exp)
		{
			System.out.println("Try again");
			System.exit(0);
		}
	}
}
