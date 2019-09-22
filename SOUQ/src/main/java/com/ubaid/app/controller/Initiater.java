package com.ubaid.app.controller;



import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.ubaid.app.model.Model;
import com.ubaid.app.model.googleTranslate.OpenPage;
import com.ubaid.app.model.logger.LogViewer;
import javafx.application.Platform;

public class Initiater implements Runnable
{

	Controller controller;
	
	public Initiater(Controller controller)
	{
		this.controller = controller;
	}
	
	@Override
	public void run()
	{
		setSystemProperties();
		setModel();
		embedLogger();
//		setTranslator();
		init();
	}
	
	@SuppressWarnings("unused")
	private void setTranslator()
	{
		ExecutorService thread = Executors.newFixedThreadPool(1);
		OpenPage translator = new OpenPage(controller);
		thread.execute(translator);
		thread.shutdown();
		controller.setOpenPage(translator);
	}
	
	private void setModel()
	{
		controller.setModel(new Model(controller));
	}
	
	private void init()
	{
		try
		{
			ExecutorService thread = Executors.newFixedThreadPool(1);
			thread.execute(new Handover());
			thread.shutdown();

		}
		catch(Exception exp)
		{
			controller.getQueue().setText(exp, controller.getQueue().getErrorIndex());
		}
	}
	
	
	private void embedLogger()
	{
		Platform.runLater(new Runnable()
		{
			
			@Override
			public void run()
			{
				try
				{
					LogViewer logViewer = new LogViewer(controller);
					controller.getViewController().getBorderPane().setCenter(logViewer);
				}
				catch (Exception e)
				{
					e.printStackTrace();
				}
				
			}
		});
		

	}
	
	private void setSystemProperties()
	{
		try
		{
			//changes
			System.setProperty("webdriver.chrome.driver", ".//Resource//chromedriver.exe");
			System.setProperty("webdriver.gecko.driver", "./Resource/geckodriver.exe");		
	
		}
		catch(Exception exp)
		{
			controller.getQueue().setText(exp, controller.getQueue().getErrorIndex());
		}
	}
	
	
	private class Handover implements Runnable
	{

		@Override
		public void run()
		{
			initializeApp();
		}
		
		private void initializeApp()
		{
			try
			{
				new AppHandler(controller);
			}
			catch(Exception exp)
			{
				controller.getQueue().setText(exp, controller.getQueue().getErrorIndex());
			}
		}
		
	}
}
