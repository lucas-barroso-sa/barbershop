package com.barbershop.manager.models.entities;

import com.barbershop.manager.models.entities.user.Barber;
import com.barbershop.manager.models.enums.ScheduleStatus;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "Schedule")
public class Schedule {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDateTime date;
    private ScheduleStatus scheduleStatus;

    private Barber barber;

    private Servicing servicing;

    private Client client;


    public Schedule() {
    }

    public Schedule(Long id, LocalDateTime date, ScheduleStatus scheduleStatus) {
        this.id = id;
        this.date = date;
        this.scheduleStatus = scheduleStatus;
    }


}
