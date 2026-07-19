package com.barbershop.manager.models.entities;

import com.barbershop.manager.models.enums.EventType;
import com.barbershop.manager.models.enums.MovementStatus;
import com.barbershop.manager.models.enums.MovementType;
import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "tb_financial_movement")
public class FinancialMovement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private EventType eventType;
    @Enumerated(EnumType.STRING)
    private MovementType movementType;
    @Enumerated(EnumType.STRING)
    private MovementStatus movementStatus;

    private BigDecimal grossAmount;
    private BigDecimal netAmount;
    private BigDecimal discountAmount;
    private BigDecimal feeAmount;
    private LocalDate dueDate;
    private LocalDate paymentDate;
    @CreationTimestamp
    @Column(updatable = false)
    private LocalDateTime creationDate;


    private String description;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cost_center_id")
    private CostCenter costCenter;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "client_id")
    private Client client; // Pode ser nulo

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "schedule_id")
    private Schedule schedule;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "bank_account_id")
    private BankAccount bankAccount;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "payment_method_id")
    private PaymentMethod paymentMethod;

    public FinancialMovement() {}

    public FinancialMovement(Schedule schedule, PaymentMethod paymentMethod, BigDecimal grossAmount, BigDecimal amountPaid, LocalDate referenceDate) {
        this.eventType = EventType.SERVICE_PAYMENT;
        this.movementType = MovementType.INCOME;
        this.schedule = schedule;
        this.paymentMethod = paymentMethod;
        this.bankAccount = paymentMethod.getDefaultBankAccount();
        this.description = "Pagamento - Agendamento " + schedule.getClient().getName();
        this.client = schedule.getClient();

        //  Cálculos Financeiros
        this.grossAmount = grossAmount != null ? grossAmount : BigDecimal.ZERO;
        BigDecimal safeAmountPaid = amountPaid != null ? amountPaid : this.grossAmount;
        this.discountAmount = this.grossAmount.subtract(safeAmountPaid);

        if (paymentMethod.getFeePercentage() != null && paymentMethod.getFeePercentage().compareTo(BigDecimal.ZERO) > 0) {
            this.feeAmount = safeAmountPaid
                    .multiply(paymentMethod.getFeePercentage())
                    .divide(new BigDecimal("100"), 2, RoundingMode.HALF_UP);
        } else {
            this.feeAmount = BigDecimal.ZERO;
        }

        this.netAmount = this.grossAmount.subtract(this.discountAmount).subtract(this.feeAmount);

        // Regras de Prazos e Status
        int daysToReceive = paymentMethod.getDaysToReceive() != null ? paymentMethod.getDaysToReceive() : 0;
        this.dueDate = referenceDate.plusDays(daysToReceive);
        this.movementStatus = MovementStatus.PENDING;
    }

    // --- GETTERS ---
    public Long getId() { return id; }
    public EventType getEventType() { return eventType; }
    public MovementType getMovementType() { return movementType; }
    public BigDecimal getGrossAmount() { return grossAmount; }
    public BigDecimal getNetAmount() { return netAmount; }
    public BigDecimal getDiscountAmount() { return discountAmount; }
    public CostCenter getCostCenter() { return costCenter; }
    public String getDescription() { return description; }
    public Client getClient() { return client; }
    public Schedule getSchedule() { return schedule; }
    public MovementStatus getMovementStatus() {
        return movementStatus;
    }
    public LocalDate getDueDate() {
        return dueDate;
    }
    public LocalDate getPaymentDate() {
        return paymentDate;
    }
    public LocalDateTime getCreationDate() {
        return creationDate;
    }
    public BankAccount getBankAccount() {
        return bankAccount;
    }
    public PaymentMethod getPaymentMethod() {
        return paymentMethod;
    }
    public BigDecimal getFeeAmount() {
        return feeAmount;
    }


    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }
    public void setPaymentDate(LocalDate paymentDate) {
        this.paymentDate = paymentDate;
    }
    public void setId(Long id) { this.id = id; }
    public void setEventType(EventType eventType) { this.eventType = eventType; }
    public void setMovementType(MovementType movementType) { this.movementType = movementType; }
    public void setCostCenter(CostCenter costCenter) { this.costCenter = costCenter; }
    public void setDescription(String description) { this.description = description; }
    public void setClient(Client client) { this.client = client; }
    public void setSchedule(Schedule schedule) { this.schedule = schedule; }
    public void setMovementStatus(MovementStatus movementStatus) { this.movementStatus = movementStatus; }
    public void setBankAccount(BankAccount bankAccount) {
        this.bankAccount = bankAccount;
    }
    public void setPaymentMethod(PaymentMethod paymentMethod) {
        this.paymentMethod = paymentMethod;
    }
    public void setFeeAmount(BigDecimal feeAmount) {
        this.feeAmount = feeAmount;
    }


}