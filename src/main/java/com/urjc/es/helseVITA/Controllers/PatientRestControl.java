package com.urjc.es.helseVITA.Controllers;

import com.urjc.es.helseVITA.Services.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.urjc.es.helseVITA.Entities.*;
import java.util.Collection;

@RestController
public class PatientRestControl {

    @Autowired
    PatientService patientService;

    @PostMapping("/api/patients")
    @ResponseStatus(HttpStatus.CREATED)
    public Patient newPatient(@RequestBody Patient patient){
        return patientService.addPatient(patient);
    }

    @PutMapping("/api/patients/{id}")
    public ResponseEntity<Patient> updatePatient(@PathVariable Integer id, @RequestBody Patient patient){
        if (patientService.exists(id)){
            return new ResponseEntity<>(patientService.addPatient(patient), HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/api/patients/{id}")
    public ResponseEntity<Patient> deletePatient(@PathVariable Integer id){
        if (patientService.exists(id)){
            return  new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    //Get one specified user
    @GetMapping("/api/patients/{id}")
    public ResponseEntity<Patient> getSinglePatient(@PathVariable Integer id){
        if (patientService.exists(id)){
            return new ResponseEntity<>(patientService.returnPatient(id),HttpStatus.OK);
        }else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    //Get All Users
    @GetMapping("/api/patients")
    @ResponseStatus(HttpStatus.OK)
    public Collection<Patient> returnAll(){
        return patientService.returnAllPatients();
    }

    @PatchMapping("/api/patients/{id}")
    public ResponseEntity<Patient> patchPatient(@PathVariable Integer id, @RequestBody Patient patient){
        if (patientService.exists(id)){
            //Get actual Patient with that ID
            Patient temp = patientService.returnPatient(id);

            if (patient.getName() != null)
                temp.setName(patient.getName());
            if(patient.getSurename1() != null)
                temp.setSurename1(patient.getSurename1());
            if (patient.getSurename2() != null)
                temp.setSurename2(patient.getSurename2());
            if (patient.getDni() != null)
                temp.setDni(patient.getDni());
            if (patient.getEmail() != null)
                temp.setEmail(patient.getEmail());
            if (patient.getPassword() != null)
                temp.setPassword(patient.getPassword());
            if (patient.getUsername() != null)
                temp.setUsername(patient.getUsername());
            if (patient.getAge() < 0)
                temp.setAge(patient.getAge());

            return new ResponseEntity<>(patientService.addPatient(temp),HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}