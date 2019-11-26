package com.ubaid.app;

import java.sql.Connection;


import com.mysql.cj.jdbc.MysqlDataSource;


public class DataSource
{
	private static Connection connection = createConnection();
	
	
	
	private DataSource()
	{
		
	}
	
	public static void main(String [] args)
	{
		getConnection();
	}
	
	public static Connection getConnection()
	{
		return connection;
	}
	
	private static Connection createConnection()
	{
		
		
		MysqlDataSource dataSource = new MysqlDataSource();
		
		Connection connection = null;
		try
		{
			dataSource.setAllowPublicKeyRetrieval(true);
			dataSource.setUseSSL( true );
		    dataSource.setServerTimezone("GMT");
		    dataSource.setServerName("localhost");
		    dataSource.setDatabaseName("noon");
		    dataSource.setPortNumber(3306);
		    dataSource.setUser("root");
		    dataSource.setPassword("password");
		    dataSource.setRewriteBatchedStatements(true);
		    connection = dataSource.getConnection();
		    System.out.println("Connected");
			
		}
		catch(Exception exp)
		{
			exp.printStackTrace();
		}
		
	    return connection;			

	}
}
