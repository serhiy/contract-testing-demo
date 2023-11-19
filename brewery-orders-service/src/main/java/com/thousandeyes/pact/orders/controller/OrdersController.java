package com.thousandeyes.pact.orders.controller;

import com.thousandeyes.pact.orders.domain.Order;
import com.thousandeyes.pact.orders.dto.OrderRequest;
import com.thousandeyes.pact.orders.service.OrdersService;
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
