package com.example.controller;

import com.example.model.ProductRepository;
import com.example.model.Product;
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

    //find product by id (long)
    @GetMapping("/product/id/{id}")
    public ResponseEntity<Product> findById(@PathVariable ("id") long id) {
        Optional<Product> productData = productRepository.findById(id);
        return productData.map(product -> new ResponseEntity<>(product, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    //find product by category (String)
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

    //find product by name (String)
    @GetMapping("/product/name/{name}")
    public ResponseEntity<Product> findByName(@PathVariable ("name") String name) {
        Optional<Product> productData = productRepository.findByName(name);
        return productData.map(product -> new ResponseEntity<>(product, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
}
