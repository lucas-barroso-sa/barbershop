package com.barbershop.manager.services;

import com.barbershop.manager.models.DTOs.financial.*;
import com.barbershop.manager.models.entities.BankAccount;
import com.barbershop.manager.models.entities.FinancialMovement;
import com.barbershop.manager.models.entities.PaymentMethod;
import com.barbershop.manager.models.entities.Schedule;
import com.barbershop.manager.models.enums.MovementStatus;
import com.barbershop.manager.models.enums.MovementType;
import com.barbershop.manager.models.exceptions.ResourceNotFoundException;
import com.barbershop.manager.repositories.FinancialMovementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
public class FinancialMovementService {
    @Autowired
    FinancialMovementRepository financialMovementRepository;
    @Autowired
    ScheduleService scheduleService;
    @Autowired
    PaymentMethodService paymentMethodService;
    @Autowired
    BankAccountService bankAccountService;

    @Transactional
    public FinancialMovementScheduleDTO scheduleCheckOut(CheckOutDTO dto) {
        Schedule schedule = scheduleService.findEntityById(dto.getScheduleId());
        PaymentMethod paymentMethod = paymentMethodService.findEntityById(dto.getPaymentMethodId());

        FinancialMovement movement = new FinancialMovement(
                schedule,
                paymentMethod,
                schedule.calculateGrossValue(),
                dto.getValue(),
                LocalDate.now()
        );

        financialMovementRepository.save(movement);
        scheduleService.concludeSchedule(dto.getScheduleId(),dto.getValue());

        return new FinancialMovementScheduleDTO(movement);
    }

    @Transactional(readOnly = true)
    public FinancialMovementDTO findById (Long id){
        FinancialMovement entity = financialMovementRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Financial Movement not found with id "+id));
        return new FinancialMovementDTO(entity);
    }

    @Transactional
    public void settleMovement(Long id, SettleMovementDTO dto) {
        FinancialMovement movement = financialMovementRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Financial Movement not found with id " + id));

        if (movement.getMovementStatus() == MovementStatus.SETTLED) {
            throw new IllegalStateException("Este movimento financeiro já foi efetivado anteriormente.");
        }

        BankAccount bankAccount = bankAccountService.findEntityById(dto.getBankAccountId());
        if (bankAccount == null) {
            throw new ResourceNotFoundException("Financial Movement returning bankAccount null id: " + id);
        }

        if (movement.getMovementType() == MovementType.INCOME) {
            bankAccount.receive(movement.getNetAmount());
        } else if (movement.getMovementType() == MovementType.EXPENSE) {
            bankAccount.pay(movement.getNetAmount());
        }

        bankAccountService.updateFromMovement(bankAccount);

        movement.setMovementStatus(MovementStatus.SETTLED);
        movement.setPaymentDate(dto.getPaymentDate());

        financialMovementRepository.save(movement);
    }

    @Transactional
    public FinancialMovementDTO insert(FinancialMovementManualInsertDTO dto) {
        PaymentMethod paymentMethod = paymentMethodService.findEntityById(dto.getPaymentMethodId());
        FinancialMovement movement = new FinancialMovement(dto,paymentMethod);
        financialMovementRepository.save(movement);
        return new FinancialMovementDTO(movement);
    }

    public List<FinancialMovement> findAllEntities() {
        try{
            return financialMovementRepository.findAll();
        }catch(Exception e){
            throw new ResourceNotFoundException("Financial Movement not found");
        }
    }

    @Transactional
    public FinancialMovementDTO update(FinancialMovementUpdateDTO dto, Long id) {
        FinancialMovement movement = financialMovementRepository.findById(id).orElseThrow(()
                              -> new ResourceNotFoundException("Financial Movement not found with id " + id));
        movement.setDescription(dto.getDescription());
        movement.setDueDate(dto.getDueDate());

        if(dto.getPaymentDate() != null) {
            movement.setPaymentDate(dto.getPaymentDate());
        }
        if(dto.getBankAccountId() != null) {
            BankAccount bankAccount = bankAccountService.findEntityById(dto.getBankAccountId());
            movement.setBankAccount(bankAccount);
        }
        if(dto.getAmount() != null) {
            //passado duas vezes para verificação no metodo update amount
            movement.updateAmounts(dto.getAmount(), dto.getAmount());
        }
        movement = financialMovementRepository.save(movement);
        return new FinancialMovementDTO(movement);
    }

    public Page<FinancialMovementGetMinDTO> findReceivablesMin(LocalDate inicio, LocalDate fim, Pageable paginacao) {
        return financialMovementRepository.findByMovementTypeAndMovementStatusAndDueDateBetween(
                MovementType.INCOME,
                MovementStatus.PENDING,
                inicio, fim, paginacao
        ).map(np -> new FinancialMovementGetMinDTO(np));
    }


    public Page<FinancialMovementGetMinDTO> findPayablesMin(LocalDate startDate, LocalDate endDate, Pageable pageable) {
        return financialMovementRepository.findByMovementTypeAndMovementStatusAndDueDateBetween(
                MovementType.EXPENSE,
                MovementStatus.PENDING,
                startDate, endDate, pageable
        ).map(FinancialMovementGetMinDTO::new);
    }

    public Page<FinancialMovementGetMinDTO> findCashFLowMin(LocalDate startDate, LocalDate endDate, Pageable pageable) {
        return financialMovementRepository.findByMovementStatusAndPaymentDateBetweenOrderByPaymentDateDesc(
                MovementStatus.SETTLED,
                startDate, endDate, pageable
        ).map(FinancialMovementGetMinDTO::new);
    }




}
