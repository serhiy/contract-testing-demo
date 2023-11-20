package com.onlinebrewery.products.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.onlinebrewery.products.domain.Product;
import com.onlinebrewery.products.repository.ProductsRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductsService {

    private final ProductsRepository repository;

    public List<Product> getProducts() {
        return repository.getAll();
    }

    public Optional<Product> getProduct(Integer id) {
        return repository.getProductById(id);
    }
}
