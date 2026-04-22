package com.barbershop.manager.services;

import com.barbershop.manager.models.DTOs.Schedule.ScheduleDTO;
import com.barbershop.manager.models.DTOs.Schedule.ScheduleInsertDTO;
import com.barbershop.manager.models.DTOs.Schedule.ScheduleMinDTO;
import com.barbershop.manager.models.entities.Schedule;
import com.barbershop.manager.models.enums.ScheduleStatus;
import com.barbershop.manager.repositories.ScheduleRepository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@Service
public class ScheduleService {

    @Autowired
    private ScheduleRepository scheduleRepository;
    @Autowired
    private ServicingService servicingService;
    @Autowired
    private ClientService clientService;
    @Autowired
    private UserService userService;

    public List<ScheduleDTO> findAll() {
        return  scheduleRepository.findAll()
                .stream()
                .map(obj -> new ScheduleDTO(obj)).toList();
    }


    @Transactional(readOnly = true)
    public List<ScheduleMinDTO> findByDate(LocalDate date) {
        LocalDateTime startOfDay = date.atStartOfDay();
        LocalDateTime endOfDay = date.atTime(LocalTime.MAX);
        List<Schedule> schedules = scheduleRepository.findByAppointmentTimeBetweenOrderByAppointmentTimeAsc(
                startOfDay, endOfDay
        );
        return schedules.stream().map(obj -> new ScheduleMinDTO(obj)).toList();
    }

    public Schedule convertDTOToEntity(ScheduleInsertDTO dto) {
        Schedule schedule = new Schedule();
        if (dto.getAppointmentTime() != null) {
            schedule.setAppointmentTime(dto.getAppointmentTime());
        }else throw  new IllegalArgumentException("Appointment Time must not be null");
        schedule.setScheduleStatus(ScheduleStatus.pending);
        if (dto.getServicingIds() == null || dto.getServicingIds().isEmpty()) {
            throw new IllegalArgumentException("At least one servicing must be specified");
        }
        schedule.setServicings(dto.getServicingIds()
                .stream()
                .map(id -> servicingService.findEntityById(id)).toList()
        );
        if (dto.getClientId() == null){
            throw new IllegalArgumentException("The client id must not be null");
        }
        schedule.setClient(clientService.findEntityById(dto.getClientId()));
        if (dto.getUserId() == null) {
            throw new IllegalArgumentException("Barber must be specified");
        }
        schedule.setUser(userService.findEntityById(dto.getUserId()));
        return schedule;
    }

    @Transactional
    public ScheduleDTO insert(ScheduleInsertDTO insertDTO) {
        Schedule schedule = convertDTOToEntity(insertDTO);
        scheduleRepository.save(schedule);
        return new ScheduleDTO(schedule);
    }


}
