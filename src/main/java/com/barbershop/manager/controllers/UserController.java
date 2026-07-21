package com.barbershop.manager.controllers;

import com.barbershop.manager.models.DTOs.user.UserDTO;
import com.barbershop.manager.models.DTOs.user.UserInsertDTO;
import com.barbershop.manager.models.DTOs.user.UserUpdateDTO;
import com.barbershop.manager.services.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/users")
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping
    public List<UserDTO> findAll() {
        return this.userService.findAll();
    }

    @GetMapping(value = "/{id}")
    public UserDTO findById(Long id) {
        return userService.findById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UserDTO insert(@RequestBody UserInsertDTO userDTO) {
        return this.userService.insert(userDTO);
    }

    @PatchMapping(value = "/{id}")
    public ResponseEntity<UserDTO> update(@RequestBody @Valid UserUpdateDTO DTO, @PathVariable Long id) {
        UserDTO result = userService.update(DTO, id);
        return ResponseEntity.ok(result);

    }


}
