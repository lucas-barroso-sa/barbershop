package com.barbershop.manager.repositories;

import com.barbershop.manager.models.entities.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface ScheduleRepository extends JpaRepository<Schedule, Long> {
    List<Schedule> findByAppointmentTimeBetweenOrderByAppointmentTimeAsc(
            LocalDateTime start,
            LocalDateTime end
    );
    List<Schedule>findAllByClientIdOrderByAppointmentTimeAsc(Long clientId);

}
