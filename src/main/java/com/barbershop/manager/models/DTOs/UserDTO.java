package com.barbershop.manager.models.DTOs;

import com.barbershop.manager.models.entities.User;
import com.barbershop.manager.models.enums.UserRole;

public class UserDTO {
    private Long id;
    private String name;
    private String email;
    ;
    private UserRole role;

    public UserDTO() {

    }

    public UserDTO(User entity) {
        this.id = entity.getId();
        this.name = entity.getName();
        this.email = entity.getEmail();
        this.role = entity.getRole();
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }



    public UserRole getRole() {
        return role;
    }

    public void setRole(UserRole role) {
        this.role = role;
    }
}
