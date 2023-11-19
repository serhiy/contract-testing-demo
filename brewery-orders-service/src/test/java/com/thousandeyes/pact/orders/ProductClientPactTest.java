package com.thousandeyes.pact.orders;

import au.com.dius.pact.consumer.MockServer;
import au.com.dius.pact.consumer.dsl.PactDslWithProvider;
import au.com.dius.pact.consumer.junit5.PactConsumerTestExt;
import au.com.dius.pact.consumer.junit5.PactTestFor;
import au.com.dius.pact.core.model.V4Pact;
import au.com.dius.pact.core.model.annotations.Pact;
import com.thousandeyes.pact.orders.integration.ProductClient;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Map;

import static au.com.dius.pact.consumer.dsl.LambdaDsl.newJsonBody;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

@ExtendWith(PactConsumerTestExt.class)
@ExtendWith(SpringExtension.class)
@SpringBootTest
public class ProductClientPactTest {

    @Autowired
    private ProductClient productClient;

    @Pact(provider = "products-service", consumer = "orders-service")
    public V4Pact getExistingProduct(PactDslWithProvider builder) {
        return builder
                .given("product is found")
                .uponReceiving("a request for product")
                .path("/v1/products/1")
                .method("GET")
                .willRespondWith()
                .status(200)
                .headers(Map.of("Content-Type", "application/json"))
                .body(newJsonBody(o -> {
                    o.integerType("id");
                    o.stringType("name");
                    o.decimalType("price");
                }).build())
                .toPact(V4Pact.class);
    }

    @Test
    @PactTestFor(pactMethod = "getExistingProduct", port="8080")
    void getExistingProduct(MockServer mockServer) {
        productClient.getProduct(1);
    }

    @Pact(provider = "products-service", consumer = "orders-service")
    public V4Pact getNonExistingProduct(PactDslWithProvider builder) {
        return builder
                .given("product is not found")
                .uponReceiving("a request for product")
                .path("/v1/products/2")
                .method("GET")
                .willRespondWith()
                .status(404)
                .toPact(V4Pact.class);
    }

    @Test
    @PactTestFor(pactMethod = "getNonExistingProduct", port="8080")
    void getNonExistingProduct(MockServer mockServer) {
        try {
            productClient.getProduct(2);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}