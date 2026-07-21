package com.barbershop.manager.models.DTOs.financial;

import java.math.BigDecimal;

public record MovementSummaryDTO(
        BigDecimal total,
        BigDecimal overdue,
        BigDecimal today,
        BigDecimal thisWeek
) {}