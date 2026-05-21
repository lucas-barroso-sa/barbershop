package com.barbershop.manager.models.DTOs.Schedule;

import com.barbershop.manager.models.entities.Schedule;
import com.barbershop.manager.models.enums.ScheduleStatus;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public class ScheduleMinDTO {
    private Long id;
    private String clientName;
    private String barberName;
    private List<String> serviceNames;
    private LocalDateTime appointmentTime;
    private BigDecimal scheduleValue;
    private ScheduleStatus scheduleStatus;

    public ScheduleMinDTO() {}

    public ScheduleMinDTO(Schedule entity){
        this.id = entity.getId();
        this.clientName = entity.getClient().getName();
        this.barberName = entity.getUser().getName();
        this.serviceNames = entity.getServicings()
                .stream()
                .map(obj -> obj.getName())
                .toList();
        this.appointmentTime = entity.getAppointmentTime();
        this.scheduleValue = entity.getScheduleValue();
        this.scheduleStatus = entity.getScheduleStatus();
    }

    public ScheduleStatus getScheduleStatus() {
        return scheduleStatus;
    }

    public void setScheduleStatus(ScheduleStatus scheduleStatus) {
        this.scheduleStatus = scheduleStatus;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getAppointmentTime() {
        return appointmentTime;
    }

    public void setAppointmentTime(LocalDateTime appointmentTime) {
        this.appointmentTime = appointmentTime;
    }

    public List<String> getServiceNames() {
        return serviceNames;
    }

    public void setServiceNames(List<String> serviceNames) {
        this.serviceNames = serviceNames;
    }

    public BigDecimal getScheduleValue() {
        return scheduleValue;
    }

    public void setScheduleValue(BigDecimal price) {
        this.scheduleValue = price;
    }

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public String getBarberName() {
        return barberName;
    }

    public void setBarberName(String barberName) {
        this.barberName = barberName;
    }
}
