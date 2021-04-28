package com.urjc.es.helseVITA.Controllers;
import com.urjc.es.helseVITA.Entities.*;

import com.urjc.es.helseVITA.Services.HealthPersonnelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Collection;

@RestController
public class HealthPersonnelRestControl {
    @Autowired
    HealthPersonnelService healthPersonnelService;

    @PostMapping("/api/healthPersonnels")
    @ResponseStatus(HttpStatus.CREATED)
    public HealthPersonnel newHealthPersonnel(@RequestBody HealthPersonnel HealthPersonnel){
        return healthPersonnelService.addHealthPersonnel(HealthPersonnel);
    }

    @PutMapping("/api/healthPersonnels/{id}")
    public ResponseEntity<HealthPersonnel> updateHealthPersonnel(@PathVariable Integer id, @RequestBody HealthPersonnel HealthPersonnel){
        if (healthPersonnelService.exists(id)){
            return new ResponseEntity<>(healthPersonnelService.addHealthPersonnel(HealthPersonnel), HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/api/healthPersonnels/{id}")
    public ResponseEntity<HealthPersonnel> deleteHealthPersonnel(@PathVariable Integer id){
        if (healthPersonnelService.exists(id)){
            return  new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    //Get one specified user
    @GetMapping("/api/healthPersonnels/{id}")
    public ResponseEntity<HealthPersonnel> getSingleHealthPersonnel(@PathVariable Integer id){
        if (healthPersonnelService.exists(id)){
            return new ResponseEntity<>(healthPersonnelService.returnHealthPersonnel(id),HttpStatus.OK);
        }else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    //Get All Users
    @GetMapping("/api/healthPersonnels")
    @ResponseStatus(HttpStatus.OK)
    public Collection<HealthPersonnel> returnAll(){
        return healthPersonnelService.returnAllHealthPersonnels();
    }

    @PatchMapping("/api/healthPersonnel/{id}")
    public ResponseEntity<HealthPersonnel> patchHealthPersonnel(@PathVariable Integer id, @RequestBody HealthPersonnel healthPersonnel){
        if (healthPersonnelService.exists(id)){
            //Get actual HealthPersonnel with that ID
            HealthPersonnel temp = healthPersonnelService.returnHealthPersonnel(id);

            if (healthPersonnel.getName() != null)
                temp.setName(healthPersonnel.getName());
            if(healthPersonnel.getSurename1() != null)
                temp.setSurename1(healthPersonnel.getSurename1());
            if (healthPersonnel.getSurename2() != null)
                temp.setSurename2(healthPersonnel.getSurename2());
            if (healthPersonnel.getDni() != null)
                temp.setDni(healthPersonnel.getDni());
            if (healthPersonnel.getEmail() != null)
                temp.setEmail(healthPersonnel.getEmail());
            if (healthPersonnel.getPassword() != null)
                temp.setPassword(healthPersonnel.getPassword());
            if (healthPersonnel.getUsername() != null)
                temp.setUsername(healthPersonnel.getUsername());
            if (healthPersonnel.getAge() < 0)
                temp.setAge(healthPersonnel.getAge());

            return new ResponseEntity<>(healthPersonnelService.addHealthPersonnel(temp),HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
