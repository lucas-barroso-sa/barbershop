package com.barbershop.manager.models.entities;

import com.barbershop.manager.models.enums.PaymentCategory;
import jakarta.persistence.*;


import java.math.BigDecimal;


@Entity
@Table(name = "tb_payment_method")
public class PaymentMethod {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private BigDecimal feePercentage;
    private Integer daysToReceive;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "bank_account_id")
    private BankAccount defaultBankAccount;


    public PaymentMethod() {
    }
    public PaymentMethod(String name, BigDecimal feePercentage, Integer daysToReceive, BankAccount defaultBankAccount) {

        this.name = name;
        this.feePercentage = feePercentage;
        this.daysToReceive = daysToReceive;
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

    public BankAccount getDefaultBankAccount() {
        return defaultBankAccount;
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

    public void setDefaultBankAccount(BankAccount defaultBankAccount) {
        this.defaultBankAccount = defaultBankAccount;
    }
}
