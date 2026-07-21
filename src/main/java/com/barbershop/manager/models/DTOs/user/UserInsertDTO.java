package com.barbershop.manager.models.DTOs.user;

import com.barbershop.manager.models.entities.User;
import com.barbershop.manager.models.enums.UserRole;
import com.barbershop.manager.models.enums.UserStatus;

public class UserInsertDTO {
    private String name;
    private String email;
    private UserRole role;
    private String password;
    private UserStatus status;

    public UserInsertDTO() {

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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public UserStatus getStatus() {
        return status;
    }

    public void setStatus(UserStatus status) {
        this.status = status;
    }
}
