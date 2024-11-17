package com.example.model;


import jakarta.persistence.*;

@Entity
@Table(name = "customers")
public class Customers {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @Column(name = "name")
    private String name;

    //cart format:{id:'productId1',amount:'amount1'},,{id:'productId2',amount:'amount2'},,{id:'productId3',amount:'amount3'}
    @Column(name = "cart")
    private String cart;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCart() {
        return cart;
    }

    public void setCart(String cart) {
        this.cart = cart;
    }

    public Customers() {
    }

    public Customers(int id, String name, String cart) {
        this.id = id;
        this.name = name;
        this.cart = cart;
    }
}
