package com.example.model;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrdersRepository extends JpaRepository<Orders, Long> {

    List<Orders> findbyProductIds(String productIds);
    List<Orders> findbyTime(String time);
    List<Orders> findbyState(String state);
}
