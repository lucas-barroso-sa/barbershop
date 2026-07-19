package com.barbershop.manager.models.DTOs.financial;

import com.barbershop.manager.models.entities.FinancialMovement;

import java.math.BigDecimal;
import java.time.LocalDate;

public class FinancialMovementDTO {

    private Long id;
    private BigDecimal grossAmount;
    private BigDecimal netAmount;
    private LocalDate dueDate;
    private LocalDate paymentDate;
    private String description;
    private String clientName;
    private String bankName;
    private String paymentMethodName;
    private Long bankAccountId;

    public FinancialMovementDTO() {}

    public FinancialMovementDTO(FinancialMovement entity) {
        this.id = entity.getId();
        this.grossAmount = entity.getGrossAmount();
        this.netAmount = entity.getNetAmount();
        this.dueDate = entity.getDueDate();
        this.paymentDate = LocalDate.now();
        this.description = entity.getDescription();
        this.clientName = entity.getClient() != null ? entity.getClient().getName() : null;
        this.bankName = entity.getBankAccount() != null ? entity.getBankAccount().getName() : null;
        this.paymentMethodName = entity.getPaymentMethod() != null ? entity.getPaymentMethod().getName() : null;
        this.bankAccountId = entity.getBankAccount() != null ? entity.getBankAccount().getId() : null;

    }


    public Long getId() {
        return id;
    }

    public BigDecimal getGrossAmount() {
        return grossAmount;
    }

    public BigDecimal getNetAmount() {
        return netAmount;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public LocalDate getPaymentDate() {
        return paymentDate;
    }

    public String getDescription() {
        return description;
    }

    public String getClientName() {
        return clientName;
    }

    public String getBankName() {
        return bankName;
    }

    public String getPaymentMethodName() {
        return paymentMethodName;
    }

    public Long getBankAccountId() {
        return bankAccountId;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setGrossAmount(BigDecimal grossAmount) {
        this.grossAmount = grossAmount;
    }

    public void setNetAmount(BigDecimal netAmount) {
        this.netAmount = netAmount;
    }

    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }

    public void setPaymentDate(LocalDate paymentDate) {
        this.paymentDate = paymentDate;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public void setPaymentMethodName(String paymentMethodName) {
        this.paymentMethodName = paymentMethodName;
    }

    public void setBankAccountId(Long bankAccountId) {
        this.bankAccountId = bankAccountId;
    }
}
