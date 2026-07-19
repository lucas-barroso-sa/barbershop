package com.barbershop.manager.services;

import com.barbershop.manager.models.DTOs.PaymentMethodDTO;
import com.barbershop.manager.models.DTOs.PaymentMethodInsertDTO;
import com.barbershop.manager.models.DTOs.PaymentMethodUpdateDTO;
import com.barbershop.manager.models.entities.BankAccount;
import com.barbershop.manager.models.entities.PaymentMethod;
import com.barbershop.manager.models.exceptions.ResourceNotFoundException;
import com.barbershop.manager.repositories.PaymentMethodRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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


    @Transactional(readOnly = true)
    public List<PaymentMethodDTO> findAllPaymentMethods() {
        return  paymentMethodRepository
                .findAll().stream()
                .map(obj -> new PaymentMethodDTO(obj)).toList();
    }

    @Transactional(readOnly = true)
    public PaymentMethod findEntityById(Long id) {
        return paymentMethodRepository.findById(id).orElseThrow(()
                -> new ResourceNotFoundException("PaymentMethod not found with id " + id));
    }

    @Transactional
    public PaymentMethodDTO updatePaymentMethod(Long id,PaymentMethodUpdateDTO paymentMethodUpdateDTO) {
        PaymentMethod paymentMethod = findEntityById(id);
        paymentMethod.setName(paymentMethodUpdateDTO.getName());
        paymentMethod.setFeePercentage(paymentMethodUpdateDTO.getFeePercentage());
        paymentMethod.setDaysToReceive(paymentMethodUpdateDTO.getDaysToReceive());
        paymentMethod.setDefaultBankAccount(bankAccountService.findEntityById(paymentMethodUpdateDTO.getDefaultBankAccountId()));
        paymentMethodRepository.save(paymentMethod);
        return new PaymentMethodDTO(paymentMethod);
    }

}
