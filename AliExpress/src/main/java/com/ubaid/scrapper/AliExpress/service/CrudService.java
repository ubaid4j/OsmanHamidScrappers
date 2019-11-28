package com.ubaid.scrapper.AliExpress.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ubaid.scrapper.AliExpress.dao.ProductCDAO;
import com.ubaid.scrapper.AliExpress.product.Product;
import com.ubaid.scrapper.AliExpress.service.def.ProductCRUDService;

@Service
public class CrudService implements ProductCRUDService {

	@Autowired
	private ProductCDAO dao;
	
	@Override
	public Product save(Product product)
	{
		return dao.save(product);
	}

}
