package com.prospecta.challange.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.springframework.http.HttpStatus;
import org.springframework.web.client.RestClientException;
import org.springframework.web.server.ResponseStatusException;
import com.prospecta.challange.model.Product;

@Service
public class ApiService {

    @Autowired
    private RestTemplate restTemplate;

    @Value("${publicapi.url}")
    private String publicApiUrl;

    // Fetch products by category or all products if category is null
    public List<Product> getEntriesByCategory(String category) {
        try {
            String url;
            Product[] products;

            // Check if category is provided, if not, fetch all products
            if (category == null || category.isEmpty()) {
                url = publicApiUrl + "/products";
            } else {
                url = publicApiUrl + "/products/category/" + category;
            }

            products = restTemplate.getForObject(url, Product[].class);

            if (products == null || products.length == 0) {
                throw new ResponseStatusException(HttpStatus.NO_CONTENT, "No products found");
            }

            return List.of(products);

        } catch (RestClientException ex) {
            throw new ResponseStatusException(HttpStatus.SERVICE_UNAVAILABLE, "Error fetching data from external API", ex);
        }
    }

    // Save new product
    public Product saveNewProduct(Product newProduct) {
        try {
            String url = publicApiUrl + "/products";
            return restTemplate.postForObject(url, newProduct, Product.class);
        } catch (RestClientException ex) {
            throw new ResponseStatusException(HttpStatus.SERVICE_UNAVAILABLE, "Error saving product to external API", ex);
        }
    }
}
