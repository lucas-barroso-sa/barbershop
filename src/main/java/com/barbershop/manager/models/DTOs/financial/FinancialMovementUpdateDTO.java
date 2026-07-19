package com.barbershop.manager.models.DTOs.financial;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;

import java.math.BigDecimal;
import java.time.LocalDate;

public class FinancialMovementUpdateDTO {
    @NotBlank(message = "A descrição não pode ser vazia")
    private String description;

    @NotNull(message = "O valor é obrigatório")
    @PositiveOrZero(message = "O valor não pode ser negativo")
    private BigDecimal amount;

    @NotNull(message = "A data de vencimento é obrigatória")
    private LocalDate dueDate;

    // Esses dois podem ser nulos (caso a conta não esteja paga ou não tenha conta bancária atrelada)
    private LocalDate paymentDate;
    private Long bankAccountId;

    public FinancialMovementUpdateDTO() {}

    public String getDescription() {
        return description;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public LocalDate getPaymentDate() {
        return paymentDate;
    }

    public Long getBankAccountId() {
        return bankAccountId;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setBankAccountId(Long bankAccountId) {
        this.bankAccountId = bankAccountId;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }

    public void setPaymentDate(LocalDate paymentDate) {this.paymentDate = paymentDate;}
}