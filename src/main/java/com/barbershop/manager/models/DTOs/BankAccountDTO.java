package com.barbershop.manager.models.DTOs;

import com.barbershop.manager.models.entities.BankAccount;

import java.math.BigDecimal;

public class BankAccountDTO {
    private Long id;
    private String name;
    private BigDecimal balance;

    public BankAccountDTO() {}
    public BankAccountDTO(BankAccount entity) {
        this.id = entity.getId();
        this.name = entity.getName();
        this.balance = entity.getBalance();
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }
}
