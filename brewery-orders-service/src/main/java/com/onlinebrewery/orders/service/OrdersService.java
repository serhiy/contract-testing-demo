package com.onlinebrewery.orders.service;

import com.onlinebrewery.orders.domain.Order;
import com.onlinebrewery.orders.integration.ProductsClient;
import com.onlinebrewery.orders.integration.dto.Product;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class OrdersService {

    private final ProductsClient productsClient;

    public Order processOrder(Map<Integer, Integer> products, Integer customerId) {
        var productPrices = products.keySet().stream()
                .map(productsClient::getProduct)
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
