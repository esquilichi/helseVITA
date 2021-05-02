package com.urjc.es.helseVITA.Services;

import com.urjc.es.helseVITA.Entities.Appointment;
import com.urjc.es.helseVITA.Entities.HealthPersonnel;
import com.urjc.es.helseVITA.Entities.Patient;
import com.urjc.es.helseVITA.Repositories.AppointmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Service
public class AppointmentService {
    @Autowired
    AppointmentRepository appointmentRepository;

    
    public Appointment addAppointment(Appointment appointment){
        return appointmentRepository.save(appointment);
    }

    public boolean exists(Integer id){
        //Hay que mirar si funciona, deberia devolver Optional<>
        return appointmentRepository.findById(id) != null;
    }

    public boolean existsPatientAppointment(Patient patient){
        return appointmentRepository.findAppointmentsByPatient(patient) != null;
    }

    public boolean existsHealthPersonnelAppointment(HealthPersonnel healthPersonnel){
        return appointmentRepository.findAppointmentsByHealthPersonnel(healthPersonnel) != null;
    }

    public void delete(Integer id){
        Appointment temp;
        Optional<Appointment> tempOptional = appointmentRepository.findById(id);
            if (tempOptional.isPresent()) {
                temp = tempOptional.get();
                appointmentRepository.delete(temp);
            }
    }

    public Appointment returnAppointment(Integer id){
        Appointment temp;
        Optional<Appointment> tempOptional = appointmentRepository.findById(id);
        if (tempOptional.isPresent()){
            temp = tempOptional.get();
            return temp;
        }
        return null;
    }

    public Appointment returnPatientAppointments(Patient patient){
        Appointment temp;
        Optional<Appointment> tempOptional = appointmentRepository.findAppointmentsByPatient(patient);
        if (tempOptional.isPresent()){
            temp = tempOptional.get();
            return temp;
        }
        return null;
    }

    public Appointment returnHealthPersonnelAppointments(HealthPersonnel healthPersonnel){
        Appointment temp;
        Optional<Appointment> tempOptional = appointmentRepository.findAppointmentsByHealthPersonnel(healthPersonnel);
        if (tempOptional.isPresent()){
            temp = tempOptional.get();
            return temp;
        }
        return null;
    }

    public Collection<Appointment> returnAllAppointments(){
        List<Appointment> list = new ArrayList<>();
        appointmentRepository.findAll().forEach(list::add);
        return list;
    }

    public List <HealthPersonnel> takenHealthPersonnel(Integer year, Integer month, Integer day, Integer hour, Integer minute){
        List <Appointment> temp = appointmentRepository.findAppointmentByYearAndMonthAndDayAndHourAndMinute(year, month, day, hour, minute);
        List <HealthPersonnel> temp2 = new ArrayList<>();
        for(Appointment entry : temp){
            temp2.add(entry.getHealthPersonnel());
        }
        return temp2;
    }
}
