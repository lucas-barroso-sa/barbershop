package com.barbershop.manager.repositories;

import com.barbershop.manager.models.entities.FinancialMovement;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FinancialMovementRepository extends JpaRepository<FinancialMovement, Long> {
}
