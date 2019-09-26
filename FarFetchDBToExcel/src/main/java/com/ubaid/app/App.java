package com.ubaid.app;

import java.io.File;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.LinkedList;
import java.util.List;
import com.ubaid.app.model.export.ToXLS;
import com.ubaid.app.model.object.NewProducts;
import com.ubaid.app.model.object.Products;

public class App
{
	
	private static final String query = "select id, name, name_in_english, brand, product_link, image_link, type, price from product;";
	
	public static void main(String [] args)
	{
		App app = new App();
		List<Products> products = app.getRecords();
		
		File file = new File("file" + products.size() + ".xlsx");
		
		try
		{
			new ToXLS(file, products);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
	
	List<Products> getRecords()
	{
		
		List<Products> list = new LinkedList<Products>();
		Products product = null;
		
		try
		{

					
			Connection con = DataSource.getConnection();
			PreparedStatement statement = con.prepareStatement(query);
			ResultSet rs = statement.executeQuery();
						
			while(rs.next())
			{
				product = new NewProducts(rs.getString(2), rs.getString(3), rs.getString(5), rs.getString(6), rs.getString(8), rs.getString(7), rs.getString(4));
				list.add(product);				
			}
			
		}
		catch(Exception exp)
		{
			exp.printStackTrace();
		}
		
		return list;
	}
}




