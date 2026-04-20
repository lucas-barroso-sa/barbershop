package com.barbershop.manager.models.DTOs;

import com.barbershop.manager.models.DTOs.user.UserDTO;
import com.barbershop.manager.models.entities.Schedule;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;
import java.util.List;

public class ScheduleDTO {
    private Long id;
    @NotNull(message = "O horário do agendamento é obrigatório")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime appointmentTime;
    private ClientMinDTO client;
    private List<ServicingDTO> servicings;
    private UserDTO user;


    public ScheduleDTO() {

    }
    public ScheduleDTO(Schedule entity) {
        this.id = entity.getId();
        this.appointmentTime = entity.getAppointmentTime();
        this.client = new ClientMinDTO(entity.getClient());
        this.servicings = entity.getServicings()
                .stream()
                .map(obj -> new ServicingDTO(obj)).toList();
        this.user = new UserDTO(entity.getUser());
    }

    public UserDTO getUser() {
        return user;
    }

    public void setUser(UserDTO user) {
        this.user = user;
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

    public ClientMinDTO getClient() {
        return client;
    }

    public void setClient(ClientMinDTO client) {
        this.client = client;
    }

    public List<ServicingDTO> getServicings() {
        return servicings;
    }

    public void setServicings(List<ServicingDTO> servicings) {
        this.servicings = servicings;
    }
}
