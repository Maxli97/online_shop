package com.example.controller;

import com.example.model.Orders;
import com.example.model.OrdersRepository;
import com.example.model.Products;
import jakarta.servlet.Registration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("/api")

public class OwnerController {
    @Autowired
    OrdersRepository orderRepo;
    //------------------------findAllOrders-------------------------
    @GetMapping("/orders")
    public ResponseEntity<List<Orders>>findAllOrders(@RequestParam(required = false) String productIds) {
        try{
            List<Orders> orders = new ArrayList<Orders>();
            if (productIds != null) {
                orderRepo.findAll().forEach(orders::add);
            }else {
                orderRepo.findbyProductIds(productIds).forEach(orders::add);
            }

            if (orders.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(orders, HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

//    @PostMapping("/orders")
//    public ResponseEntity<Orders> addOrder(@RequestBody Orders order) {
//        try{
//            Orders newOrder = new Orders(order.getProductIds(), order.getTime(), order.getState());
//            orderRepo.save(newOrder);
//            return new ResponseEntity<>(newOrder, HttpStatus.CREATED);
//        }catch (Exception e){
//            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//    }
//
//    @DeleteMapping("/orders/{id}")
//    public ResponseEntity<Orders> deleteOrder(@PathVariable long id) {
//        try{
//            orderRepo.deleteById(id);
//            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//        }catch (Exception e){
//            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//    }

    //------------------------findAllProduct-------------------------
    @GetMapping("/products")
    public ResponseEntity<List<Products>>findAllProducts(@RequestParam(required = false) String productIds) {
        try{
            List<Products> products = new ArrayList<Products>();
            if (productIds != null) {
                orderRepo.findAll().forEach(products::add);
            }else {
                orderRepo.findbyProductIds(productIds).forEach(products::add);
            }

            if (products.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(products, HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }



//    @PostMapping("/registrations")
//    public ResponseEntity<Registration> findAllOrders(@RequestBody Registration reg ) {
//        return null;
//    }
//
//    @PostMapping("/registrations")
//    public ResponseEntity<Registration> findAmount(@RequestBody Registration reg ) {
//        return null;
//    }
}
