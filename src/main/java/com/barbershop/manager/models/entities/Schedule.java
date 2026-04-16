package com.barbershop.manager.models.entities;

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



    @OneToMany(fetch = FetchType.LAZY)
    private Servicing servicing;

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "client_id")
    private Client client;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;


    public Schedule() {
    }

    public Schedule(Long id, LocalDateTime date, ScheduleStatus scheduleStatus) {
        this.id = id;
        this.date = date;
        this.scheduleStatus = scheduleStatus;
    }


}
