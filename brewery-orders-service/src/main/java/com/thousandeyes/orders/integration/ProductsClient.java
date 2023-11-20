package com.thousandeyes.pact.orders.integration;

import com.thousandeyes.pact.orders.integration.dto.Product;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "product-client", url = "http://localhost:8080", primary = false)
public interface ProductsClient {
	@GetMapping("/v1/products/{id}")
	Product getProduct(@PathVariable Integer id);
}