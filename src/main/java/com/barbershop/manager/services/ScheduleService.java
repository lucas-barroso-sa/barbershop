package com.barbershop.manager.services;

import com.barbershop.manager.models.DTOs.Schedule.*;
import com.barbershop.manager.models.entities.Schedule;
import com.barbershop.manager.models.enums.ScheduleStatus;
import com.barbershop.manager.models.exceptions.ResourceNotFoundException;
import com.barbershop.manager.repositories.ScheduleRepository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.stream.Collectors;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.Objects;

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



    public ScheduleDTO findById(Long id) {
        return scheduleRepository.findById(id).map(obj -> new ScheduleDTO(obj))
                .orElseThrow(() -> new ResourceNotFoundException("Schedule not found with id "+id));
    }

    public Schedule findEntityById(Long id) {
        return scheduleRepository.findById(id)
                .orElseThrow(()
                        -> new ResourceNotFoundException("Schedule not found with id "+id));
    }

    public List<ScheduleDTO> findAll() {
        return  scheduleRepository.findAll()
                .stream()
                .map(obj -> new ScheduleDTO(obj)).toList();
    }
    public List<ScheduleMinDTO> findAllByClientId(Long clientId) {
        return scheduleRepository.findAllByClientIdOrderByAppointmentTimeAsc(clientId)
                .stream()
                .map(obj -> new ScheduleMinDTO(obj)).toList();
    }

    @Transactional
    public void updateStatus(Long scheduleId, ScheduleStatusDTO scheduleStatusDTO) {
        Schedule schedule = scheduleRepository.findById(scheduleId)
                .orElseThrow(() -> new ResourceNotFoundException("Schedule not found with id "+scheduleId));
        if (schedule.getScheduleStatus() == scheduleStatusDTO.getStatus()) {
            return;
        }

        if(scheduleStatusDTO.getStatus() != ScheduleStatus.COMPLETED) {
            schedule.setScheduleStatus(scheduleStatusDTO.getStatus());
            scheduleRepository.save(schedule);
        }

    }

    public void concludeSchedule(Long scheduleId, BigDecimal value) {
        Schedule schedule = scheduleRepository.findById(scheduleId)
                .orElseThrow(()
                -> new ResourceNotFoundException("Schedule not found with id "+scheduleId));
        schedule.setScheduleStatus(ScheduleStatus.COMPLETED);
        if(Objects.equals(schedule.getScheduleValue(), value)) {
            scheduleRepository.save(schedule);
            return;
        }
        schedule.setScheduleValue(value);
        scheduleRepository.save(schedule);


    }


    @Transactional
    public ScheduleDTO updateSchedule(Long id, ScheduleUpdateDTO dto) {
        Schedule schedule = scheduleRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Schedule not found with id " + id));

        schedule.setAppointmentTime(dto.getAppointmentTime());
        schedule.setClient(clientService.findEntityById(dto.getClientId()));
        schedule.setUser(userService.findEntityById(dto.getUserId()));
        schedule.setScheduleValue(dto.getScheduleValue());

        // 🟢 CORREÇÃO AQUI: Troque .toList() por .collect(Collectors.toList())
        schedule.setServicings(
                dto.getServicingIds()
                        .stream()
                        .map(servicingId -> servicingService.findEntityById(servicingId))
                        .collect(Collectors.toList()) // <<< LISTA MUTÁVEL PARA O HIBERNATE
        );

        schedule = scheduleRepository.save(schedule);
        return new ScheduleDTO(schedule);
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

    public Schedule convertDTOToEntityInsert(ScheduleInsertDTO dto) {
        Schedule schedule = new Schedule();
        if (dto.getAppointmentTime() != null) {
            schedule.setAppointmentTime(dto.getAppointmentTime());
        }else throw  new IllegalArgumentException("Appointment Time must not be null");
        schedule.setScheduleStatus(ScheduleStatus.PENDING);
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
        schedule.setScheduleValue(dto.getScheduleValue());
        return schedule;
    }

    @Transactional
    public ScheduleDTO insert(ScheduleInsertDTO insertDTO) {
        Schedule schedule = convertDTOToEntityInsert(insertDTO);
        scheduleRepository.save(schedule);
        return new ScheduleDTO(schedule);
    }


}
