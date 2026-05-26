package com.barbershop.manager.services;

import com.barbershop.manager.models.DTOs.FinancialMovementGetMinDTO;
import com.barbershop.manager.models.entities.FinancialMovement;
import com.barbershop.manager.models.entities.Schedule;
import com.barbershop.manager.models.enums.EventType;
import com.barbershop.manager.models.enums.MovementStatus;
import com.barbershop.manager.models.enums.MovementType;
import com.barbershop.manager.repositories.FinancialMovementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class FinancialMovementService {
    @Autowired
    FinancialMovementRepository financialMovementRepository;

    public void generateMovementFromSchedule(Schedule schedule){
        FinancialMovement movement = new FinancialMovement();
        movement.setDescription("Atendimento: " + schedule.getClient().getName());
        movement.setSchedule(schedule);

        movement.setEventType(EventType.SERVICE_PAYMENT);
        movement.setMovementType(MovementType.INCOME);
        movement.setMovementStatus(MovementStatus.PENDING);

        movement.setGrossAmount(schedule.calculateGrossValue());
        movement.setNetAmount(schedule.getScheduleValue());


        movement.setDueDate(LocalDate.now());
        movement.setPaymentDate(null);

        financialMovementRepository.save(movement);


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
