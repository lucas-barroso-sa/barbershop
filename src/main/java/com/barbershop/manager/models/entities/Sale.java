package com.barbershop.manager.models.entities;

import jakarta.persistence.*;
import org.hibernate.cache.spi.support.AbstractReadWriteAccess;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table (name = "Sale")
public class Sale {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private double total;
    private double discount;
    private LocalDateTime saleData;

    @OneToMany(fetch = FetchType.LAZY)
    private List<ItemSale> itens = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "client_id")
    private Client client;

    public Sale() {

    }

    public Sale(long id, double total, LocalDateTime saleData) {
        this.id = id;
        this.total = total;
        this.saleData = saleData;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }

    public LocalDateTime getSaleData() {
        return saleData;
    }

    public void setSaleData(LocalDateTime saleData) {
        this.saleData = saleData;
    }

    public List<ItemSale> getItens() {
        return itens;
    }

    public void setItens(List<ItemSale> itens) {
        this.itens = itens;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }
}
