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
public class HealthPersonnelRestControl {


    @Autowired
    private HealthPersonnelRepository repository;

    @PostConstruct
    public void init(){
        repository.save(new HealthPersonnel());
    }

    @PostMapping("/api/healthPersonnel")
    @ResponseStatus(HttpStatus.CREATED)
    public HealthPersonnel newHealthPersonnel(@RequestBody HealthPersonnel healthPersonnel) {
        return repository.save(healthPersonnel);
    }

    //FIND USER


    @PutMapping("/api/healthPersonnel/{id}")
    public ResponseEntity<HealthPersonnel> updateHealthPersonnel(@PathVariable Integer id, @RequestBody HealthPersonnel healthPersonnel) {
        if (repository.findById(id).isPresent()) {
            repository.editHealthPersonnel(healthPersonnel, id);
            return new ResponseEntity<>(healthPersonnel, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    //DELETE USER

    @DeleteMapping("/api/healthPersonnel/{id}")
    public ResponseEntity<HealthPersonnel> deleteHealthPatient(@PathVariable Integer id) {

        if (repository.findById(id).isPresent()) {
            repository.delete(repository.findById(id).get());

            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    //GET ONE SPECIFIED USER

    @GetMapping("/api/healthPersonnel/{id}")
    public ResponseEntity<HealthPersonnel> getHealthPersonnel(@PathVariable Integer id) {
        Optional<HealthPersonnel> op = repository.findById(id);
        if (op.isPresent()) {
            HealthPersonnel healthPersonnel = op.get();
            return new ResponseEntity<>(healthPersonnel, HttpStatus.OK);
        }  
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }


    //GET ALL USERS

    @GetMapping("/api/healthPersonnel")
    @ResponseStatus(HttpStatus.OK)
    public Collection<HealthPersonnel> advertisements() {
        return repository.findAll();
    }


    //UPDATE ONLY SPECIFIED FIELDS

    @PatchMapping("/api/healthPersonnel/{id}")
    public ResponseEntity<HealthPersonnel> patch(@RequestBody HealthPersonnel healthPersonnel, @PathVariable Integer id) {
        if (repository.findById(id).isPresent()) {
            if (healthPersonnel.getUsername() != null)
                repository.updateUsername(healthPersonnel.getUsername(), id);
            if (healthPersonnel.getPassword() != null)
                repository.updatePassword(healthPersonnel.getPassword(), id);
            if (healthPersonnel.getEmail() != null)
                repository.updateEmail(healthPersonnel.getEmail(), id);
            if (healthPersonnel.getdni() != null)
                repository.updateDni(healthPersonnel.getdni(), id);
            if (healthPersonnel.getRole() != null)
                repository.updateRole(healthPersonnel.getRole(), id);

            Optional <HealthPersonnel> op = repository.findById(id);

            if(op.isPresent()){
                HealthPersonnel healthPersonnelTemp = op;
                return new ResponseEntity<>(healthPersonnelTemp, HttpStatus.OK);

            }
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}
