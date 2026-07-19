package com.barbershop.manager.controllers;

import com.barbershop.manager.models.DTOs.financial.*;
import com.barbershop.manager.services.FinancialMovementService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequestMapping(value = "financial-movements")
public class FinancialMovementController {

    private final FinancialMovementService financialMovementService;

    public FinancialMovementController(FinancialMovementService financialMovementService) {
        this.financialMovementService = financialMovementService;
    }


    @PatchMapping(value = "/{id}/settle")
    public ResponseEntity<Void> settleMovement(@PathVariable Long id, @Valid @RequestBody SettleMovementDTO dto) {
        financialMovementService.settleMovement(id, dto);
        return ResponseEntity.noContent().build();
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<FinancialMovementDTO> findById(@PathVariable Long id){
        FinancialMovementDTO dto = financialMovementService.findById(id);
        return ResponseEntity.ok(dto);
    }

    @PostMapping(value = "/checkout")
    public ResponseEntity<FinancialMovementScheduleDTO> checkOut(@Valid @RequestBody CheckOutDTO dto) {
        FinancialMovementScheduleDTO result = financialMovementService.scheduleCheckOut(dto);
        return ResponseEntity.ok(result);
    }

    @GetMapping(value = "/receivables")
    public ResponseEntity<Page<FinancialMovementGetMinDTO>> findReceivablesMin(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate,
            Pageable pageable) {

        Page<FinancialMovementGetMinDTO> page = financialMovementService.findReceivablesMin(startDate, endDate, pageable);
        return ResponseEntity.ok(page);
    }

    @GetMapping(value = "/payables")
    public ResponseEntity<Page<FinancialMovementGetMinDTO>> findPayablesMin(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate,
            Pageable pageable) {

        Page<FinancialMovementGetMinDTO> page = financialMovementService.findPayablesMin(startDate, endDate, pageable);
        return ResponseEntity.ok(page);
    }

    @GetMapping(value = "/cash-flow")
    public ResponseEntity<Page<FinancialMovementGetMinDTO>> findCashFLowMin(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate,
            Pageable pageable) {

        Page<FinancialMovementGetMinDTO> page = financialMovementService.findCashFLowMin(startDate, endDate, pageable);
        return ResponseEntity.ok(page);
    }


}
