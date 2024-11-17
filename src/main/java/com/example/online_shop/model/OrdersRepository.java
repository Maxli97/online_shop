package com.example.online_shop.model;


import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;


public interface OrdersRepository extends JpaRepository<Orders, Long> {

//    List<Orders> findbyProducts(String products);
//    List<Orders> findbyTime(String time);
//    List<Orders> findbyState(String state);
}
