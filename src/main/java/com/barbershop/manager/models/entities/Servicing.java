package com.barbershop.manager.models.entities;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "tb_servicing")
public class Servicing {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    private double price;
    private int duration;

    @OneToMany
    private List<Schedule> schedules = new ArrayList<>();

    public Servicing() {

    }

    public Servicing(Long id, String name, double price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }
}
