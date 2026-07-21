package com.barbershop.manager.models.DTOs.financial;

import java.math.BigDecimal;

public record AccountBalanceDTO(
        String name,
        BigDecimal balance
) {}