package com.barbershop.manager.models.DTOs;



import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public class PaymentMethodUpdateDTO {
    @NotNull
    private String name;
    private BigDecimal feePercentage;
    private Integer daysToReceive;
    @NotNull
    private Long defaultBankAccountId;

    public PaymentMethodUpdateDTO() {}

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
