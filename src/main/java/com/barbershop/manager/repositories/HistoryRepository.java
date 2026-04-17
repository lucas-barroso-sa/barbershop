package com.barbershop.manager.repositories;

import com.barbershop.manager.models.entities.History;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HistoryRepository extends JpaRepository<History, Long> {
}
