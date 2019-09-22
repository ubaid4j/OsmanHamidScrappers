package com.ubaid.app.model;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.ubaid.app.controller.Controller;

import javafx.application.Platform;
import javafx.scene.control.ProgressBar;

public class Model
{
	Controller controller;
	protected Timer timer;
	
	public Model(Controller controller)
	{
		this.controller = controller;
	}
	
	public void startTimer()
	{
		ExecutorService thread = Executors.newFixedThreadPool(1);
		timer = new Timer(controller);
		thread.execute(timer);
		thread.shutdown();
	}
	
	public void stopTimer()
	{
		timer.setRun(false);
	}

	public void setTime(String time)
	{
		Platform.runLater(new Runnable()
		{
			
			@Override
			public void run()
			{
				controller.getViewController().getLabel().setText(time);
			}
		});
	}
	
	public void stopProgressBar()
	{
		Platform.runLater(new Runnable()
		{
			
			@Override
			public void run()
			{
				controller.getViewController().getProgressBar().setProgress(1);
			}
		});
	}
	
	public void startProgressBar()
	{
		Platform.runLater(new Runnable()
		{
			
			@Override
			public void run()
			{
				controller.getViewController().getProgressBar().setProgress(ProgressBar.INDETERMINATE_PROGRESS);
			}
		});
	}

	public Timestamp toTimeStamp(String date)
	{
		try
		{
		    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		    Date parsedDate = dateFormat.parse(date.trim());
		    Timestamp timestamp = new Timestamp(parsedDate.getTime());
		    return timestamp;
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return null;
		}
	}
}
