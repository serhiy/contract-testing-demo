package com.onlinebrewery.products.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Product {
    private Integer id;
    private String name;
    private String description;
    private Price price;

    @Data
    @AllArgsConstructor
    public static class Price {
        private Double amount;
        private String currency;
    }
}
