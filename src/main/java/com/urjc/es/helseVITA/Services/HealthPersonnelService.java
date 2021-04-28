package com.urjc.es.helseVITA.Services;

import com.urjc.es.helseVITA.Entities.HealthPersonnel;
import com.urjc.es.helseVITA.Repositories.HealthPersonnelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class HealthPersonnelService {

    @Autowired
    HealthPersonnelRepository healthPersonnelRepository;

    public Optional<HealthPersonnel> findById(Integer id){
        return healthPersonnelRepository.findById(id);
    }

    public List<HealthPersonnel> findAll(){
        return healthPersonnelRepository.findAll();
    }

    public List<HealthPersonnel> findByDni(String dni){
        return healthPersonnelRepository.findHealthPersonnelByDni(dni);
    }

    public List<HealthPersonnel> findByUsername(String username){
        return healthPersonnelRepository.findHealthPersonnelByUsername(username);
    }
}
