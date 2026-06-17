package com.barbershop.manager.services;

import com.barbershop.manager.models.DTOs.bankacc.BankAccountDTO;
import com.barbershop.manager.models.DTOs.bankacc.BankAccountInsertDTO;
import com.barbershop.manager.models.entities.BankAccount;
import com.barbershop.manager.models.exceptions.ResourceNotFoundException;
import com.barbershop.manager.repositories.BankAccountRepository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class BankAccountService {
    @Autowired
    private BankAccountRepository bankAccountRepository;

    @Transactional
    public BankAccountDTO insertBankAccount(BankAccountInsertDTO dto) {

        BankAccount bankAccount = new BankAccount();
        bankAccount.setName(dto.getName());
        bankAccount.setBalance(dto.getBalance());
        bankAccountRepository.save(bankAccount);
        return new BankAccountDTO(bankAccount);

    }

    @Transactional(readOnly = true)
    public List<BankAccountDTO> findAll() {
        return bankAccountRepository.findAll()
                .stream()
                .map(obj -> new BankAccountDTO(obj)).toList();
    }

    @Transactional(readOnly = true)
    public BankAccount findEntityById(Long id) {
        return bankAccountRepository.findById(id).orElseThrow(()
                -> new ResourceNotFoundException("Bank Account not found with id " + id));
    }


}
