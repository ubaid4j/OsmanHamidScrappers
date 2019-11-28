package com.ubaid.scrape.NoonScrapper.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ubaid.scrape.NoonScrapper.dao.ProductCDAO;
import com.ubaid.scrape.NoonScrapper.entity.Product;
import com.ubaid.scrape.NoonScrapper.service.def.ProductCRUDService;

@Service
public class ProductCRUDServiceImp implements ProductCRUDService {

	@Autowired
	ProductCDAO dao;
	
	@Override
	public Product save(Product product) {
		return dao.save(product);
	}

}
