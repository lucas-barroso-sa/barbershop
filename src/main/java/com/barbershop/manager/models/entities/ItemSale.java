package com.barbershop.manager.models.entities;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "ItemSale")
public class ItemSale {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int quantity;
    private double unitPrice;

    @ManyToOne(fetch = FetchType.LAZY)
    private Sale sales;

    @OneToOne(fetch = FetchType.LAZY)
    private Product product;

    public ItemSale(Long id, int quantity, double unitPrice, Sale sales, Product product) {
        this.id = id;
        this.quantity = quantity;
        this.unitPrice = unitPrice;
        this.sales = sales;
        this.product = product;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
    }

    public Sale getSales() {
        return sales;
    }

    public void setSales(Sale sales) {
        this.sales = sales;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
}
