package com.barbershop.manager.controllers;

import com.barbershop.manager.models.DTOs.PaymentMethodDTO;
import com.barbershop.manager.models.DTOs.PaymentMethodInsertDTO;
import com.barbershop.manager.models.DTOs.PaymentMethodUpdateDTO;
import com.barbershop.manager.services.PaymentMethodService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value ="/payment-methods")
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
    @GetMapping
    public ResponseEntity<List<PaymentMethodDTO>> findAllPaymentMethods() {
        List<PaymentMethodDTO> responseDto = paymentMethodService.findAllPaymentMethods();
        return ResponseEntity.ok(responseDto);
    }

    @PatchMapping(value = "/{id}")
    public ResponseEntity<PaymentMethodDTO> updatePaymentMethod(@PathVariable Long id, @RequestBody @Valid PaymentMethodUpdateDTO updateDTO) {
        PaymentMethodDTO responseDto = paymentMethodService.updatePaymentMethod(id, updateDTO);
        return ResponseEntity.ok(responseDto);
    }



}
