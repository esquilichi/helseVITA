package spring.io;
import java.util.Collection;
import java.util.Optional;

import javax.annotation.PostConstruct;

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
public class AppointmentRestControl {


    @Autowired
    private AppointmentRepository repository;

    @PostConstruct
    public void init(){
        repository.save(new Appointment());
    }

    @PostMapping("/api/appointment")
    @ResponseStatus(HttpStatus.CREATED)
    public Appointment newAppointment(@RequestBody Appointment appointment) {
        return repository.save(appointment);
    }

    //FIND APPOINTMENT


    @PutMapping("/api/appointment/{id}")
    public ResponseEntity<Appointment> updateAppointment(@PathVariable Integer id, @RequestBody Appointment appointment) {
        if (repository.findById(id).isPresent()) {
            repository.editAppointment(appointment, id);
            return new ResponseEntity<>(appointment, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    //DELETE APPOINTMENT

    @DeleteMapping("/api/Appointment/{id}")
    public ResponseEntity<Appointment> deleteAppointment(@PathVariable Integer id) {

        if (repository.findById(id).isPresent()) {
            repository.delete(repository.findById(id).get());

            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    //GET ONE SPECIFIED APPOINTMENT

    @GetMapping("/api/appointment/{id}")
    public ResponseEntity<Appointment> getAppointment(@PathVariable Integer id) {
        Optional<Appointment> op = repository.findById(id);
        if (op.isPresent()) {
        	Appointment appointment = op.get();
            return new ResponseEntity<>(appointment, HttpStatus.OK);
        }  
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }


    //GET ALL APPOINTMENTS

    @GetMapping("/api/appointment")
    @ResponseStatus(HttpStatus.OK)
    public Collection<Appointment> advertisements() {
        return repository.findAll();
    }


    //UPDATE ONLY SPECIFIED FIELDS

    @PatchMapping("/api/appointment/{id}")
    public ResponseEntity<Appointment> patch(@RequestBody Appointment appointment, @PathVariable Integer id) {
        if (repository.findById(id).isPresent()) {
            if (appointment.getHour() > 0)
                repository.updateHour(appointment.getHour(), id);
            if (appointment.getDay() > 0)
                repository.updateDay(appointment.getDay(), id);
            if (appointment.getMonth() > 0)
                repository.updateMonth(appointment.getMonth(), id);
            if (appointment.getYear() > 0)
                repository.updateYear(appointment.getYear(), id);

            Optional <Appointment> op = repository.findById(id);

            if(op.isPresent()){
            	Appointment appointmentTemp = op.get();
                return new ResponseEntity<>(appointmentTemp, HttpStatus.OK);

            }
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);

    }

}
