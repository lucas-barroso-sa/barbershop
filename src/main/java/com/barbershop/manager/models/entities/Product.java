package com.barbershop.manager.models.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "product")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private int stock;
    private double defaultPrice;

    public Product(Long id, String name, int stock, double defaultPrice) {
        this.id = id;
        this.name = name;
        this.stock = stock;
        this.defaultPrice = defaultPrice;
    }
}
