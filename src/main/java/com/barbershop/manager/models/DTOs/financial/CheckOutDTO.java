package com.barbershop.manager.models.DTOs.financial;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public class CheckOutDTO {
    @NotNull
    private Long scheduleId;
    @NotNull
    private Long paymentMethodId;
    @Min(0)
    private BigDecimal value;

    public CheckOutDTO(Long scheduleId, Long PaymentMethodId) {
        this.scheduleId = scheduleId;
        this.paymentMethodId = PaymentMethodId;

    }

    public Long getScheduleId() {
        return scheduleId;
    }

    public Long getPaymentMethodId() {
        return paymentMethodId;
    }

    public BigDecimal getValue() {
        return value;
    }

    public void setScheduleId(Long scheduleId) {
        this.scheduleId = scheduleId;
    }

    public void setPaymentMethodId(Long paymentMethodId) {
        this.paymentMethodId = paymentMethodId;
    }

    public void setValue(BigDecimal netAmount) {
        this.value = netAmount;
    }
}
