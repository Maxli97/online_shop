package com.example.online_shop.controller;

import com.example.online_shop.model.*;
import org.apache.tomcat.util.json.JSONParser;
import org.apache.tomcat.util.json.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class CustomerController {
    @Autowired
    CustomersRepository customersRepository;
    @Autowired
    ProductRepository productsRepository;
    @Autowired
    OrdersRepository ordersRepository;
    @PostMapping("/viewCart/{id}")
    public ResponseEntity<String> viewCart(@PathVariable("id") int id) {
        List<Customers> customers=customersRepository.findById(id);
        String cart=customers.get(0).getCart();
        if(cart.isEmpty()){
            return new ResponseEntity<>("No items in the cart.",HttpStatus.OK);
        }else {
            return new ResponseEntity<>(cart,HttpStatus.OK);
        }

    }

    @PostMapping("/addToCart/{Cid}/{Pid}/{amount}")
    public ResponseEntity<Customers> addToCart(@PathVariable("Cid") int id,@PathVariable("Pid") long pid,@PathVariable("amount") int amount ) {
        List<Customers> customers=customersRepository.findById(id);
        String cart=customers.get(0).getCart();
        Optional<Product> products=productsRepository.findById(pid);
        if (cart.isEmpty()){
            cart="{id:'"+pid+"',amount:'"+amount+"'}";
        }else{
            cart=cart+",,{id:'"+pid+"',amount:'"+amount+"'}";
        }
        Customers customer=customers.get(0);
        customer.setCart(cart);
        customersRepository.save(customer);
        return new ResponseEntity<>(customer,HttpStatus.OK);
    }

    //get products in a customer cart and calaculate the total cost
    @PostMapping("/checkOut/{id}")
    public ResponseEntity<Double> chectOut(@PathVariable("id") int id) {
        List<Customers> customers=customersRepository.findById(id);
        String cart=customers.get(0).getCart();
        if(cart.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        String[] products=cart.split(",,");
        double totalCost=0;
        try {
            for (String p:products) {
                JSONParser jsonParser=new JSONParser(p);
                LinkedHashMap<String, Object> object= jsonParser.parseObject();
                Optional<Product> product=productsRepository.findById(Long.parseLong(object.get("id").toString()));
                totalCost=totalCost+Double.parseDouble(object.get("amount").toString())*product.get().getPrice();
            }
            SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
            ordersRepository.save(new Orders(cart, format.format(new Date()),totalCost,"Payment in progress"));
            Customers customer=customers.get(0);
            customer.setCart("");
            customersRepository.save(customer);
            return new ResponseEntity<>(totalCost,HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }


    }

    //test for json transfer
    public static void main(String[] args) throws ParseException {
        String a="{id:'aaa',amount:'123'},,{id:'bbb',amount:'222'},,{id:'ccc',amount:'321'}";
        String[] products=a.split(",,");
        for (String p:products) {
            JSONParser jsonParser=new JSONParser(p);
            LinkedHashMap<String, Object> object= jsonParser.parseObject();
//            System.out.println(jsonParser.parseObject().get("id"));
            System.out.println(object.get("id"));
            System.out.println(object.get("amount"));
        }
    }
}
