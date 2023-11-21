package com.onlinebrewery.orders.integration.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Product {
    private Integer id;
    private String name;
    private Price price;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Price {
        private Double amount;
        private String currency;
    }
}
