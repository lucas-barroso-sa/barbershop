package com.barbershop.manager.services;

import com.barbershop.manager.models.DTOs.ServicingDTO;
import com.barbershop.manager.models.entities.Servicing;
import com.barbershop.manager.models.exceptions.DataBaseException;
import com.barbershop.manager.repositories.ServicingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ServicingService {
    @Autowired
    private ServicingRepository servicingRepository;


    @Transactional(readOnly = true )
    public Servicing findEntityById(Long id) {
        return servicingRepository.findById(id).orElse(null);
   }
   @Transactional
    public ServicingDTO insert(ServicingDTO dto) {
        Servicing servicing = convertDTOToEntity(dto);
        servicingRepository.save(servicing);
        return new ServicingDTO(servicing);
    }
    public Servicing convertDTOToEntity(ServicingDTO dto) {
        Servicing servicing = new Servicing();
        if (dto.getName() == null || dto.getName().isEmpty()) {
            throw new IllegalArgumentException("Service name must not be empty or null");
        }
        servicing.setName(dto.getName());
        servicing.setPrice(dto.getPrice());
        if(dto.getDurationInMinutes() == 0){
            throw new IllegalArgumentException("Duration must be greater than zero");
        }
        servicing.setDurationInMinutes(dto.getDurationInMinutes());
        return servicing;
    }

    @Transactional(readOnly = true)
    public List<ServicingDTO> findAll(){
        return servicingRepository.findAll()
                .stream()
                .map(obj -> new ServicingDTO(obj)).toList();

    }

    @Transactional
    public ServicingDTO update(ServicingDTO dto, Long id) {
        try {
            Servicing entity = findEntityById(id);
            entity.setName(dto.getName());
            entity.setPrice(dto.getPrice());
            entity.setDurationInMinutes(dto.getDurationInMinutes());
            entity = servicingRepository.save(entity);
            return new ServicingDTO(entity);
        }catch (Exception e){
            throw new DataBaseException("Error while updating entity" + e.getMessage());
        }

    }


}
