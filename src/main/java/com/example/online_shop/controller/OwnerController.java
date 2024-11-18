package com.example.online_shop.controller;

import com.example.online_shop.model.Orders;
import com.example.online_shop.model.OrdersRepository;
import com.example.online_shop.model.Product;
import com.example.online_shop.model.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("/api")

public class OwnerController {
    @Autowired
    OrdersRepository orderRepo;
    //------------------------allow the owner company check customers order -------------------------
    @GetMapping("/orders")
    public ResponseEntity<List<Orders>>findAllOrders() {
        try{
            List<Orders> orders = new ArrayList<Orders>();
                orderRepo.findAll().forEach(orders::add);

            if (orders.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(orders, HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //------------------------if the company need to update a missing order-------------------------
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
    //------------------------if an order is conflict by multiple user-------------------------
//    @DeleteMapping("/orders/{id}")
//    public ResponseEntity<Orders> deleteOrder(@PathVariable long id) {
//        try{
//            orderRepo.deleteById(id);
//            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//        }catch (Exception e){
//            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//    }

    //------------------------allow the owner company check their product inventory -------------------------
    @Autowired
    ProductRepository productRepo;
    @GetMapping("/products")
    public ResponseEntity<List<Product>>findAllProducts() {
        try{
            List<Product> products = new ArrayList<Product>();
            productRepo.findAll().forEach(products::add);

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
