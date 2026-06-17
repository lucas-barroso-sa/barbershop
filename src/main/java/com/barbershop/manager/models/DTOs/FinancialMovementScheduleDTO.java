package com.barbershop.manager.models.DTOs;

import com.barbershop.manager.models.entities.FinancialMovement;
import com.barbershop.manager.models.enums.EventType;
import com.barbershop.manager.models.enums.MovementStatus;
import com.barbershop.manager.models.enums.MovementType;

import java.math.BigDecimal;
import java.time.LocalDate;

public class FinancialMovementScheduleDTO {
    private Long id;
    private EventType eventType;
    private MovementType movementType;
    private MovementStatus movementStatus;
    private BigDecimal grossAmount;
    private BigDecimal netAmount;
    private BigDecimal discountAmount;
    private LocalDate dueDate;
    private BigDecimal feeAmount;
    private Long scheduleId;
    private Long bankAccountId;
    private Long paymentMethodId;

    public FinancialMovementScheduleDTO() {}

    public FinancialMovementScheduleDTO(FinancialMovement entity) {
        this.id = entity.getId();
        this.eventType = entity.getEventType();
        this.movementType = entity.getMovementType();
        this.movementStatus = entity.getMovementStatus();
        this.grossAmount = entity.getGrossAmount();
        this.netAmount = entity.getNetAmount();
        this.discountAmount = entity.getDiscountAmount();
        this.dueDate = entity.getDueDate();
        this.scheduleId = entity.getSchedule().getId();
        this.bankAccountId = entity.getBankAccount().getId();
        this.paymentMethodId = entity.getPaymentMethod().getId();
        this.feeAmount = entity.getFeeAmount();
    }

    public Long getId() {
        return id;
    }

    public EventType getEventType() {
        return eventType;
    }

    public MovementType getMovementType() {
        return movementType;
    }

    public MovementStatus getMovementStatus() {
        return movementStatus;
    }

    public BigDecimal getGrossAmount() {
        return grossAmount;
    }

    public BigDecimal getNetAmount() {
        return netAmount;
    }

    public BigDecimal getDiscountAmount() {
        return discountAmount;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public Long getScheduleId() {
        return scheduleId;
    }

    public Long getBankAccountId() {
        return bankAccountId;
    }

    public Long getPaymentMethodId() {
        return paymentMethodId;
    }

    public BigDecimal getFeeAmount() {
        return feeAmount;
    }


    public void setId(Long id) {
        this.id = id;
    }

    public void setEventType(EventType eventType) {
        this.eventType = eventType;
    }

    public void setMovementType(MovementType movementType) {
        this.movementType = movementType;
    }

    public void setMovementStatus(MovementStatus movementStatus) {
        this.movementStatus = movementStatus;
    }

    public void setGrossAmount(BigDecimal grossAmount) {
        this.grossAmount = grossAmount;
    }

    public void setNetAmount(BigDecimal netAmount) {
        this.netAmount = netAmount;
    }

    public void setDiscountAmount(BigDecimal discountAmount) {
        this.discountAmount = discountAmount;
    }

    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }

    public void setScheduleId(Long scheduleId) {
        this.scheduleId = scheduleId;
    }

    public void setBankAccountId(Long bankAccountId) {
        this.bankAccountId = bankAccountId;
    }

    public void setPaymentMethodId(Long paymentMethodId) {
        this.paymentMethodId = paymentMethodId;
    }

    public void setFeeAmount(BigDecimal feeAmount) {}
}
