package com.ubaid.app.controller;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.Vector;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.ubaid.app.model.DataSource;
import com.ubaid.app.model.Model;
import com.ubaid.app.model.googleTranslate.OpenPage;
import com.ubaid.app.model.logger.Queue;
import com.ubaid.app.model.object.Products;
import com.ubaid.app.view.View;

public class Controller
{
	private final Queue queue;
	private View view;
	private Model model;
	private ExecutorService intiater_thread;
	private OpenPage openPage;
	private static List<Products> products = new Vector<>();
	private static boolean isPause;
	private static final String query = "INSERT INTO products(name, nameE, productLink, imageLink, price, type) VALUES(?, ?, ?, ?, ?, ?)";
	private PreparedStatement statement;
	private Connection con;
	private ExecutorService service = Executors.newFixedThreadPool(1);



	
	public Controller(Queue queue, View view)
	{
		this.queue = queue;
		this.setViewController(view);
		
		con = DataSource.getConnection();
		
		try
		{
			statement = con.prepareStatement(query);
		}
		catch (SQLException e)
		{
			queue.setText(e, queue.getErrorIndex());
		}
		

		
		runInitiater();
	}
	
	public static List<Products> getList()
	{
		return products;
	}
	
	private void runInitiater()
	{
		intiater_thread = Executors.newFixedThreadPool(1);
		intiater_thread.execute(new Initiater(this));
		intiater_thread.shutdown();
	}


	public Queue getQueue() {
		return queue;
	}

	public View getViewController() {
		return view;
	}

	public void setViewController(View view) {
		this.view = view;
	}

	public Model getModel() {
		return model;
	}

	public void setModel(Model model) {
		this.model = model;
	}

	public OpenPage getOpenPage() {
		return openPage;
	}

	public void setOpenPage(OpenPage openPage) {
		this.openPage = openPage;
	}

//	public synchronized void setRecord(Products product)
//	{
//		int id = xmldom.createRecord();
//		xmldom.setName(product.getProductName(), id);
//		xmldom.setNameInEnglish(product.getProductNameInEnglish(), id);
//		xmldom.setProductLink(product.getProductLink(), id);
//		xmldom.setImageLink(product.getImageLink(), id);
//		xmldom.setPrice(product.getProductPrice(), id);
//
//	}

	public static boolean isPause() {
		return isPause;
	}

	public static void setPause(boolean isPause) {
		Controller.isPause = isPause;
	}
	
	
	public void setRecord(Products product)
	{
		
		service.execute(new Runnable()
		{
			
			@Override
			public void run()
			{
				try
				{
					statement.setString(1, product.getProductName());
					statement.setString(2, product.getProductNameInEnglish());
					statement.setString(3, product.getProductLink());
					statement.setString(4, product.getImageLink());
					statement.setString(5, product.getProductPrice());
					statement.setString(6, product.getProdctType());
//					statement.setString(7, product.getBrand());
					statement.execute();
				}
				catch(SQLException exp)
				{
					getQueue().setText(exp, getQueue().getErrorIndex());
				}
				
			}
		});

	}

}
