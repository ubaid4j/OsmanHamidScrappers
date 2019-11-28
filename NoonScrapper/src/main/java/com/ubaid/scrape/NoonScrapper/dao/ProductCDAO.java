package com.ubaid.scrape.NoonScrapper.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.ubaid.scrape.NoonScrapper.entity.Product;

@Repository
public interface ProductCDAO extends CrudRepository<Product, Long> {

}
