package com.barbershop.manager.services;

import com.barbershop.manager.models.DTOs.user.UserDTO;
import com.barbershop.manager.models.DTOs.user.UserInsertDTO;
import com.barbershop.manager.models.DTOs.user.UserUpdateDTO;
import com.barbershop.manager.models.entities.User;
import com.barbershop.manager.models.enums.UserStatus;
import com.barbershop.manager.models.exceptions.EmailAlreadyExistsException;
import com.barbershop.manager.models.exceptions.ResourceNotFoundException;
import com.barbershop.manager.models.exceptions.RoleNullException;
import com.barbershop.manager.repositories.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

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

    public User convertDTOtoEntity(UserInsertDTO dto) {
        if (dto != null && dto.getRole() != null && dto.getEmail() != null) {
            User user = new User();
            user.setName(dto.getName());
            user.setEmail(dto.getEmail());
            user.setRole(dto.getRole());
            user.setStatus(dto.getStatus());
            String encryptedPassword = passwordEncoder.encode(dto.getPassword());
            user.setPassword(encryptedPassword);
            return user;
        } else {
            throw new RoleNullException("Role must not be null");
        }

    }
    @Transactional
    public UserDTO insert(UserInsertDTO dto) {
        if(userRepository.findByEmail(dto.getEmail()) == null){
            User user = convertDTOtoEntity(dto);
            user.setStatus(UserStatus.ACTIVE);
            userRepository.save(user);
            return new UserDTO(user);
        }
        throw new EmailAlreadyExistsException("Email already exists");


    }

    @Transactional
    public UserDTO update(UserUpdateDTO dto,Long id) {
        User user = findEntityById(id);
        user.setName(dto.getName());
        user.setRole(dto.getRole());
        user.setStatus(dto.getStatus());
        userRepository.save(user);
        return new UserDTO(user);
    }


}



