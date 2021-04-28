package com.urjc.es.helseVITA.Services;

import com.urjc.es.helseVITA.Entities.Patient;
import com.urjc.es.helseVITA.Repositories.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Service
public class PatientService {

    @Autowired
    PatientRepository patientRepository;

    public Patient addPatient(Patient patient){
        return patientRepository.save(patient);
    }

    public boolean exists(Integer id){
        //Hay que mirar si funciona, deberia devolver Optional<>
        if (patientRepository.findById(id) != null){
            return true;
        } else {
            return false;
        }
    }

    public void delete(Integer id){
        Patient temp;
        Optional<Patient> tempOptional = patientRepository.findById(id);
            if (tempOptional.isPresent()) {
                temp = tempOptional.get();
                patientRepository.delete(temp);
            }
    }

    public Patient returnPatient(Integer id){
        Patient temp;
        Optional<Patient> tempOptional = patientRepository.findById(id);
        if (tempOptional.isPresent()){
            temp = tempOptional.get();
            return temp;
        }
        return null;
    }

    public Collection<Patient> returnAllPatients(){
        List<Patient> list = new ArrayList<>();
        patientRepository.findAll().forEach(list::add);
        return list;
    }
}