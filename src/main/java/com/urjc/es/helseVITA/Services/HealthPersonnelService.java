package com.urjc.es.helseVITA.Services;

import com.urjc.es.helseVITA.Entities.Appointment;
import com.urjc.es.helseVITA.Entities.HealthPersonnel;
import com.urjc.es.helseVITA.Entities.Patient;
import com.urjc.es.helseVITA.Repositories.HealthPersonnelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class HealthPersonnelService {

    @Autowired
    HealthPersonnelRepository healthPersonnelRepository;    

    @Autowired
    AppointmentService appointmentService;

    
    public HealthPersonnel addHealthPersonnel(HealthPersonnel HealthPersonnel){
        return healthPersonnelRepository.save(HealthPersonnel);
    }

    public boolean exists(Integer id){
        //Hay que mirar si funciona, deberia devolver Optional<>
        if (healthPersonnelRepository.findById(id).isPresent()){
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

    public List<HealthPersonnel> returnAllHealthPersonnels(){
        return healthPersonnelRepository.findAll();
    }

    public List<HealthPersonnel> search(String input){
        return healthPersonnelRepository.findHealthPersonnelByNameContainsIgnoreCaseOrSurname1ContainsIgnoreCaseOrSurname2ContainsIgnoreCaseOrEmailContainsIgnoreCase(input, input, input, input);
    }

    public List<HealthPersonnel> searchByAge(String input){
        if (input.equals("")){
            return null;
        }{
            return healthPersonnelRepository.findByAge(Integer.parseInt(input));
        }

    }

	public List <HealthPersonnel> availableHealthPersonnel(Appointment appointment) {
        List <HealthPersonnel> temp = healthPersonnelRepository.findAll();

        List<HealthPersonnel> tempOptional = appointmentService.takenHealthPersonnel(appointment.getYear(), appointment.getMonth(), appointment.getDay(), appointment.getHour(), appointment.getMinute());
        
        for(HealthPersonnel entry : tempOptional){
            temp.remove(entry);
        }
        return temp;
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
    public HealthPersonnel returnHealthPersonnelByUsername(String username){
        var temp = healthPersonnelRepository.findHealthPersonnelByUsername(username);
        return temp.orElse(null);
    }

    public List<HealthPersonnel> returnHealthPersonnelsByPatient(List<Patient> lista){
        return healthPersonnelRepository.findHealthPersonnelsByPatientsIn(lista);
    }

    public List<Appointment> addAppointmentToHealthPersonnel(Integer id, Appointment a){
        Optional<HealthPersonnel> op  = healthPersonnelRepository.findById(id);
        if (op.isPresent()){
            var temp = op.get();
            List<Appointment> list = temp.getAppointments();
            list.add(a);
            temp.setAppointments(list);
            healthPersonnelRepository.save(temp);
            return list;
        }
        return null;
    }

    public List <Patient> addPatientToHealthPersonnel(Integer id, Patient patient){
        var temp = healthPersonnelRepository.findById(id).orElse(null);
        List <Patient> list = temp.getPatients();
        list.add(patient);
        healthPersonnelRepository.save(temp);
        return list;
    }
}
