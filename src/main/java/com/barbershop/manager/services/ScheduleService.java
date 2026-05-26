package com.barbershop.manager.services;

import com.barbershop.manager.models.DTOs.Schedule.*;
import com.barbershop.manager.models.entities.Schedule;
import com.barbershop.manager.models.enums.ScheduleStatus;
import com.barbershop.manager.models.exceptions.ResourceNotFoundException;
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

    @Autowired
    private FinancialMovementService financialMovementService;

    public ScheduleDTO findById(Long id) {
        return scheduleRepository.findById(id).map(obj -> new ScheduleDTO(obj))
                .orElseThrow(() -> new ResourceNotFoundException("Schedule not found with id "+id));
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

        schedule.setScheduleStatus(scheduleStatusDTO.getStatus());
        //removido if com antíga lógica de completar agendamento -> gera financeiro
        scheduleRepository.save(schedule);
    }

    public 



    @Transactional
    public ScheduleDTO updateSchedule(Long id, ScheduleUpdateDTO dto) {
        Schedule schedule = scheduleRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Schedule not found with id " + id));

        // (Nenhum 'if null' é necessário graças ao @Valid no Controller)
        schedule.setAppointmentTime(dto.getAppointmentTime());
        schedule.setClient(clientService.findEntityById(dto.getClientId()));
        schedule.setUser(userService.findEntityById(dto.getUserId()));
        schedule.setScheduleValue(dto.getScheduleValue());

        schedule.setServicings(
                dto.getServicingIds()
                        .stream()
                        .map(servicingId -> servicingService.findEntityById(servicingId))
                        .toList()
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
