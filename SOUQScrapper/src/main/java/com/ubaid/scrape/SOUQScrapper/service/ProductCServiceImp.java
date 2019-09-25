package com.ubaid.scrape.SOUQScrapper.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ubaid.scrape.SOUQScrapper.dao.ProductCDAO;
import com.ubaid.scrape.SOUQScrapper.entity.Product;

@Service
public class ProductCServiceImp implements ProductCService
{

	@Autowired
	private ProductCDAO dao;
	
	@Override
	public Product save(Product product) {
		return dao.save(product);
	}

}
