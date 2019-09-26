package com.ubaid.scrape.SOUQScrapper.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.ubaid.scrape.SOUQScrapper.entity.Product;

@Repository
public interface ProductCDAO extends CrudRepository<Product, Long> {

}
