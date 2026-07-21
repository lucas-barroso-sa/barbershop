package com.barbershop.manager.models.DTOs.financial;

import java.math.BigDecimal;
import java.util.List;

public record AvailabilitySummaryDTO(
        BigDecimal totalBalance,
        List<AccountBalanceDTO> accounts
) {}