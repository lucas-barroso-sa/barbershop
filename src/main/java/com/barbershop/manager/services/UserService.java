package com.barbershop.manager.services;

import com.barbershop.manager.models.DTOs.UserDTO;
import com.barbershop.manager.models.entities.User;
import com.barbershop.manager.repositories.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

}
