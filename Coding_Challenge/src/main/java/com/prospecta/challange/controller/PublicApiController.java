package com.prospecta.challange.controller;

import com.prospecta.challange.model.Product;
import com.prospecta.challange.service.ApiService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;


/**
 * REST controller for handling product-related endpoints.
 */
@RestController
@RequestMapping("/api/products")
public class PublicApiController {

    @Autowired
    private ApiService apiService;

    // Fetch products list by category
    @GetMapping
    public ResponseEntity<List<Product>> getEntriesByCategory(@RequestParam(required = false) String category) {
        List<Product> entries = apiService.getEntriesByCategory(category);
        return ResponseEntity.ok(entries);
    }
    /**
     * Save a new product after validating the request body.
     * Returns validation errors if any are found.
     *
     * Example request body:
     * {
     *   "title": "Sample Product",
     *   "description": "This is a sample product.",
     *   "category": "Electronics",
     *   "price": 199.99,
     *   "image": "http://example.com/image.png",
     *   "rating": {
     *     "rate": 4.5,
     *     "count": 100
     *   }
     * }
     *
     * @param newProduct the product to save
     * @param bindingResult contains validation errors if any
     * @return the saved product or validation errors
     */
    @PostMapping
    public ResponseEntity<?> saveNewProduct(@Valid @RequestBody Product newProduct, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            // Collect all validation errors and return them as a list of error messages
            List<String> errors = bindingResult.getFieldErrors().stream()
                    .map(fieldError -> fieldError.getField() + ": " + fieldError.getDefaultMessage())
                    .collect(Collectors.toList());
            return ResponseEntity.badRequest().body(errors);
        }

        Product savedProduct = apiService.saveNewProduct(newProduct);
        return ResponseEntity.ok(savedProduct);
    }
}
