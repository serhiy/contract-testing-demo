package com.thousandeyes.pact.suggestions;

import au.com.dius.pact.consumer.MockServer;
import au.com.dius.pact.consumer.dsl.PactDslWithProvider;
import au.com.dius.pact.consumer.junit5.PactConsumerTestExt;
import au.com.dius.pact.consumer.junit5.PactTestFor;
import au.com.dius.pact.core.model.V4Pact;
import au.com.dius.pact.core.model.annotations.Pact;
import com.thousandeyes.pact.suggestions.integration.ProductClient;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Map;

import static au.com.dius.pact.consumer.dsl.LambdaDsl.*;

@ExtendWith(PactConsumerTestExt.class)
@ExtendWith(SpringExtension.class)
@SpringBootTest
public class ProductClientPactTest {

    @Autowired
    private ProductClient productClient;

    @Pact(provider = "products-service", consumer = "suggestions-service")
    public V4Pact getProducts(PactDslWithProvider builder) {
        return builder
                .given("there are products")
                .uponReceiving("a request for products")
                .path("/v1/products")
                .method("GET")
                .willRespondWith()
                .status(200)
                .headers(Map.of("Content-Type", "application/json"))
                .body(newJsonArrayMinLike(1, a -> a.object(o -> {
                    o.integerType("id");
                    o.stringType("name");
                })).build())
                .toPact(V4Pact.class);
    }

    @Test
    @PactTestFor(pactMethod = "getProducts", port="8080")
    void getProducts(MockServer mockServer) {
        productClient.getProducts();
    }
}