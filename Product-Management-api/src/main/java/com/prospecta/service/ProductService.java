// Declared the ProductService class as a Spring @Service to handle business logic for product management.
package com.prospecta.service;

import java.util.Arrays;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import com.prospecta.entity.Product;
import com.prospecta.exception.AddProductException;

// Marked ProductService class as a @Service to make it a Spring-managed bean.
@Service
public class ProductService {

    // Injected the FakeStore API base URL using @Value annotation.
    @Value("${fakestore.api.url}")
    private String apiURL;

    // Declared RestTemplate for making HTTP calls to the FakeStore API.
    private final RestTemplate restTemplate;

    // Used constructor-based injection to provide RestTemplate instance.
    @Autowired
    public ProductService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    // Implemented method to fetch products by category from the FakeStore API.
    public List<Product> getProductsByCategory(String category) {
        // Constructed URL to fetch products by category.
        String url = apiURL + "/category/" + category;

        // Made GET request to FakeStore API to retrieve products of a specific category.
        ResponseEntity<Product[]> response = restTemplate.getForEntity(url, Product[].class);

        // Converted the array of products to a list and returned it.
        return Arrays.asList(response.getBody());
    }
    
    // Implemented method to add a new product to the FakeStore API.
    public Product addProduct(Product product) {
        // Set the base URL for the FakeStore API.
        String url = apiURL;

        // Created HttpHeaders and set content type as JSON.
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        
        // Created an HttpEntity object containing the product and headers.
        HttpEntity<Product> request = new HttpEntity<>(product, headers);

        // Made POST request to FakeStore API to add the product.
        ResponseEntity<Product> response = restTemplate.postForEntity(url, request, Product.class);

        // Checked the response status; if it’s not CREATED (201), throw an exception.
        if (response.getStatusCode() != HttpStatus.CREATED) {
            // Threw custom exception if product addition failed.
            throw new AddProductException("Failed to add product. Status code: " + response.getStatusCode());
        }

        // Returned the body of the response, which is the newly added product.
        return response.getBody();
    }

}
