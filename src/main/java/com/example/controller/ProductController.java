package com.example.controller;

import com.example.model.Product;
import com.example.model.ProductRepository;
import jakarta.servlet.Registration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@CrossOrigin (origins = "http://localhost:8080")
@RestController
@RequestMapping("/api")
public class ProductController {

    @Autowired
    ProductRepository productRepository;

    @GetMapping("/product/id/{id}")
    public ResponseEntity<Product> findById(@PathVariable ("id") long id) {
        Optional<Product> productData = productRepository.findById(id);
        return productData.map(product -> new ResponseEntity<>(product, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/product/category/{category}")
    public ResponseEntity<List<Product>> findByCategory(@PathVariable ("category") String category) {
        try {
            List<Product> productList = new ArrayList<>();
            if (category == null) {
                productList.addAll(productRepository.findAll());
            } else if (!category.isEmpty()) {
                productList.addAll(productRepository.findByCategory(category));
            }
            return new ResponseEntity<>(productList, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/product/name/{name}")
    public ResponseEntity<Product> findByName(@PathVariable ("name") String name) {
        Optional<Product> productData = productRepository.findByName(name);
        return productData.map(product -> new ResponseEntity<>(product, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
}
