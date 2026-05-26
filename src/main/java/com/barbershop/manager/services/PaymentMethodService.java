package com.barbershop.manager.services;

import com.barbershop.manager.models.DTOs.PaymentMethodDTO;
import com.barbershop.manager.models.DTOs.PaymentMethodInsertDTO;
import com.barbershop.manager.models.entities.BankAccount;
import com.barbershop.manager.models.entities.PaymentMethod;
import com.barbershop.manager.models.exceptions.ResourceNotFoundException;
import com.barbershop.manager.repositories.PaymentMethodRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PaymentMethodService {
    @Autowired
    private PaymentMethodRepository paymentMethodRepository;
    @Autowired
    private BankAccountService bankAccountService;

    @Transactional
    public PaymentMethodDTO insertPaymentMethod(PaymentMethodInsertDTO paymentMethodInsertDTO) {
        PaymentMethod paymentMethod = new PaymentMethod();
        paymentMethod.setName(paymentMethodInsertDTO.getName());
        paymentMethod.setFeePercentage(paymentMethodInsertDTO.getFeePercentage());
        paymentMethod.setDaysToReceive(paymentMethodInsertDTO.getDaysToReceive());
        BankAccount bankAccount = bankAccountService.findEntityById(paymentMethodInsertDTO.getDefaultBankAccountId());
        if(bankAccount == null){
            throw new ResourceNotFoundException("Bank account not found with id " + paymentMethodInsertDTO.getDefaultBankAccountId());
        }
        paymentMethod.setDefaultBankAccount(bankAccount);

        paymentMethodRepository.save(paymentMethod);
        return new PaymentMethodDTO(paymentMethod);

    }

    public List<PaymentMethodDTO> findAllPaymentMethods() {
        return  paymentMethodRepository
                .findAll().stream()
                .map(obj -> new PaymentMethodDTO(obj)).toList();
    }

}
