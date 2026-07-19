package com.barbershop.manager.controllers;

import com.barbershop.manager.models.DTOs.bankacc.BankAccountDTO;
import com.barbershop.manager.models.DTOs.bankacc.BankAccountInsertDTO;
import com.barbershop.manager.models.DTOs.bankacc.BankAccountUpdateDTO;
import com.barbershop.manager.services.BankAccountService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/bank")
public class BankAccountController {

    private BankAccountService bankAccountService;

    public BankAccountController(BankAccountService bankAccountService) {
        this.bankAccountService = bankAccountService;
    }

    @PostMapping
    public ResponseEntity<BankAccountDTO> insertBankAccount(@RequestBody BankAccountInsertDTO insertDto) {
        BankAccountDTO responseDto = bankAccountService.insertBankAccount(insertDto);
        return ResponseEntity.ok(responseDto);
    }
    @GetMapping
    public List<BankAccountDTO> findAllBankAccount() {
        return bankAccountService.findAll();
    }

    @PatchMapping(value = "/{id}")
    public ResponseEntity<BankAccountDTO> updateBankAccount(
            @PathVariable Long id,
            @RequestBody @Valid BankAccountUpdateDTO dto) {

        BankAccountDTO responseDto = bankAccountService.updateBankAccount(id, dto);
        return ResponseEntity.ok(responseDto);
    }

}
