package com.thousandeyes.pact.product.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.thousandeyes.pact.product.domain.Product;
import com.thousandeyes.pact.product.repository.ProductsRepository;

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
