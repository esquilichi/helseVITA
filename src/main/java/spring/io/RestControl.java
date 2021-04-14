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
public class RestControl {

	@Autowired
	UserService manager;
	@Autowired
	PatientService patientManager;
	@Autowired
	HealthPersonnelService healthPersonnelManager;

	//ADD USER FOR PATIENT AND HEALTH PERSONNEL
	@PostMapping("/api")
	@ResponseStatus(HttpStatus.CREATED)
	public Patient newPatient(@RequestBody Patient patient) {
		return patientManager.addPatient(patient);
	}
	@PostMapping("/api")
	@ResponseStatus(HttpStatus.CREATED)
	public HealthPersonnel newHealthPersonnel(@RequestBody HealthPersonnel healthPersonnel) {
		return healthPersonnelManager.addHealthPersonnel(healthPersonnel);
	}

	////////////////////////////////////////////////////////////////////////////////////
	//FIND USER FOR PATIENT AND HEALTH SERVICE

	@PutMapping("/api/{id}")
	public ResponseEntity<Patient> updatePatient(@PathVariable Long id, @RequestBody Patient patient) {

		if (patientManager.exists(id)) {
			patientManager.editPatient(id, healthPersonnel, patient);
			return new ResponseEntity<>(patient, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@PutMapping("/api/{id}")
	public ResponseEntity<HealthPersonnel> updateHealthPersonnel(@PathVariable Long id, @RequestBody HealthPersonnel healthPersonnel) {

		if (healthPersonnelManager.exists(id)) {
			healthPersonnelManager.editHealthPersonnel(id, healthPersonnel, FALTA LISTA PACIENTES);
			return new ResponseEntity<>(healthPersonnel, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	////////////////////////////////////////////////////////////////////////////////////
	//DELETE USER

	@DeleteMapping("/api/{id}")
	public ResponseEntity<Patient> deletePatient(@PathVariable Long id) {

		if (patientManager.exists(id)) {
			patientManager.deletePatient(id);

			return new ResponseEntity<>(HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@DeleteMapping("/api/{id}")
	public ResponseEntity<HealthPersonnel> deleteHealthPatient(@PathVariable Long id) {

		if (healthPersonnelManager.exists(id)) {
			healthPersonnelManager.deleteHealthPersonnel(id);

			return new ResponseEntity<>(HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	////////////////////////////////////////////////////////////////////////////////////
	//GET ONE SPECIFIED USER

	@GetMapping("/api/{id}")
	public ResponseEntity<Patient> getPatient(@PathVariable Long id) {
		if (patientManager.exists(id)) {
			Patient patientTemp = patientManager.returnPatient(id);
			return new ResponseEntity<>(patientTemp, HttpStatus.OK);
		} else
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}

	@GetMapping("/api/{id}")
	public ResponseEntity<HealthPersonnel> getHealthPersonnel(@PathVariable Long id) {
		if (healthPersonnelManager.exists(id)) {
			HealthPersonnel healthPersonnelTemp = healthPersonnelManager.returnHealthPersonnel(id);
			return new ResponseEntity<>(healthPersonnelTemp, HttpStatus.OK);
		} else
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}

	////////////////////////////////////////////////////////////////////////////////////
	//GET ALL USERS

	@GetMapping("/api")
	@ResponseStatus(HttpStatus.OK)
	public Collection<Patient> advertisements() {
		return patientManager.returnAll();
	}

	@GetMapping("/api")
	@ResponseStatus(HttpStatus.OK)
	public Collection<HealthPersonnel> advertisements() {
		return healthPersonnelManager.returnAll();
	}


	////////////////////////////////////////////////////////////////////////////////////
	//UPDATE ONLY SPECIFIED FIELDS
	@PatchMapping("/api/{id}")
	public ResponseEntity<Patient> patch(@RequestBody Patient patient, @PathVariable Long id) {
		if (patientManager.exists(id)) {
			if (patient.getUsername() != null)
				patientManager.updateUsername(patient.getUsername(), id);
			if (patient.getPassword() != null)
				patientManager.updatePassword(patient.getPassword(), id);
			if (patient.getEmail() != null)
				patientManager.updateEmail(patient.getEmail(), id);
			if (patient.getdni() != null)
				patientManager.updateDNI(patient.getdni(), id);

			Patient patientTemp = patientManager.returnPatient(id);

			return new ResponseEntity<>(patientTemp, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@PatchMapping("/api/{id}")
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

			HealthPersonnel healthPersonnelTemp = healthPersonnelManager.returnHealthPersonnel(id);

			return new ResponseEntity<>(healthPersonnelTemp, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

}
