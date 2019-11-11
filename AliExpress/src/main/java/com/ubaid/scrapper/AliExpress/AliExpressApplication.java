package com.ubaid.scrapper.AliExpress;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.ubaid.scrapper.AliExpress.model.Scrapper;

@SpringBootApplication
public class AliExpressApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(AliExpressApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Scrapper scrapper = new Scrapper();
	}

}
