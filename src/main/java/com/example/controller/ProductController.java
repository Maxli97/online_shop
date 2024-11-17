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

    @GetMapping("/product/{id}")
    public ResponseEntity<Product> findById(@PathVariable ("id") long id) {
        Optional<Product> productData = productRepository.findById(id);
        if (productData.isPresent()) {
            return new ResponseEntity<>(productData.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/product/{category}")
    public ResponseEntity<List<Product>> findByCategory(@RequestParam(required = false) String category) {
        try {
            List<Product> productList = new ArrayList<Product>();
            if (category == null) {
                productRepository.findAll().forEach(productList::add);
            } else if (category != null && !category.equals("")) {
                productRepository.findByCategory(category).forEach(productList::add);
            }
            return new ResponseEntity<>(productList, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/product/{name}")
    public ResponseEntity<Product> findByName(@PathVariable ("name") String name) {
        Optional<Product> productData = productRepository.findByName(name);
        if (productData.isPresent()) {
            return new ResponseEntity<>(productData.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
