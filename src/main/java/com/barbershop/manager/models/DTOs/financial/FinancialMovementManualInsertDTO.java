package com.barbershop.manager.models.DTOs.financial;

import com.barbershop.manager.models.enums.MovementType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;

import java.math.BigDecimal;
import java.time.LocalDate;

public class FinancialMovementManualInsertDTO {
    @NotNull
    private MovementType movementType;
    @NotNull
    @PositiveOrZero
    private BigDecimal grossAmount;
    @NotNull
    private LocalDate dueDate;
    @NotBlank
    private String description;
    @NotNull
    private Long paymentMethodId;

    public FinancialMovementManualInsertDTO() {}

    public MovementType getMovementType() {
        return movementType;
    }

    public BigDecimal getGrossAmount() {
        return grossAmount;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public String getDescription() {
        return description;
    }

    public Long getPaymentMethodId() {
        return paymentMethodId;
    }


    public void setMovementType(MovementType movementType) {
        this.movementType = movementType;
    }

    public void setGrossAmount(BigDecimal grossAmount) {
        this.grossAmount = grossAmount;
    }

    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setPaymentMethodId(Long paymentMethodId) {
        this.paymentMethodId = paymentMethodId;
    }
}
