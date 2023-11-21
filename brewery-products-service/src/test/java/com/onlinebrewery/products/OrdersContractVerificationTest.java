package com.onlinebrewery.products;

import au.com.dius.pact.provider.junit5.PactVerificationContext;
import au.com.dius.pact.provider.junitsupport.Consumer;
import au.com.dius.pact.provider.junitsupport.Provider;
import au.com.dius.pact.provider.junitsupport.State;
import au.com.dius.pact.provider.junitsupport.loader.PactBroker;
import au.com.dius.pact.provider.spring.junit5.PactVerificationSpringProvider;
import org.junit.jupiter.api.TestTemplate;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import com.onlinebrewery.products.domain.Product;
import com.onlinebrewery.products.repository.ProductsRepository;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@Provider("products-service")
@Consumer("orders-service")
@PactBroker
public class OrdersContractVerificationTest {

    @MockBean
    private ProductsRepository productsRepository;

    @TestTemplate
    @ExtendWith(PactVerificationSpringProvider.class)
    void pactVerificationTestTemplate(PactVerificationContext context) {
        context.verifyInteraction();
    }

    @State("product is found")
    public void existingProduct() {
        var product = new Product(1, "test", "empty", new Product.Price(1.00d, "EUR"));
        when(productsRepository.getProductById(any())).thenReturn(Optional.of(product));
    }

    @State("product is not found")
    public void productNotFound() {
        when(productsRepository.getProductById(any())).thenReturn(Optional.empty());
    }
}
