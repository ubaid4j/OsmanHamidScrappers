package com.ubaid.scrape.NoonScrapper.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.ubaid.scrape.NoonScrapper.service.def.FileService;

@Service
public class FileServiceImp implements FileService {

	@Value("${scrape.categories}")
	private List<String> categories;

	
	
	@Override
	public List<String> getAllCategories() {
		return categories;
	}

}
