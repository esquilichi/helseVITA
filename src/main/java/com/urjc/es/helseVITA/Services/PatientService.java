package com.urjc.es.helseVITA.Services;

import com.urjc.es.helseVITA.Entities.Appointment;
import com.urjc.es.helseVITA.Entities.HealthPersonnel;
import com.urjc.es.helseVITA.Entities.Patient;
import com.urjc.es.helseVITA.Repositories.AppointmentRepository;
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
    @Autowired
    AppointmentRepository appointmentRepository;

    public Patient addPatient(Patient patient){
        return patientRepository.save(patient);
    }

    public boolean exists(Integer id){
        //Hay que mirar si funciona, deberia devolver Optional<>
        return patientRepository.findById(id).isPresent();
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

    public Patient searchUsername(String text){
        Optional<Patient> op = patientRepository.findByUsername(text);
        return op.orElse(null);
    }

    public Patient searchEmail(String text){
        Optional<Patient> op = patientRepository.findByEmail(text);
        return op.orElse(null);
    }

    public Patient searchDNI(String text){
        Optional<Patient> op = patientRepository.findByDni(text);
        return op.orElse(null);
    }

    public List<Appointment> addAppointmentToPatient(Integer id , Appointment a){
        Optional<Patient> op  = patientRepository.findById(id);
        if (op.isPresent()){
            var temp = op.get();
            List<Appointment> list = temp.getAppointments();
            list.add(a);
            temp.setAppointments(list);
            patientRepository.save(temp);
            return list;
        }
        return null;
    }

    public Collection<Patient> search(String input) {
        return patientRepository.findPatientByNameContainsIgnoreCaseOrSurname1ContainsIgnoreCaseOrSurname2ContainsIgnoreCaseOrEmailContainsIgnoreCase(input, input, input, input);
    }
    public List<Patient> searchByAge(String input){
        if (input.equals("")){
            return null;
        }else {
            return patientRepository.findPatientByAge(Integer.parseInt(input));
        }

    }
    public void newHealthPersonnel (HealthPersonnel healthPersonnel, Integer id){
        Patient temp = patientRepository.findById(id).get();
        temp.getHealthPersonnelList().set(healthPersonnel.getId(), healthPersonnel);
        Patient temp2 = new Patient(id, temp.getUsername(), temp.getPassword(), 
        temp.getEmail(), temp.getDni(), temp.getName(), temp.getSurname1(), 
        temp.getSurname2(), temp.getAge(), temp.getHealthPersonnelList());
        patientRepository.save(temp2);
    }

    public Patient returnPatientByUsername(String username){
        var temp = patientRepository.findByUsername(username);
        return temp.orElse(null);
    }

    public Appointment returnLastAppointment(Integer patient){
        var lista = appointmentRepository.findAppointmentsByPatientId(patient);
        return lista.get(lista.size() - 1);
    }

    public List<Patient> returnAllPatientsByHealthPersonnel(HealthPersonnel h){
        return patientRepository.findPatientByHealthPersonnelList(h);
    }
}
