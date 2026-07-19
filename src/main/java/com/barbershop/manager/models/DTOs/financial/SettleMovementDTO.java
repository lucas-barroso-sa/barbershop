package com.barbershop.manager.models.DTOs.financial;

import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public class SettleMovementDTO {

    @NotNull(message = "A data de pagamento é obrigatória")
    private LocalDate paymentDate;

    @NotNull
    private Long bankAccountId;

    public SettleMovementDTO() {}

    public LocalDate getPaymentDate() {
        return paymentDate;
    }

    public Long getBankAccountId() {
        return bankAccountId;
    }

    public void setPaymentDate(LocalDate paymentDate) {
        this.paymentDate = paymentDate;
    }

    public void setBankAccountId(Long bankAccountId) {this.bankAccountId = bankAccountId;}

}