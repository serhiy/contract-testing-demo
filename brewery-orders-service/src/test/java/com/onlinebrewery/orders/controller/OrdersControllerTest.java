package com.thousandeyes.pact.orders.controller;

import com.thousandeyes.pact.orders.TestDataReader;
import com.thousandeyes.pact.orders.integration.ProductsClient;
import com.thousandeyes.pact.orders.integration.dto.Product;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.hasEntry;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
@ContextConfiguration(classes = { OrdersControllerTest.TestConfig.class })
class OrdersControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ProductsClient productsClient;

    @Test
    void shouldReturnProductsDetails() throws Exception {
        when(productsClient.getProduct(1)).thenReturn(
                new Product(1, "Test Name", 0.50d)
        );

        mockMvc.perform(post("/v1/orders")
                        .contentType("application/json")
                        .content(TestDataReader.readFile("create-order.json")))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.customerId", is(543)))
                .andExpect(jsonPath("$.products", hasEntry("1", 2)))
                .andExpect(jsonPath("$.totalCost", is(1.00d)));
    }

    @Configuration
    @TestConfiguration
    static class TestConfig {
        @Bean
        @Primary
        public ProductsClient productsClient() {
            return mock(ProductsClient.class);
        }
    }
}