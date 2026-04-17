package com.barbershop.manager.models.entities;

import com.barbershop.manager.models.enums.ScheduleStatus;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "Schedule")
public class Schedule {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDateTime date;
    private ScheduleStatus scheduleStatus;



    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "servicings_id")
    private List<Servicing> servicings;

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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public ScheduleStatus getScheduleStatus() {
        return scheduleStatus;
    }

    public void setScheduleStatus(ScheduleStatus scheduleStatus) {
        this.scheduleStatus = scheduleStatus;
    }

    public List<Servicing> getServicings() {
        return servicings;
    }

    public void setServicings(List<Servicing> servicings) {
        this.servicings = servicings;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
