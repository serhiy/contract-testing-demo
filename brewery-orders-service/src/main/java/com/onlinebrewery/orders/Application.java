package com.onlinebrewery.orders;

import com.onlinebrewery.orders.integration.ProductsClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class Application {

	@Autowired
	private ProductsClient client;

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

}
