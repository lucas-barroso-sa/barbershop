package com.barbershop.manager.models.DTOs;

import com.barbershop.manager.models.entities.PaymentMethod;

import java.math.BigDecimal;

public class PaymentMethodDTO {
    private Long id;
    private String name;
    private BigDecimal feePercentage;
    private Integer daysToReceive;
    private String defaultBankAccountName;

    public PaymentMethodDTO() {}

    public PaymentMethodDTO(PaymentMethod entity) {
        this.id = entity.getId();
        this.name = entity.getName();
        this.feePercentage = entity.getFeePercentage();
        this.daysToReceive = entity.getDaysToReceive();
        this.defaultBankAccountName = entity
                .getDefaultBankAccount()
                .getName();
    }

    public Long getId() {
        return id;
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

    public String getDefaultBankAccountName() {
        return defaultBankAccountName;
    }

    public void setId(Long id) {
        this.id = id;
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

    public void setDefaultBankAccountName(String defaultBankAccountName) {
        this.defaultBankAccountName = defaultBankAccountName;
    }
}
