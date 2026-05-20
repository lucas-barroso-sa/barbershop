package com.barbershop.manager.controllers;

import com.barbershop.manager.models.DTOs.user.UserDTO;
import com.barbershop.manager.models.DTOs.user.UserInsertDTO;
import com.barbershop.manager.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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


}
