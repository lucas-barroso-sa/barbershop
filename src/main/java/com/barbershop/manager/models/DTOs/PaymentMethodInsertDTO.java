package com.barbershop.manager.models.DTOs;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public class PaymentMethodInsertDTO {
    @NotNull
    private String name;
    @Min(0)
    private BigDecimal feePercentage;
    @NotNull
    @Min(0)
    private Integer daysToReceive;
    @NotNull
    private Long defaultBankAccountId;

    public PaymentMethodInsertDTO() {}

    public PaymentMethodInsertDTO(String name, BigDecimal feePercentage, Integer daysToRecieve, Long defaultBankAccountId) {
        this.name = name;
        this.feePercentage = feePercentage;
        this.daysToReceive = daysToRecieve;
        this.defaultBankAccountId = defaultBankAccountId;
    }

    public String getName() {
        return name;
    }

    public BigDecimal getFeePercentage() {
        return feePercentage;
    }

    public Integer getDaysToReceive() {
        return daysToReceive;
    }

    public Long getDefaultBankAccountId() {
        return defaultBankAccountId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setFeePercentage(BigDecimal feePercentage) {
        this.feePercentage = feePercentage;
    }

    public void setDaysToReceive(Integer daysToReceive) {
        this.daysToReceive = daysToReceive;
    }

    public void setDefaultBankAccountId(Long defaultBankAccountId) {
        this.defaultBankAccountId = defaultBankAccountId;
    }
}
