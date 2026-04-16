package com.barbershop.manager.models.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "Servicing")
public class Servicing {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    private double price;
    private int duration;

    public Servicing() {

    }

    public Servicing(Long id, String name, double price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }


}
