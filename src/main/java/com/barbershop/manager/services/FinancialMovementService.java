package com.barbershop.manager.services;

import com.barbershop.manager.models.entities.FinancialMovement;
import com.barbershop.manager.models.entities.Schedule;
import com.barbershop.manager.models.enums.EventType;
import com.barbershop.manager.models.enums.MovementStatus;
import com.barbershop.manager.models.enums.MovementType;
import com.barbershop.manager.repositories.FinancialMovementRepository;
import org.springframework.beans.factory.annotation.Autowired;
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
}
