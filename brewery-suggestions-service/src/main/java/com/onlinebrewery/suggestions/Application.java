package com.onlinebrewery.suggestions;

import com.onlinebrewery.suggestions.integration.ProductClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class Application {

	@Autowired
	private ProductClient client;

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

}
