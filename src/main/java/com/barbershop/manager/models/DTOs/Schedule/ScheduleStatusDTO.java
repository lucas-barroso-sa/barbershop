package com.barbershop.manager.models.DTOs.Schedule;

import com.barbershop.manager.models.enums.ScheduleStatus;
import jakarta.validation.constraints.NotNull;

public class ScheduleStatusDTO {

    @NotNull(message = "O status não pode ser nulo")
    private ScheduleStatus newStatus;

    public ScheduleStatusDTO() {}

    public ScheduleStatus getStatus() { return newStatus; }
    public void setStatus(ScheduleStatus status) { this.newStatus = status; }
}
