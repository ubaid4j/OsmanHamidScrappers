package com.ubaid.scrapper.AliExpress.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.ubaid.scrapper.AliExpress.product.Product;


@Repository
public interface ProductCDAO extends CrudRepository<Product, Long> {

}
