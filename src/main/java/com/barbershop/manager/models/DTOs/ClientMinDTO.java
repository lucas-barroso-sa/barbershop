package com.barbershop.manager.models.DTOs;

import com.barbershop.manager.models.entities.Client;
import jakarta.validation.constraints.Pattern;
public class ClientMinDTO {

    private Long  id;
    private String name;

    private String phone;
    private String email;


    public ClientMinDTO() {

    }
    public ClientMinDTO(Client entity) {
        this.id = entity.getId();
        this.name = entity.getName();
        this.phone = entity.getphone();
        this.email = entity.getEmail();

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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }



}
