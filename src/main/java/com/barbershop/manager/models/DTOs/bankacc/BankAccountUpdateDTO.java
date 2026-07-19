package com.barbershop.manager.models.DTOs.bankacc;

import jakarta.validation.constraints.NotNull;

public class BankAccountUpdateDTO {

    @NotNull
    private String bankAccountName;

    public BankAccountUpdateDTO() {}

    public String getBankAccountName() {
        return bankAccountName;
    }

    public void setBankAccountName(String bankAccountName) {
        this.bankAccountName = bankAccountName;
    }
}
