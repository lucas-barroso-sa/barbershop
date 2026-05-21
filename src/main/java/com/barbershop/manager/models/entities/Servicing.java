package com.barbershop.manager.models.entities;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "tb_servicing")
public class Servicing {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(nullable = false)
    private String name;
    private BigDecimal price;
    @Column(nullable = false)
    private int durationInMinutes;

    public Servicing() {

    }

    public Servicing(Long id, String name, BigDecimal price , int durationInMinutes) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.durationInMinutes =  durationInMinutes;
    }


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public int getDurationInMinutes() {
        return durationInMinutes;
    }

    public void setDurationInMinutes(int duration) {
        this.durationInMinutes = duration;
    }
}
