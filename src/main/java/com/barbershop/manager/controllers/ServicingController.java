package com.barbershop.manager.controllers;

import com.barbershop.manager.models.DTOs.ServicingDTO;
import com.barbershop.manager.services.ServicingService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/servicings")
public class ServicingController {
    @Autowired
    private ServicingService servicingService;

    @GetMapping
    public List<ServicingDTO> findAll() {
        return servicingService.findAll();
    }


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ServicingDTO insert(@Valid @RequestBody ServicingDTO dto) {
        return servicingService.insert(dto);
    }

    @PatchMapping(value = "/{id}")
    public ServicingDTO update(@Valid @RequestBody ServicingDTO dto, @PathVariable Long id) {
        return servicingService.update(dto,id);
    }

}
