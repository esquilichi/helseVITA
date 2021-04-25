package spring.io;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;


//This is the class that manages the API rest
@RestController
public class PatientRestControl {


    @Autowired
    PatientRepository repository;


    //ADD USER FOR PATIENT
    @PostMapping("/api/patients")
    @ResponseStatus(HttpStatus.CREATED)
    public Patient newPatient(@RequestBody Patient patient) {
        return repository.save(patient);
    }

    //FIND USER FOR PATIENT AND HEALTH SERVICE

    @PutMapping("/api/patients/{id}")
    public ResponseEntity<Patient> updatePatient(@PathVariable Integer id, @RequestBody Patient patient) {
        Optional <Patient> op = repository.findById(id);
        if (op.isPresent()) {
            repository.editPatient(patient, id);
            return new ResponseEntity<>(patient, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);

    }

    //DELETE USER

    @DeleteMapping("/api/patients/{id}")
    public ResponseEntity<Patient> deletePatient(@PathVariable Integer id) {
        Optional <Patient> op = repository.findById(id);
        if (op.isPresent()) {
            Patient patient = op.get();
            repository.delete(patient);

            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }



    //GET ONE SPECIFIED USER

    @GetMapping("/api/patients/{id}")
    public ResponseEntity<Patient> getPatient(@PathVariable Integer id) {
        Optional <Patient> op = repository.findById(id);
        if (op.isPresent()) {
            Patient patient = op.get();
            return new ResponseEntity<>(patient, HttpStatus.OK);
        } else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    //GET ALL USERS

    @GetMapping("/api/patients")
    @ResponseStatus(HttpStatus.OK)
    public Collection<Patient> advertisements() {
        return repository.findAll();
    }


    //UPDATE ONLY SPECIFIED FIELDS
    @PatchMapping("/api/patients/{id}")
    public ResponseEntity<Patient> patch(@RequestBody Patient patient, @PathVariable Integer id) {
        Optional <Patient> op = repository.findById(id);
        if (op.isPresent()) {
            //Patient patientTemp = op.get();
            if (patient.getUsername() != null)
                repository.updateUsername(patient.getUsername(), id);
            if (patient.getPassword() != null)
                repository.updatePassword(patient.getPassword(), id);
            if (patient.getEmail() != null)
                repository.updateEmail(patient.getEmail(), id);
            if (patient.getdni() != null)
                repository.updateDni(patient.getdni(), id);

            return new ResponseEntity<>(patient, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @PostMapping("/api/patients/{id}/appointments")
    public ResponseEntity<Appointment> newAppointment(@RequestBody Appointment appointment, @PathVariable Integer id) {
        Optional <Patient> op = this.repository.findById(id);
        if (op.isPresent()){
            Patient temp = op.get();
            temp.getAppointments().add(appointment);
            return new ResponseEntity<>(temp, HttpStatus.OK);
        }
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/api/patients/{id}/appointments")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<Appointment>> returnAllAppoinments(@PathVariable Integer id){
        Optional <Patient> op = this.repository.findById(id);
        if(op.isPresent()){
            return new ResponseEntity<>(repository.returnAllAppointments(id),HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/api/patients/{id}/appointments/{id_appointment}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Appointment> returnAppoinment(@PathVariable Integer id,  @PathVariable Integer id_appointment){
        Optional <Patient> op = this.repository.findById(id);
        if(op.isPresent()){
            if(repository.appointmentExists(id, id_appointment)){
                var a = patientManager.returnAppointment(id, id_appointment);
                return new ResponseEntity<>(a,HttpStatus.OK);
            }
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        
    }

    @PutMapping("/api/patients/{id}/appointments/{id_appointment}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity <Appointment> updateAppointment(@PathVariable Long id, @RequestBody Appointment appointment,  @PathVariable Long id_appointment){
        if(patientManager.exists(id)){
            if(patientManager.appointmentExists(id, id_appointment)){
                patientManager.editAppointment(id, appointment, id_appointment);
                return new ResponseEntity<>(appointment, HttpStatus.OK);
            }
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    
    }

    @DeleteMapping("/api/patients/{id}/appointments/{id_appointment}")
    public ResponseEntity<Appointment> deleteAppointment(@PathVariable Long id, @PathVariable Long id_appointment) {

        if (patientManager.exists(id)) {
            if(patientManager.appointmentExists(id, id_appointment)){
                patientManager.deleteAppointment(id, id_appointment);
                return new ResponseEntity<>(HttpStatus.OK);
            }
        } 
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping("/api/patients/{id}/doctors/")
    public ResponseEntity<HealthPersonnel> addDoc(@PathVariable Long id, @RequestBody HealthPersonnel h){
        var temp = patientManager.addDoc(id,h);
        return new ResponseEntity<>(temp,HttpStatus.OK);
    }
    @GetMapping("/api/patients/{id}/doctors/")
    public ResponseEntity<HealthPersonnel> getDoc(@PathVariable Long id){
        var temp = patientManager.returnDoc(id);
        if(temp != null)
        return new ResponseEntity<>(temp,HttpStatus.OK);
        else
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    @PutMapping("/api/patients/{id}/doctors/")
    public ResponseEntity<HealthPersonnel>putDoc(@PathVariable Long id, @RequestBody HealthPersonnel h){
        patientManager.addDoc(id,h);
        return new ResponseEntity<>(HttpStatus.OK);
    }


    //UPDATE ONLY SPECIFIED FIELDS
    @PatchMapping("/api/patients/{id}/appointments/{id_appointments}")
    public ResponseEntity<Appointment> patchAppointment(@RequestBody Appointment appointment, @PathVariable Long id, @PathVariable Long id_appointments) {

        if (patientManager.appointmentExists(id, id_appointments)) {
            String temp = String.valueOf(appointment.getDay());
            if (temp!= null){
                patientManager.updateDay(appointment.getDay(), id, id_appointments);}
            
            temp=String.valueOf(appointment.getHour());
            if (temp!= null){
                patientManager.updateHour(appointment.getHour(), id, id_appointments);}

            temp=String.valueOf(appointment.getMonth());
            if (temp!= null){
                patientManager.updateMonth(appointment.getMonth(), id, id_appointments);}

            temp=String.valueOf(appointment.getYear());
            if (temp!= null){
                patientManager.updateYear(appointment.getYear(), id, id_appointments);}
            
            Appointment temp1 = new Appointment(appointment.getHour(), appointment.getDay(), appointment.getMonth(), appointment.getYear());
            return new ResponseEntity<>(temp1, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        
    }
}
