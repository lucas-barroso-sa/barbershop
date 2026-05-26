package com.barbershop.manager.controllers;

import com.barbershop.manager.models.DTOs.BankAccountDTO;
import com.barbershop.manager.models.DTOs.BankAccountInsertDTO;
import com.barbershop.manager.models.DTOs.FinancialMovementGetMinDTO;
import com.barbershop.manager.services.BankAccountService;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

}
