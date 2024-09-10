// Declared the ProductController class to handle product-related HTTP requests.
package com.prospecta.controller;

// Imported required classes and packages.
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.prospecta.entity.Product;
import com.prospecta.exception.CategoryNotFoundException;
import com.prospecta.service.ProductService;

import lombok.extern.slf4j.Slf4j;

// Declared the ProductController class and added the @RestController annotation to handle HTTP requests.
@RestController
@Slf4j
// Mapped the base URL of the ProductController to "/api/products".
@RequestMapping("/api/products")

public class ProductController {

    // Declared the ProductService for managing products.
    private ProductService productService;

    // Used @Autowired to inject ProductService via constructor.
    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }
    
    // Created a GET mapping to fetch products by category.
    @GetMapping("/category/{category}")
    public ResponseEntity<List<Product>> getProductsByCategory(@PathVariable String category) {
        // Logged an informational message when fetching products by category.
        log.info("Inside the getProductsByCategory method of ProductController. Fetching products for category: {}", category);

        // Retrieved the list of products by category using ProductService.
        List<Product> products = productService.getProductsByCategory(category);

        // Threw a custom exception if no products were found for the given category.
        if (products.isEmpty()) {
            throw new CategoryNotFoundException("No products found for category: " + category);
        }

        // Returned the list of products with an HTTP status of 200 (OK).
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    // Created a POST mapping to add a new product.
    @PostMapping("/add")
    public ResponseEntity<Product> addProduct(@RequestBody Product product) {
        // Logged an informational message when adding a product.
        log.info("Inside the addProduct method of ProductController");

        // Added the new product using ProductService and returned it with a status of 201 (Created).
        Product addedProduct = productService.addProduct(product);
        return new ResponseEntity<>(addedProduct, HttpStatus.CREATED);
    }

}
