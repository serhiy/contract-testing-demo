package com.thousandeyes.pact.orders.dto;

import lombok.Data;

import java.util.Map;

@Data
public class OrderRequest {
    private Map<Integer, Integer> products;
    private Integer customerId;
}
