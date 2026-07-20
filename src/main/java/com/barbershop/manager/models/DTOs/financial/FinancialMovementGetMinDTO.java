package com.barbershop.manager.models.DTOs.financial;

import com.barbershop.manager.models.entities.FinancialMovement;
import com.barbershop.manager.models.enums.MovementType;
import com.barbershop.manager.models.enums.MovementStatus;
import com.barbershop.manager.models.enums.EventType;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class FinancialMovementGetMinDTO {
    private Long id;
    private MovementType movementType;
    private List<String> servicesNames;
    private String description;
    private LocalDate dueDate;
    private BigDecimal netAmount;

    private EventType eventType;
    private MovementStatus movementStatus;
    private LocalDate paymentDate;
    private String costCenterName;

    public FinancialMovementGetMinDTO() {}

    public FinancialMovementGetMinDTO(FinancialMovement entity) {
        this.id = entity.getId();
        this.movementType = entity.getMovementType();
        this.description = entity.getDescription();
        this.dueDate = entity.getDueDate();
        this.netAmount = entity.getNetAmount();

        // 🚨 1. BLINDAGEM CONTRA NULL POINTER EXCEPTION
        if (entity.getSchedule() != null && entity.getSchedule().getServicings() != null) {
            this.servicesNames = entity.getSchedule().getServicings().stream()
                    .map(p -> p.getName()).toList();
        } else {
            // Se for lançamento manual, devolve uma lista vazia em vez de quebrar a API
            this.servicesNames = new ArrayList<>();
        }

        // 🚨 2. MAPEAMENTO PARA O REACT (Ícones, status e categorias)
        this.eventType = entity.getEventType();
        this.movementStatus = entity.getMovementStatus();
        this.paymentDate = entity.getPaymentDate();
        this.costCenterName = entity.getCostCenter() != null ? entity.getCostCenter().getName() : null;
    }

    // --- GETTERS ---
    public Long getId() { return id; }
    public MovementType getMovementType() { return movementType; }
    public List<String> getServicesNames() { return servicesNames; }
    public String getDescription() { return description; }
    public LocalDate getDueDate() { return dueDate; }
    public BigDecimal getNetAmount() { return netAmount; }
    public EventType getEventType() { return eventType; }
    public MovementStatus getMovementStatus() { return movementStatus; }
    public LocalDate getPaymentDate() { return paymentDate; }
    public String getCostCenterName() { return costCenterName; }

    // --- SETTERS ---
    public void setId(Long id) { this.id = id; }
    public void setMovementType(MovementType movementType) { this.movementType = movementType; }
    public void setServicesNames(List<String> servicesNames) { this.servicesNames = servicesNames; }
    public void setDescription(String description) { this.description = description; }
    public void setDueDate(LocalDate dueDate) { this.dueDate = dueDate; }
    public void setNetAmount(BigDecimal netAmount) { this.netAmount = netAmount; }
    public void setEventType(EventType eventType) { this.eventType = eventType; }
    public void setMovementStatus(MovementStatus movementStatus) { this.movementStatus = movementStatus; }
    public void setPaymentDate(LocalDate paymentDate) { this.paymentDate = paymentDate; }
    public void setCostCenterName(String costCenterName) { this.costCenterName = costCenterName; }
}