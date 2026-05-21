package com.barbershop.manager.models.DTOs;

import com.barbershop.manager.models.entities.Servicing;

import java.math.BigDecimal;

public class ServicingDTO {

    private Long id;
    private String name;
    private BigDecimal price;
    private int durationInMinutes;

    public ServicingDTO() {

    }
    public ServicingDTO(Servicing entity) {
        this.id = entity.getId();
        this.name = entity.getName();
        this.price = entity.getPrice();
        this.durationInMinutes = entity.getDurationInMinutes();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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
