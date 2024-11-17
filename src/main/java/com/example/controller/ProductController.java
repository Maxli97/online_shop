package com.example.controller;

import jakarta.servlet.Registration;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

public class ProductController {
    @PostMapping("/registrations")
    public ResponseEntity<Registration> FindByCategory(@RequestBody Registration reg ) {
        return null;
    }

    @PostMapping("/registrations")
    public ResponseEntity<Registration> findById(@RequestBody Registration reg ) {
        return null;
    }
    @PostMapping("/registrations")
    public ResponseEntity<Registration> findByName(@RequestBody Registration reg ) {
        return null;
    }
}
