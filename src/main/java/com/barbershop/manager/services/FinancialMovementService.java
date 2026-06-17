package com.barbershop.manager.services;

import com.barbershop.manager.models.DTOs.CheckOutDTO;
import com.barbershop.manager.models.DTOs.FinancialMovementGetMinDTO;
import com.barbershop.manager.models.DTOs.FinancialMovementScheduleDTO;
import com.barbershop.manager.models.DTOs.PaymentMethodDTO;
import com.barbershop.manager.models.entities.FinancialMovement;
import com.barbershop.manager.models.entities.PaymentMethod;
import com.barbershop.manager.models.entities.Schedule;
import com.barbershop.manager.models.enums.EventType;
import com.barbershop.manager.models.enums.MovementStatus;
import com.barbershop.manager.models.enums.MovementType;
import com.barbershop.manager.repositories.FinancialMovementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;

@Service
public class FinancialMovementService {
    @Autowired
    FinancialMovementRepository financialMovementRepository;
    @Autowired
    ScheduleService scheduleService;
    @Autowired
    PaymentMethodService paymentMethodService;

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
