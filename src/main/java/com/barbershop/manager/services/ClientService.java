package com.barbershop.manager.services;

import com.barbershop.manager.models.DTOs.ClientMinDTO;
import com.barbershop.manager.models.entities.Client;
import com.barbershop.manager.models.exceptions.ResourceNotFoundException;
import com.barbershop.manager.repositories.ClientRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@Service
public class ClientService {

   @Autowired
    private ClientRepository clientRepository;

   @Transactional
    public ClientMinDTO findClientByID(Long id){
        Optional<Client> optionalClient = clientRepository.findById(id);
        if(optionalClient.isPresent()){
            Client client = optionalClient.get();
            return new ClientMinDTO(client);
        }else {
            throw new ResourceNotFoundException("Client not found");
        }


    }




}
