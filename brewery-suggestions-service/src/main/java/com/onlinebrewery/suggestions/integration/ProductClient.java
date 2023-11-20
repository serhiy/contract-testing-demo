package com.onlinebrewery.suggestions.integration;

import com.onlinebrewery.suggestions.integration.dto.Product;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@FeignClient(name = "product-client", url = "http://localhost:8080")
public interface ProductClient {
	@GetMapping("/v1/products")
	List<Product> getProducts();
}