package com.barbershop.manager.services;

import com.barbershop.manager.models.DTOs.ClientMinDTO;
import com.barbershop.manager.models.entities.Client;
import com.barbershop.manager.models.exceptions.CpfNullException;
import com.barbershop.manager.models.exceptions.ResourceNotFoundException;
import com.barbershop.manager.repositories.ClientRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class ClientService {

   @Autowired
    private ClientRepository clientRepository;



   @Transactional
   public List<ClientMinDTO> findAll(){
       return clientRepository.findAll().stream().map(obj -> new ClientMinDTO(obj)).toList();
   }

   @Transactional
    public ClientMinDTO findByID(Long id){
        return clientRepository.findById(id)
                .map(obj -> new ClientMinDTO(obj))
                .orElseThrow(() -> new ResourceNotFoundException("Client not found"));
    }
    public ClientMinDTO insert(ClientMinDTO dto){
        Client client = convertDTOtoEntity(dto);
        clientRepository.save(client);
        return new ClientMinDTO(client);
    }

    public Client convertDTOtoEntity(ClientMinDTO dto){
       if(dto != null && dto.getCpf() != null){
           Client client = new Client();
           client.setName(dto.getName());
           client.setCpf(dto.getCpf());
           client.setphone(dto.getPhone());
           client.setEmail(dto.getEmail());
           return client;
       }else {
           throw new CpfNullException("CPF must not be null");
       }
    }



}
