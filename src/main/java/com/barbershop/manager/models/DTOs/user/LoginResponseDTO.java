package com.barbershop.manager.models.DTOs.user;

import com.barbershop.manager.models.enums.UserRole;

public record LoginResponseDTO(String token, UserRole role, String name) {
}