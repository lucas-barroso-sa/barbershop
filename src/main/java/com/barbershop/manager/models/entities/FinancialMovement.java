package com.barbershop.manager.models.entities;

import com.barbershop.manager.models.enums.EventType;
import com.barbershop.manager.models.enums.MovementType;
import jakarta.persistence.*;

import java.math.BigDecimal;
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

    private BigDecimal grossAmount;
    private BigDecimal netAmount;
    private BigDecimal discountAmount;

    private LocalDateTime movementDate;

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


    public FinancialMovement() {}

    public FinancialMovement(EventType eventType, MovementType movementType, BigDecimal grossAmount, LocalDateTime movementDate, CostCenter costCenter, String description) {
        this.eventType = eventType;
        this.movementType = movementType;
        this.movementDate = movementDate;
        this.costCenter = costCenter;
        this.description = description;

        // 1. Proteção contra nulos
        this.grossAmount = grossAmount != null ? grossAmount : BigDecimal.ZERO;

        // Se não enviar o valor líquido, assumimos que pagou o valor cheio
        this.netAmount = netAmount != null ? netAmount : this.grossAmount;

        this.discountAmount = this.grossAmount.subtract(this.netAmount);
    }

    // --- GETTERS ---
    public Long getId() { return id; }
    public EventType getEventType() { return eventType; }
    public MovementType getMovementType() { return movementType; }
    public BigDecimal getGrossAmount() { return grossAmount; }
    public BigDecimal getNetAmount() { return netAmount; }
    public BigDecimal getDiscountAmount() { return discountAmount; }
    public LocalDateTime getMovementDate() { return movementDate; }
    public CostCenter getCostCenter() { return costCenter; }
    public String getDescription() { return description; }
    public Client getClient() { return client; }
    public Schedule getSchedule() { return schedule; }

    public void setId(Long id) { this.id = id; }
    public void setEventType(EventType eventType) { this.eventType = eventType; }
    public void setMovementType(MovementType movementType) { this.movementType = movementType; }
    public void setMovementDate(LocalDateTime movementDate) { this.movementDate = movementDate; }
    public void setCostCenter(CostCenter costCenter) { this.costCenter = costCenter; }
    public void setDescription(String description) { this.description = description; }
    public void setClient(Client client) { this.client = client; }
    public void setSchedule(Schedule schedule) { this.schedule = schedule; }

    // Ao mudar o valor bruto, o valor líquido se atualiza sozinho
    public void setGrossAmount(BigDecimal grossAmount) {
        this.grossAmount = grossAmount != null ? grossAmount : BigDecimal.ZERO;
        recalculateNetAmount();
    }

    // Ao mudar o desconto, o valor líquido se atualiza sozinho
    public void setDiscountAmount(BigDecimal discountAmount) {
        this.discountAmount = discountAmount != null ? discountAmount : BigDecimal.ZERO;
        recalculateNetAmount();
    }

    // Méthodo interno privado para garantir mudança no valor liquido ao mudar valor bruto
    private void recalculateNetAmount() {
        if (this.grossAmount != null && this.discountAmount != null) {
            this.netAmount = this.grossAmount.subtract(this.discountAmount);
        }
    }
}