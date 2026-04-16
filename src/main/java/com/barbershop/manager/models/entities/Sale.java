package com.barbershop.manager.models.entities;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table (name = "Sale")
public class Sale {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private double total;
    private double discount;
    private LocalDateTime saleData;

    public Sale() {

    }

    public Sale(long id, double total, LocalDateTime saleData) {
        this.id = id;
        this.total = total;
        this.saleData = saleData;
    }
}
