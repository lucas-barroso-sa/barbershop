package com.barbershop.manager.controllers;

import com.barbershop.manager.models.DTOs.ScheduleDTO;
import com.barbershop.manager.models.DTOs.ScheduleInsertDTO;
import com.barbershop.manager.services.ScheduleService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
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
    public ResponseEntity<List<ScheduleDTO>> findByDate(
            @RequestParam(value = "date", required = false)
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
        if (date == null) {
            date = LocalDate.now();
        }
        List<ScheduleDTO> list = scheduleService.findByDate(date);
        return ResponseEntity.ok().body(list);
    }
    @PostMapping
    public ScheduleDTO insertSchedule(@RequestBody @Valid ScheduleInsertDTO dto) {
        return scheduleService.insert(dto);
    }

}
