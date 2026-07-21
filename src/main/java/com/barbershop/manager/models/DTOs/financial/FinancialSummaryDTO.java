package com.barbershop.manager.models.DTOs.financial;

import java.math.BigDecimal;
import java.util.List;

public record FinancialSummaryDTO(
        String currentMonthYear,
        MovementSummaryDTO payables,
        MovementSummaryDTO receivables,
        AvailabilitySummaryDTO availabilities,
        List<ChartDataDTO> chartData
) {}