package com.barbershop.manager.repositories;

import com.barbershop.manager.models.entities.FinancialMovement;
import com.barbershop.manager.models.enums.MovementStatus;
import com.barbershop.manager.models.enums.MovementType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;


import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public interface FinancialMovementRepository extends JpaRepository<FinancialMovement, Long> {

    // buscar "contas a pagar" e "contas a receber"
    Page<FinancialMovement> findByMovementTypeAndMovementStatusAndDueDateBetween(
            MovementType movementType,
            MovementStatus movementStatus,
            LocalDate startDate,
            LocalDate endDate,
            Pageable pageable
    );

    // Busca o "Caixa" (Traz INCOME e EXPENSE misturados)
    Page<FinancialMovement> findByMovementStatusAndPaymentDateBetweenOrderByPaymentDateDesc(
            MovementStatus status,
            LocalDate start,
            LocalDate end,
            Pageable pageable);

    // Busca todas as movimentações pendentes para calcular atrasos, hoje e semana financia summary
    List<FinancialMovement> findAllByMovementStatus(MovementStatus status);


}
