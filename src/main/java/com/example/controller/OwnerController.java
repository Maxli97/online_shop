package com.example.controller;

import jakarta.servlet.Registration;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

public class OwnerController {
    @PostMapping("/registrations")
    public ResponseEntity<Registration> findAllOrders(@RequestBody Registration reg ) {
        return null;
    }

    @PostMapping("/registrations")
    public ResponseEntity<Registration> findAmount(@RequestBody Registration reg ) {
        return null;
    }
}
