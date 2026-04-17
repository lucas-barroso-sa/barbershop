package com.barbershop.manager.services;

import com.barbershop.manager.models.DTOs.UserDTO;
import com.barbershop.manager.models.entities.User;
import com.barbershop.manager.models.exceptions.ResourceNotFoundException;
import com.barbershop.manager.repositories.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Transactional
    public List<UserDTO> findAll() {
        return userRepository.findAll().stream().map(obj -> new UserDTO(obj)).toList();

    }

    @Transactional
    public UserDTO findById(Long id) {
        return userRepository.findById(id)
                .map(obj -> new UserDTO(obj))
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));
    }

}
