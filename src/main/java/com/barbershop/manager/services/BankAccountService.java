package com.barbershop.manager.services;

import com.barbershop.manager.models.DTOs.BankAccountDTO;
import com.barbershop.manager.models.DTOs.BankAccountInsertDTO;
import com.barbershop.manager.models.entities.BankAccount;
import com.barbershop.manager.repositories.BankAccountRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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


}
