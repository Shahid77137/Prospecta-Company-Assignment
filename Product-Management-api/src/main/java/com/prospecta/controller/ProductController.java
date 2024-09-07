package com.prospecta.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;

import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import lombok.extern.slf4j.Slf4j;

//Declared ProductController class as a @RestController to handle incoming HTTP requests.
@Slf4j
@RestController

// Mapped controller's base URL to '/products'.
@RequestMapping("/products")

public class ProductController {

    //  Injected FakeStore API URL from application.properties using @Value.
    @Value("${fakestore.api.url}")
    private String apiURL;

    //  Declared RestTemplate for making HTTP requests.
    private final RestTemplate restTemplate;

    //  Constructor to inject RestTemplate instance.
    public ProductController(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    //  Created a GET mapping for fetching products by category.
    @GetMapping("/category/{category}")
    public ResponseEntity<List<Map<String, Object>>> getProductsByCategory(@PathVariable String category) {
      
        // Appended category to base URL to form a full request URL.
        String url = apiURL + "/category/" + category;
       
        // Made GET request to FakeStore API using RestTemplate and returned the response.
        ResponseEntity<List> response = restTemplate.getForEntity(url, List.class);
      
        //  Returned the response body wrapped in a ResponseEntity.
        return ResponseEntity.ok(response.getBody());
    }

    //  Created a POST mapping for adding new products.
    @PostMapping("/add")
    public ResponseEntity<Map<String, Object>> addProduct(@RequestBody Map<String, Object> product) {
     
        //  Logged the product data that is being added.
        log.info("Attempting to add a product: {}", product);

        //  Set the URL for the POST request to the base API URL.
        String url = apiURL;
        
        //  Logged the API URL to which the product data will be sent.
        log.info("Sending product data to FakeStore API: {}", url);

        //  Made POST request to FakeStore API to add the product.
        ResponseEntity<Map> response = restTemplate.postForEntity(url, product, Map.class);

        // Logged the response received from FakeStore API after adding the product.
        log.info("Product added successfully, response: {}", response.getBody());
        
        // Returned the response body wrapped in a ResponseEntity.
        return ResponseEntity.ok(response.getBody());
    }
}
