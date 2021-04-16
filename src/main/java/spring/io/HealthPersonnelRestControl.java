package spring.io;
import java.util.Collection;
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
    HealthPersonnelService healthPersonnelManager;


    @PostMapping("/api/healthPersonnel")
    @ResponseStatus(HttpStatus.CREATED)
    public HealthPersonnel newHealthPersonnel(@RequestBody HealthPersonnel healthPersonnel) {
        return healthPersonnelManager.addHealthPersonnel(healthPersonnel);
    }

    //FIND USER


    @PutMapping("/api/healthPersonnel/{id}")
    public ResponseEntity<HealthPersonnel> updateHealthPersonnel(@PathVariable Long id, @RequestBody HealthPersonnel healthPersonnel) {
        if (healthPersonnelManager.exists(id)) {
            healthPersonnelManager.editHealthPersonnel(id, healthPersonnel);
            return new ResponseEntity<>(healthPersonnel, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    //DELETE USER

    @DeleteMapping("/api/healthPersonnel/{id}")
    public ResponseEntity<HealthPersonnel> deleteHealthPatient(@PathVariable Long id) {

        if (healthPersonnelManager.exists(id)) {
            healthPersonnelManager.deleteHealthPersonnel(id);

            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    //GET ONE SPECIFIED USER

    @GetMapping("/api/healthPersonnel/{id}")
    public ResponseEntity<HealthPersonnel> getHealthPersonnel(@PathVariable Long id) {
        if (healthPersonnelManager.exists(id)) {
            HealthPersonnel healthPersonnelTemp = healthPersonnelManager.returnHealthPersonnel(id);
            return new ResponseEntity<>(healthPersonnelTemp, HttpStatus.OK);
        } else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }


    //GET ALL USERS

    @GetMapping("/api/healthPersonnel")
    @ResponseStatus(HttpStatus.OK)
    public Collection<HealthPersonnel> advertisements() {
        return healthPersonnelManager.returnAll();
    }


    //UPDATE ONLY SPECIFIED FIELDS

    @PatchMapping("/api/healthPersonnel/{id}")
    public ResponseEntity<HealthPersonnel> patch(@RequestBody HealthPersonnel healthPersonnel, @PathVariable Long id) {
        if (healthPersonnelManager.exists(id)) {
            if (healthPersonnel.getUsername() != null)
                healthPersonnelManager.updateUsername(healthPersonnel.getUsername(), id);
            if (healthPersonnel.getPassword() != null)
                healthPersonnelManager.updatePassword(healthPersonnel.getPassword(), id);
            if (healthPersonnel.getEmail() != null)
                healthPersonnelManager.updateEmail(healthPersonnel.getEmail(), id);
            if (healthPersonnel.getdni() != null)
                healthPersonnelManager.updateDNI(healthPersonnel.getdni(), id);
            if (healthPersonnel.getRole() != null)
                healthPersonnelManager.updateRole(healthPersonnel.getRole(), id);

            HealthPersonnel healthPersonnelTemp = healthPersonnelManager.returnHealthPersonnel(id);

            return new ResponseEntity<>(healthPersonnelTemp, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}
