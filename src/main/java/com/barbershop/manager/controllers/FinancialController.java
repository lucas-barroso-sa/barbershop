package com.barbershop.manager.controllers;

import com.barbershop.manager.models.DTOs.financial.FinancialSummaryDTO;
import com.barbershop.manager.services.FinancialSummaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/financial")
public class FinancialController {

    @Autowired
    private FinancialSummaryService service;

    @GetMapping("/summary")
    public ResponseEntity<FinancialSummaryDTO> getSummary() {
        return ResponseEntity.ok(service.getSummary());
    }
}