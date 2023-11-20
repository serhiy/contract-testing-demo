package com.onlinebrewery.orders.controller;

import com.onlinebrewery.orders.domain.Order;
import com.onlinebrewery.orders.dto.OrderRequest;
import com.onlinebrewery.orders.service.OrdersService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/orders")
@RequiredArgsConstructor
public class OrdersController {

    private final OrdersService ordersService;

    @PostMapping
    public ResponseEntity<Order> processOrder(@RequestBody OrderRequest order) {
        return ResponseEntity.ok(ordersService.processOrder(order.getProducts(), order.getCustomerId()));
    }
}
