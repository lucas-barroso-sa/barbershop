package com.barbershop.manager.models.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "ItemSale")
public class ItemSale {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private int quantity;
    private double unitPrice;

    public ItemSale() {

    }

    public ItemSale(long id, int quantity, double unitPrice) {
        this.id = id;
        this.quantity = quantity;
        this.unitPrice = unitPrice;
    }
}
