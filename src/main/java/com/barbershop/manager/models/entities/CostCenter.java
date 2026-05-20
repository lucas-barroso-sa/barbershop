package com.barbershop.manager.models.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.util.List;

@Entity
@Table(name = "tb_cost_center")
public class CostCenter {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    private String name;

    private String description;

    @OneToMany
    private List<FinancialMovement> financialMovements;

    public CostCenter() {}

    public CostCenter(String name, String description, List<FinancialMovement> financialMovements) {
        this.name = name;
        this.description = description;
        this.financialMovements = financialMovements;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<FinancialMovement> getFinancialMovements() {
        return financialMovements;
    }

    public void setFinancialMovements(List<FinancialMovement> financialMovements) {
        this.financialMovements = financialMovements;
    }
}
