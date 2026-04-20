package com.barbershop.manager.services;

import com.barbershop.manager.models.DTOs.UserDTO;
import com.barbershop.manager.models.DTOs.UserInsertDTO;
import com.barbershop.manager.models.entities.User;
import com.barbershop.manager.models.exceptions.ResourceNotFoundException;
import com.barbershop.manager.models.exceptions.RoleNullException;
import com.barbershop.manager.repositories.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public User findEntityById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(()->new ResourceNotFoundException("User not found with id " + id));
    }

    @Transactional
    public List<UserDTO> findAll() {
        return userRepository.findAll()
                .stream()
                .map(obj -> new UserDTO(obj)).toList();

    }

    @Transactional
    public UserDTO findById(Long id) {
        return userRepository.findById(id)
                .map(obj -> new UserDTO(obj))
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));
    }

    public User convertDTOtoEntity(UserInsertDTO userDTO) {
        if (userDTO != null && userDTO.getRole() != null && userDTO.getEmail() != null) {
            User user = new User();
            user.setName(userDTO.getName());
            user.setEmail(userDTO.getEmail());
            user.setId(userDTO.getId());
            user.setRole(userDTO.getRole());
            return user;
        } else {
            throw new RoleNullException("Role must not be null");
        }

    }

    public UserDTO insert(UserInsertDTO dto) {
        User user = convertDTOtoEntity(dto);
        userRepository.save(user);
        return new UserDTO(user);

    }

}



