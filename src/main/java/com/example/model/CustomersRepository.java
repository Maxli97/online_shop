package com.example.model;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CustomersRepository extends JpaRepository<Customers, Long> {
    List<Customers> findById(int id);
    @Modifying
    @Query("update Customers u set u.cart = ?1 where u.id = ?2")
    void setCartById(String cart, Integer userId);

}
