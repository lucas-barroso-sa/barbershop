package com.barbershop.manager.models.DTOs.Schedule;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public class ScheduleInsertDTO {
    @NotNull(message = "The Schedule time is mandatory")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime appointmentTime;

    @NotNull(message = "Client Id is mandatory")
    private Long clientId;

    @NotNull(message = "User Id is mandatory")
    private Long userId;

    @NotEmpty(message = "must have at least one servicing")
    private List<Long> servicingIds;
    @Min(value = 0)
    private BigDecimal scheduleValue;

    public ScheduleInsertDTO() {
    }

    public LocalDateTime getAppointmentTime() { return appointmentTime; }
    public void setAppointmentTime(LocalDateTime appointmentTime) { this.appointmentTime = appointmentTime; }

    public Long getClientId() { return clientId; }
    public void setClientId(Long clientId) { this.clientId = clientId; }

    public Long getUserId() { return userId; }
    public void setUserId(Long userId) { this.userId = userId; }

    public List<Long> getServicingIds() { return servicingIds; }
    public void setServicingIds(List<Long> servicingIds) { this.servicingIds = servicingIds; }

    public BigDecimal getScheduleValue() { return scheduleValue; }
    public void setScheduleValue(BigDecimal scheduleValue) { this.scheduleValue = scheduleValue; }

}
