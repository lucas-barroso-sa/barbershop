package com.barbershop.manager.models.DTOs;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public class BankAccountInsertDTO {
    @NotNull
    private String name;
    @Min(0)
    private BigDecimal balance;

    public BankAccountInsertDTO() {}

    public BankAccountInsertDTO(String name, BigDecimal balance) {
        this.name = name;
        this.balance = balance;
    }

    public String getName() {
        return name;
    }

    public BigDecimal getBalance() {
        return balance;
    }


    public void setName(String name) {
        this.name = name;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }
}
