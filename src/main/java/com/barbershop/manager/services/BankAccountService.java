package com.barbershop.manager.services;

import com.barbershop.manager.models.DTOs.bankacc.BankAccountDTO;
import com.barbershop.manager.models.DTOs.bankacc.BankAccountInsertDTO;
import com.barbershop.manager.models.DTOs.bankacc.BankAccountUpdateDTO;
import com.barbershop.manager.models.entities.BankAccount;
import com.barbershop.manager.models.enums.MovementType;
import com.barbershop.manager.models.exceptions.DataBaseException;
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

    public List<BankAccount> findAllEntities(){
        List<BankAccount> bankAccounts = bankAccountRepository.findAll();
        if(bankAccounts.isEmpty()){
            throw new ResourceNotFoundException("Bank Account not found");
        }
        return bankAccounts;
    }

    @Transactional(readOnly = true)
    public BankAccount findEntityById(Long id) {
        return bankAccountRepository.findById(id).orElseThrow(()
                -> new ResourceNotFoundException("Bank Account not found with id " + id));
    }
    @Transactional
    public void updateFromMovement(BankAccount entity) {
        try{
            bankAccountRepository.save(entity);
        }catch (DataBaseException e){
            e.getStackTrace();
        }

    }
    @Transactional
    public BankAccountDTO updateBankAccount(Long id, BankAccountUpdateDTO dto) {
        BankAccount bankAccount = findEntityById(id);
        bankAccount.setName(dto.getBankAccountName());
        bankAccountRepository.save(bankAccount);
        return new BankAccountDTO(bankAccount);

    }


}
