package com.barbershop.manager.repositories;

import com.barbershop.manager.models.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
