package com.barbershop.manager.controllers;

import com.barbershop.manager.models.DTOs.Schedule.*;
import com.barbershop.manager.services.ScheduleService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping(value = "/schedules")
public class ScheduleController {
    @Autowired
    ScheduleService scheduleService;

    @GetMapping
    public ResponseEntity<List<ScheduleMinDTO>> findByDate(
            @RequestParam(value = "date", required = false)
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
        if (date == null) {
            date = LocalDate.now();
        }
        List<ScheduleMinDTO> list = scheduleService.findByDate(date);
        return ResponseEntity.ok().body(list);
    }
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public ScheduleDTO insertSchedule(@RequestBody @Valid ScheduleInsertDTO dto) {
        return scheduleService.insert(dto);
    }
    @GetMapping(value = "/client/{clientId}") //endpoint para popular historico nos detalhes do cliente
    public ResponseEntity<List<ScheduleMinDTO>> findAllByClientId(@PathVariable("clientId") Long clientId) {
        List<ScheduleMinDTO> list = scheduleService.findAllByClientId(clientId);
        return ResponseEntity.ok().body(list);
    }

    @GetMapping(value = "/{id}")
    public ScheduleDTO findById(@PathVariable("id") Long id) {
        return scheduleService.findById(id);
    }
    @PatchMapping(value = "/{id}/status")
    public ResponseEntity<Void> updateStatus(
            @PathVariable Long id,
            @RequestBody @Valid ScheduleStatusDTO dto) {

        scheduleService.updateStatus(id, dto);
        return ResponseEntity.noContent().build(); // Retorna 204
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<ScheduleDTO> updateSchedule(@PathVariable Long id, @RequestBody @Valid ScheduleUpdateDTO dto) {
        ScheduleDTO updatedDto = scheduleService.updateSchedule(id,dto);
        return ResponseEntity.ok().body(updatedDto);
    }

}
