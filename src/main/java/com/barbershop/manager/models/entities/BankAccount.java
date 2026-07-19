    package com.barbershop.manager.models.entities;

    import jakarta.persistence.*;
    import org.hibernate.annotations.CreationTimestamp;


    import java.math.BigDecimal;
    import java.time.LocalDateTime;

    @Entity
    @Table(name = "tb_bank_account")
    public class BankAccount {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        private String name;

        private BigDecimal balance;

        @CreationTimestamp
        @Column(updatable = false)
        private LocalDateTime createdAt;

        public BankAccount() {}
        public BankAccount(String name, BigDecimal balance) {
            this.name = name;
            this.balance = balance;
        }

        public void receive(BigDecimal amount) {
            // Blindagem contra valores nulos ou negativos
            if (amount == null || amount.compareTo(BigDecimal.ZERO) <= 0) {
                throw new IllegalArgumentException("O valor a ser recebido deve ser maior que zero.");
            }

            //  Prevenção de NullPointerException caso a conta tenha sido criada sem saldo inicial
            if (this.balance == null) {
                this.balance = BigDecimal.ZERO;
            }


            this.balance = this.balance.add(amount);
        }


        public void pay(BigDecimal amount) {
            //Blindagem contra valores nulos ou negativos
            if (amount == null || amount.compareTo(BigDecimal.ZERO) <= 0) {
                throw new IllegalArgumentException("O valor a ser pago deve ser maior que zero.");
            }

            if (this.balance == null) {
                this.balance = BigDecimal.ZERO;
            }


            this.balance = this.balance.subtract(amount);
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

        public LocalDateTime getCreatedAt() {
            return createdAt;
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
