package com.barbershop.manager.controllers;

import com.barbershop.manager.models.DTOs.ClientMinDTO;
import com.barbershop.manager.services.ClientService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping(value = "/clients")
public class ClientController {

    @Autowired
    ClientService clientService;

    @PutMapping(value = "/{id}")
    public ClientMinDTO updateClient(@Valid @RequestBody ClientMinDTO dto, @PathVariable Long id){
        return clientService.updateById(id, dto);
    }

    @DeleteMapping(value = "/{id}")
    public void deleteClient(@PathVariable Long id){
            clientService.deleteById(id);
    }

    @GetMapping
    public List<ClientMinDTO> findAll() {
        return this.clientService.findAll();
    }



    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ClientMinDTO insert(@RequestBody @Valid ClientMinDTO clientMinDTO) {
        return this.clientService.insert(clientMinDTO);
    }


    @GetMapping(value = "/{id}")
    public ClientMinDTO findById(@PathVariable Long id){
        return clientService.findByID(id);
    }

}
