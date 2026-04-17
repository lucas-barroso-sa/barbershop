package com.barbershop.manager.controllers;

import com.barbershop.manager.models.DTOs.ClientMinDTO;
import com.barbershop.manager.services.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/clients")
public class ClientController {

    @Autowired
    ClientService clientService;


    @GetMapping
    public List<ClientMinDTO> findAll() {
        return this.clientService.findAll();
    }

    @GetMapping(value = "/{id}")
    public ClientMinDTO findById(Long id){
        return clientService.findByID(id);
    }

}
