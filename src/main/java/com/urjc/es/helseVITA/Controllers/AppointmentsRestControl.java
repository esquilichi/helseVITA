package com.urjc.es.helseVITA.Controllers;
import com.urjc.es.helseVITA.Entities.*;
import com.urjc.es.helseVITA.Services.PatientService;
import org.springframework.web.bind.annotation.RestController;
import com.urjc.es.helseVITA.Services.AppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Collection;

@RestController
public class AppointmentsRestControl {
    
    @Autowired
    AppointmentService appointmentService;

    @Autowired
    PatientService patientService;

    @PostMapping("/api/appointments")
    @ResponseStatus(HttpStatus.CREATED)
    public Appointment newAppointment(@RequestBody Appointment appointment){
        return appointmentService.addAppointment(appointment);
    }

    @PutMapping("/api/appointments/{id}")
    public ResponseEntity<Appointment> updateAppointment(@PathVariable Integer id, @RequestBody Appointment appointment){
        if (appointmentService.exists(id)){
            return new ResponseEntity<>(appointmentService.addAppointment(appointment), HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/api/appointments/{id}")
    public ResponseEntity<Appointment> deleteAppointment(@PathVariable Integer id){
        if (appointmentService.exists(id)){
            return  new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    //Get one specified user
    @GetMapping("/api/appointments/{id}")
    public ResponseEntity<Appointment> getSingleAppointment(@PathVariable Integer id){
        if (appointmentService.exists(id)){
            return new ResponseEntity<>(appointmentService.returnAppointment(id),HttpStatus.OK);
        }else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    //Get all appointments from one patient
    @GetMapping("/api/appointments/{patient}")
    public ResponseEntity<Appointment> getPatientAppointment(@PathVariable String patient){
        var temp = patientService.returnPatientByUsername(patient);
        if (temp != null){
                return new ResponseEntity<>(appointmentService.returnPatientAppointments(temp),HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    //Get all appointment from one health personnel
    @GetMapping("/api/appointments/{healthPersonnel}")
    public ResponseEntity<Appointment> getHealthPersonnelAppointment(@PathVariable HealthPersonnel healthPersonnel){
        if (!appointmentService.existsHealthPersonnelAppointment(healthPersonnel))
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        else {
            return new ResponseEntity<>(appointmentService.returnHealthPersonnelAppointments(healthPersonnel),HttpStatus.OK);
        }
    }


    //Get All Users
    @GetMapping("/api/appointments")
    @ResponseStatus(HttpStatus.OK)
    public Collection<Appointment> returnAll(){
        return appointmentService.returnAllAppointments();
    }

    @PatchMapping("/api/appointments/{id}")
    public ResponseEntity<Appointment> patchAppointment(@PathVariable Integer id, @RequestBody Appointment appointment){
        if (appointmentService.exists(id)){
            //Get actual Appointment with that ID
            Appointment temp = appointmentService.returnAppointment(id);

            if (appointment.getDay() != null)
                temp.setDay(appointment.getDay());
            if(appointment.getHour() != null)
                temp.setHour(appointment.getHour());
            if (appointment.getMonth() != null)
                temp.setMonth(appointment.getMonth());
            if (appointment.getYear() != null)
                temp.setYear(appointment.getYear());
            
            return new ResponseEntity<>(appointmentService.addAppointment(temp),HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

}
