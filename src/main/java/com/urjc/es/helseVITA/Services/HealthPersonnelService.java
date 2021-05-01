package com.urjc.es.helseVITA.Services;

import com.urjc.es.helseVITA.Entities.HealthPersonnel;
import com.urjc.es.helseVITA.Repositories.HealthPersonnelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Service
public class HealthPersonnelService {

    @Autowired
    HealthPersonnelRepository healthPersonnelRepository;    

    
    public HealthPersonnel addHealthPersonnel(HealthPersonnel HealthPersonnel){
        return healthPersonnelRepository.save(HealthPersonnel);
    }

    public boolean exists(Integer id){
        //Hay que mirar si funciona, deberia devolver Optional<>
        if (healthPersonnelRepository.findById(id) != null){
            return true;
        } else {
            return false;
        }
    }

    public void delete(Integer id){
        HealthPersonnel temp;
        Optional<HealthPersonnel> tempOptional = healthPersonnelRepository.findById(id);
            if (tempOptional.isPresent()) {
                temp = tempOptional.get();
                healthPersonnelRepository.delete(temp);
            }
    }

    public HealthPersonnel returnHealthPersonnel(Integer id){
        HealthPersonnel temp;
        Optional<HealthPersonnel> tempOptional = healthPersonnelRepository.findById(id);
        if (tempOptional.isPresent()){
            temp = tempOptional.get();
            return temp;
        }
        return null;
    }

    public Collection<HealthPersonnel> returnAllHealthPersonnels(){
        List<HealthPersonnel> list = new ArrayList<>();
        healthPersonnelRepository.findAll().forEach(list::add);
        return list;
    }

    public List<HealthPersonnel> search(String input){
        return healthPersonnelRepository.findHealthPersonnelByNameContains(input/*, input, input, input*/); 
    }

    /*
    public HealthPersonnel searchUsername(String text){
        Optional<HealthPersonnel> op = healthPersonnelRepository.findHealthPersonnelByUsername(text);
        if (op.isPresent()){
            return op.get();
        }
        return null;
    }

    public HealthPersonnel searchEmail(String text){
        Optional<HealthPersonnel> op = healthPersonnelRepository.findHealthPersonnelByEmail(text);
        if (op.isPresent()){
            return op.get();
        }
        return null;
    }

    public HealthPersonnel searchDni(String text){
        Optional<HealthPersonnel> op = healthPersonnelRepository.findHealthPersonnelByDni(text);
        if (op.isPresent()){
            return op.get();
        }
        return null;
    }*/
}
