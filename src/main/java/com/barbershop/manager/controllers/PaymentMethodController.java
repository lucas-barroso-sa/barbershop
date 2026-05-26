package com.barbershop.manager.controllers;

import com.barbershop.manager.models.DTOs.bankacc.BankAccountDTO;
import com.barbershop.manager.models.DTOs.PaymentMethodDTO;
import com.barbershop.manager.models.DTOs.PaymentMethodInsertDTO;
import com.barbershop.manager.services.PaymentMethodService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value ="/paymentMethods")
public class PaymentMethodController {

    private PaymentMethodService paymentMethodService;

    public PaymentMethodController(PaymentMethodService paymentMethodService) {
        this.paymentMethodService = paymentMethodService;
    }

    @PostMapping
    public ResponseEntity<PaymentMethodDTO> insertPaymentMethod(@RequestBody PaymentMethodInsertDTO insertDTO) {
        PaymentMethodDTO responseDto = paymentMethodService.insertPaymentMethod(insertDTO);
        return ResponseEntity.ok(responseDto);
    }


}
