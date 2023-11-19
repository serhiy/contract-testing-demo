package com.thousandeyes.pact.orders.domain;

import lombok.*;

import java.util.Map;

@Value
@Builder
public class Order {
    String id;
    Integer customerId;
    Map<Integer, Integer> products;
    Double totalCost;
}
