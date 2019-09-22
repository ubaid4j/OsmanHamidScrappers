package com.ubaid.app.model;

import com.ubaid.app.controller.Controller;

public class Timer implements Runnable
{

	long seconds;
	protected Controller controller;
	private boolean isRun = true;
	
	public Timer(Controller controller)
	{
		this.controller = controller;
	}
	
	
	@Override
	public void run()
	{
		while(isRun)
		{
			seconds++;
			try
			{
				Thread.sleep(1000);
				controller.getModel().setTime(getTime());
			}
			catch(Exception exp)
			{
				exp.printStackTrace();
			}
		}
	}
	
	private int getHours()
	{
		int hours = (int) (seconds / 3600);
		return hours;
	}
	
	private int getMinutes()
	{
		long remainingSeconds = seconds - (getHours() * 3600);
		int minutes = (int) (remainingSeconds / 60);
		return minutes;
	}
	
	private int getSeconds()
	{
		return (int) (seconds - ((getMinutes() * 60) + (getHours() * 3600)));
	}
	
	public String getTime()
	{
		return String.format("%02d:%02d:%02d", getHours(), getMinutes(), getSeconds());
	}


	public boolean isRun() {
		return isRun;
	}


	public void setRun(boolean isRun) {
		this.isRun = isRun;
	}

}
