package com.barbershop.manager.models.entities;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Client")
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String cpf;
    private String phone;
    private String email;

    @OneToMany(fetch = FetchType.LAZY)
    private List<Sale> sales = new ArrayList<>();

    @OneToMany
    private List<Schedule> schedules = new ArrayList<>();


    public Client() {

    }

    public Client(Long id, String name, String cpf, String telephone, String email, List<Sale> sales, List<Schedule> schedules) {
        this.id = id;
        this.name = name;
        this.cpf = cpf;
        this.phone = telephone;
        this.email = email;
        this.sales = sales;
        this.schedules = schedules;
    }

    public List<Schedule> getSchedules() {
        return schedules;
    }

    public void setSchedules(List<Schedule> schedules) {
        this.schedules = schedules;
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

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getphone() {
        return phone;
    }

    public void setphone(String telephone) {
        this.phone = telephone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<Sale> getSales() {
        return sales;
    }

    public void setSales(List<Sale> sales) {
        this.sales = sales;
    }
}
