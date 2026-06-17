package com.barbershop.manager.controllers;

import com.barbershop.manager.models.DTOs.bankacc.BankAccountDTO;
import com.barbershop.manager.models.DTOs.bankacc.BankAccountInsertDTO;
import com.barbershop.manager.services.BankAccountService;
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

}
