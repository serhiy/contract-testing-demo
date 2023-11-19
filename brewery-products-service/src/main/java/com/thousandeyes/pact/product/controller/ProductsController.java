package com.thousandeyes.pact.product.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.thousandeyes.pact.product.domain.Product;
import com.thousandeyes.pact.product.service.ProductsService;

import java.util.List;

@RestController
@RequestMapping("/v1/products")
@RequiredArgsConstructor
public class ProductsController {

    private final ProductsService productsService;

    @GetMapping
    public ResponseEntity<List<Product>> getProducts() {
        return ResponseEntity.ok(productsService.getProducts());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> getProduct(@PathVariable Integer id) {
        return ResponseEntity.of(productsService.getProduct(id));
    }
}