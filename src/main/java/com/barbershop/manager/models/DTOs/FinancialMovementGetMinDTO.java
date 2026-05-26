package com.barbershop.manager.models.DTOs;

import com.barbershop.manager.models.entities.FinancialMovement;
import com.barbershop.manager.models.enums.MovementType;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public class FinancialMovementGetMinDTO {
    private Long id;
    private MovementType movementType;
    private List<String> servicesNames;
    private String description;
    private LocalDate dueDate;
    private BigDecimal netAmount;


    public FinancialMovementGetMinDTO() {}

    public FinancialMovementGetMinDTO(FinancialMovement entity) {
        this.id = entity.getId();
        this.movementType = entity.getMovementType();
        this.description = entity.getDescription();
        this.dueDate = entity.getDueDate();
        this.netAmount = entity.getNetAmount();
        this.servicesNames = entity
                .getSchedule()
                .getServicings().stream()
                .map(p -> p.getName()).toList();
    }

    public Long getId() {
        return id;
    }

    public MovementType getMovementType() {
        return movementType;
    }

    public List<String> getServicesNames() {
        return servicesNames;
    }

    public String getDescription() {
        return description;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public BigDecimal getNetAmount() {
        return netAmount;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setMovementType(MovementType movementType) {
        this.movementType = movementType;
    }

    public void setServicesNames(List<String> servicesNames) {
        this.servicesNames = servicesNames;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }

    public void setNetAmount(BigDecimal netAmount) {
        this.netAmount = netAmount;
    }
}
