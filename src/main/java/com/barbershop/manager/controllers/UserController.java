package com.barbershop.manager.controllers;

import com.barbershop.manager.models.DTOs.UserDTO;
import com.barbershop.manager.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
