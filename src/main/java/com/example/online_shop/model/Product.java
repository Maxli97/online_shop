package com.example.online_shop.model;

import jakarta.persistence.*;

@Entity
@Table (name = "products")
public class Product {

    @Id
    @GeneratedValue (strategy = GenerationType.AUTO)
    private Long id;

    @Column (name = "name")
    private String name;

    @Column (name = "category")
    private String category;

    @Column (name = "description")
    private String description;

    @Column (name = "price")
    private double price;

    @Column (name = "amount")
    private int amount;

    public Product(){

    }

    public Product(String name, String category, String description, double price, int amount) {
        this.name = name;
        this.category = category;
        this.description = description;
        this.price = price;
        this.amount = amount;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String type) {
        this.category = category;
    }

    public String getDescription() {
        return description;
    }

//    public void setDescription(String description) {
//        this.description = description;
//    }

    public double getPrice() {
        return price;
    }

//    public void setPrice(double price) {
//        this.price = price;
//    }

    public int getAmount() {
        return amount;
    }

//    public void setAmount(int amount) {
//        this.amount = amount;
//    }

    public String getName() {
        return name;
    }

//    public void setName(String name) {
//        this.name = name;
//    }

//    public void setId(Long id) {
//        this.id = id;
//    }

    public Long getId() {
        return id;
    }

    @Override
    public String toString() {
        return id + ", " + name + ", " + category + ", " + description + ", " + price + ", " + amount;
    }

}
