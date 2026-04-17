package com.barbershop.manager.models.entities;

import com.barbershop.manager.models.enums.EventType;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "tb_histoy")
public class History {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private EventType eventType;
    private String description;
    private double value;
    private LocalDateTime localDateTime;
    private long referencialid;

    public History() {

    }

    public History(long id, EventType eventType, double value, String description, LocalDateTime localDateTime, long referencialid) {
        this.id = id;
        this.eventType = eventType;
        this.value = value;
        this.description = description;
        this.localDateTime = localDateTime;
        this.referencialid = referencialid;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public EventType getEventType() {
        return eventType;
    }

    public void setEventType(EventType eventType) {
        this.eventType = eventType;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public LocalDateTime getLocalDateTime() {
        return localDateTime;
    }

    public void setLocalDateTime(LocalDateTime localDateTime) {
        this.localDateTime = localDateTime;
    }

    public long getReferencialid() {
        return referencialid;
    }

    public void setReferencialid(long referencialid) {
        this.referencialid = referencialid;
    }
}
