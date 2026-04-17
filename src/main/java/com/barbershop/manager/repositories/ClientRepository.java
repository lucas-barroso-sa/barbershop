package com.barbershop.manager.repositories;

import com.barbershop.manager.models.entities.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<Client, Long> {
}
