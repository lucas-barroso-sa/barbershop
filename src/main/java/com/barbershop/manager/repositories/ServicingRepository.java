package com.barbershop.manager.repositories;

import com.barbershop.manager.models.entities.Servicing;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ServicingRepository extends JpaRepository<Servicing, Long> {

}
