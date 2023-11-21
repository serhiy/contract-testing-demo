package com.onlinebrewery.products.repository;

import com.onlinebrewery.products.domain.Product;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class ProductsRepository {

    private final List<Product> products = List.of(
            new Product(1, "Weiss", "Great beer", new Product.Price(2.75d, "EUR")),
            new Product(2, "Amber", "Also great beer", new Product.Price(2.75d, "EUR")),
            new Product(3, "Stout", "Dark great beer", new Product.Price(3.40d, "EUR"))
    );

    public List<Product> getAll() {
        return products;
    }

    public Optional<Product> getProductById(Integer id) {
        return products.stream().filter(p -> p.getId().equals(id)).findFirst();
    }
}
