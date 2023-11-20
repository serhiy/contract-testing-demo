package com.thousandeyes.pact.product.controller;

import com.thousandeyes.pact.product.domain.Product;
import com.thousandeyes.pact.product.repository.ProductsRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;
import java.util.Optional;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
@ContextConfiguration(classes = { ProductsControllerTest.TestConfig.class })
class ProductsControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ProductsRepository productsRepository;

    @Test
    void shouldReturnProductsList() throws Exception {
        when(productsRepository.getAll()).thenReturn(
                List.of(new Product(1, "Test Name", "Test Description", 0.50d))
        );

        mockMvc.perform(get("/v1/products")
                        .contentType("application/json"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$.[0].id", is(1)))
                .andExpect(jsonPath("$.[0].name", is("Test Name")))
                .andExpect(jsonPath("$.[0].description", is("Test Description")))
                .andExpect(jsonPath("$.[0].price", is(0.50d)));
    }

    @Test
    void shouldReturnProductsDetails() throws Exception {
        when(productsRepository.getProductById(1)).thenReturn(
                Optional.of(new Product(1, "Test Name", "Test Description", 0.50d))
        );

        mockMvc.perform(get("/v1/products/{id}", 1)
                        .contentType("application/json"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.name", is("Test Name")))
                .andExpect(jsonPath("$.description", is("Test Description")))
                .andExpect(jsonPath("$.price", is(0.50d)));
    }

    @Test
    void shouldReturn404() throws Exception {
        when(productsRepository.getProductById(1)).thenReturn(
                Optional.empty()
        );

        mockMvc.perform(get("/v1/products/{id}", 1)
                        .contentType("application/json"))
                .andExpect(status().isNotFound());
    }

    @Configuration
    @TestConfiguration
    static class TestConfig {
        @Bean
        @Primary
        public ProductsRepository productsRepository() {
            return mock(ProductsRepository.class);
        }
    }
}