package com.ubaid.scrape.NoonScrapper.service.def;

import java.util.List;

import com.ubaid.scrape.NoonScrapper.entity.EnArNodes;
import com.ubaid.scrape.NoonScrapper.entity.Product;

public interface ProductScrapperService {
	public List<Product> getProducts(EnArNodes node);
}
