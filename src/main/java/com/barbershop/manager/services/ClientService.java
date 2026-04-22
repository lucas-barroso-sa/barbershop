package com.barbershop.manager.services;

import com.barbershop.manager.models.DTOs.ClientMinDTO;
import com.barbershop.manager.models.entities.Client;
import com.barbershop.manager.models.exceptions.CpfNullException;
import com.barbershop.manager.models.exceptions.DataBaseException;
import com.barbershop.manager.models.exceptions.ResourceNotFoundException;
import com.barbershop.manager.repositories.ClientRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.orm.ObjectOptimisticLockingFailureException;
import org.springframework.stereotype.Service;
import java.util.ConcurrentModificationException;
import java.util.List;

@Service
public class ClientService {

   @Autowired
    private ClientRepository clientRepository;



   @Transactional
   public List<ClientMinDTO> findAll(){
       return clientRepository.findAll().stream().map(obj -> new ClientMinDTO(obj)).toList();
   }

   public Client findEntityById(Long id){
       return clientRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Client not found with id "+id));
   }

   @Transactional
    public ClientMinDTO findByID(Long id){

        return clientRepository.findById(id)
                .map(obj -> new ClientMinDTO(obj))
                .orElseThrow(() -> new ResourceNotFoundException("Client not found with id " + id));
    }
    public ClientMinDTO insert(ClientMinDTO dto){
        Client client = convertDTOtoEntity(dto);
        clientRepository.save(client);
        return new ClientMinDTO(client);
    }

    public Client convertDTOtoEntity(ClientMinDTO dto){
       if(dto != null && dto.getName() != null){
           Client client = new Client();
           client.setName(dto.getName());
           client.setphone(dto.getPhone());
           client.setEmail(dto.getEmail());
           return client;
       }else {
           throw new CpfNullException("Name must not be null");
       }
    }

// TODO(refactor: validation with equals to compare Strings, verifie if CPF exists)
    public void updateEntityFromDTO(Client entity, ClientMinDTO dto){
       if(dto.getName() != null && dto.getName() != entity.getName()){
           entity.setName(dto.getName());
       }
       if(dto.getPhone() !=null){
           entity.setphone(dto.getPhone());
       }
       if(dto.getEmail() !=null){
           entity.setEmail(dto.getEmail());
       }
    }

    @Transactional
    public ClientMinDTO updateById(Long id, ClientMinDTO dto){
       try{
           Client client = clientRepository.findById(id)
                   .orElseThrow(() -> new ResourceNotFoundException("Client not found with id " + id));
           updateEntityFromDTO(client, dto);
           Client entity = clientRepository.save(client);
           return new ClientMinDTO(entity);
       }
       catch (DataIntegrityViolationException e){
           throw new DataBaseException("Integrity violation during update. Check unique fields or foreign keys.");
       }
       catch (ObjectOptimisticLockingFailureException e){
           throw new ConcurrentModificationException("Record was updated by another user. Please refresh and try again.");
       }
   }
    @Transactional
    public void deleteById(Long id) {
        if (id == null) {
            // Lançamos uma exceção clara, convertida pelo Spring para 400 Bad Request
            throw new IllegalArgumentException("O ID do recurso é obrigatório para a exclusão.");
        }
        try {
            clientRepository.deleteById(id);

        } catch (EmptyResultDataAccessException e) {
            throw new ResourceNotFoundException("Property not found with ID: " + id);
        } catch (DataIntegrityViolationException e) {
            throw new DataBaseException("Integrity violation: Property ID " + id + " is referenced by other data (e.g., Reservations).");
        }
    }


}
