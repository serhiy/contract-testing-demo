package com.thousandeyes.pact.orders.service;

import com.thousandeyes.pact.orders.domain.Order;
import com.thousandeyes.pact.orders.integration.ProductClient;
import com.thousandeyes.pact.orders.integration.dto.Product;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class OrdersService {

    private final ProductClient productClient;

    public Order processOrder(Map<Integer, Integer> products, Integer customerId) {
        var productPrices = products.keySet().stream()
                .map(productClient::getProduct)
                .collect(Collectors.toMap(Product::getId, Product::getPrice));

        var totalCost = products.keySet().stream()
                .mapToDouble(id -> costForProduct(products.get(id), productPrices.get(id)))
                .sum();

        return Order.builder()
                .id(UUID.randomUUID().toString())
                .customerId(customerId)
                .products(products)
                .totalCost(totalCost)
                .build();
    }

    private Double costForProduct(Integer quantity, Double price) {
        return quantity * price;
    }
}
