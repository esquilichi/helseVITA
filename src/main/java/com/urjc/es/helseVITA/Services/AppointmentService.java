package com.urjc.es.helseVITA.Services;

import com.urjc.es.helseVITA.Entities.Appointment;
import com.urjc.es.helseVITA.Repositories.AppointmentRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

public class AppointmentService {
    @Autowired
    AppointmentRepository appointmentRepository;

    
    public Appointment addAppointment(Appointment appointment){
        return appointmentRepository.save(appointment);
    }

    public boolean exists(Integer id){
        //Hay que mirar si funciona, deberia devolver Optional<>
        if (appointmentRepository.findById(id) != null){
            return true;
        } else {
            return false;
        }
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

    public Collection<Appointment> returnAllAppointments(){
        List<Appointment> list = new ArrayList<>();
        appointmentRepository.findAll().forEach(list::add);
        return list;
    }
}
