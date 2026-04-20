package com.barbershop.manager.models.DTOs;

import com.barbershop.manager.models.entities.Servicing;

public class ServicingDTO {

    private Long id;
    private String name;
    private double price;
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

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getDurationInMinutes() {
        return durationInMinutes;
    }

    public void setDurationInMinutes(int duration) {
        this.durationInMinutes = duration;
    }
}
