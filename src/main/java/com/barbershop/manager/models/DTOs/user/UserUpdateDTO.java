package com.barbershop.manager.models.DTOs.user;

import com.barbershop.manager.models.enums.UserRole;
import com.barbershop.manager.models.enums.UserStatus;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class UserUpdateDTO {
    @NotBlank(message = "O nome não pode estar em branco")
    private String name;

    @NotNull(message = "O tipo de perfil é obrigatório")
    private UserRole role;

    @NotNull(message = "O status do usuário é obrigatório")
    private UserStatus status;
    public UserUpdateDTO() {}

    public String getName() {
        return name;
    }

    public UserRole getRole() {
        return role;
    }

    public UserStatus getStatus() {
        return status;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setRole(UserRole role) {
        this.role = role;
    }

    public void setStatus(UserStatus status) {
        this.status = status;
    }
}
