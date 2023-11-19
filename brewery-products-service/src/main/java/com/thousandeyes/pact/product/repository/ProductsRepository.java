package com.thousandeyes.pact.product.repository;

import com.thousandeyes.pact.product.domain.Product;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class ProductsRepository {

    private final List<Product> products = List.of(
            new Product(1, "Weiss", "Great beer", 2.75d),
            new Product(2, "Amber", "Also great beer", 2.75d),
            new Product(3, "Stout", "Dark great beer", 3.40d)
    );

    public List<Product> getAll() {
        return products;
    }

    public Optional<Product> getProductById(Integer id) {
        return products.stream().filter(p -> p.getId().equals(id)).findFirst();
    }
}
