package com.barbershop.manager.models.DTOs.financial;

import java.math.BigDecimal;

public record ChartDataDTO(
        String name,
        BigDecimal recebimentos,
        BigDecimal pagamentos
) {}