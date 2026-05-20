package com.barbershop.manager.repositories;

import com.barbershop.manager.models.entities.Schedule;
import com.barbershop.manager.models.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDateTime;
import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {
    UserDetails findByEmail(String email);

}
