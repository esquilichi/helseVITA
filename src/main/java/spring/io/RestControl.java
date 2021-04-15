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


	//ADD USER FOR PATIENT AND HEALTH PERSONNEL
	@PostMapping("/api")
	@ResponseStatus(HttpStatus.CREATED)
	public User newUser(@RequestBody User user) {
		return manager.addUser(user);
	}

	//FIND USER FOR PATIENT AND HEALTH SERVICE

	@PutMapping("/api/{id}")
	public ResponseEntity<Patient> updatePatient(@PathVariable Long id, @RequestBody Patient patient) {

		if (manager.exists(id)) {
			manager.editUser(id, patient);
			return new ResponseEntity<>(patient, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	//DELETE USER

	@DeleteMapping("/api/{id}")
	public ResponseEntity<User> deletePatient(@PathVariable Long id) {

		if (manager.exists(id)) {
			manager.deleteUser(id);

			return new ResponseEntity<>(HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}


	//GET ONE SPECIFIED USER

	@GetMapping("/api/{id}")
	public ResponseEntity<User> getUser(@PathVariable Long id) {
		if (manager.exists(id)) {
			User userTemp = manager.returnUser(id);
			return new ResponseEntity<>(userTemp, HttpStatus.OK);
		} else
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}


	//GET ALL USERS

	@GetMapping("/api")
	@ResponseStatus(HttpStatus.OK)
	public Collection<User> advertisements() {
		return manager.returnAll();
	}


	//UPDATE ONLY SPECIFIED FIELDS
	@PatchMapping("/api/{id}")
	public ResponseEntity<User> patch(@RequestBody User user, @PathVariable Long id) {
		if (manager.exists(id)) {
			if (user.getUsername() != null)
				manager.updateUser(user.getUsername(), id);
			if (user.getPassword() != null)
				manager.updatePassword(user.getPassword(), id);
			if (user.getEmail() != null)
				manager.updateEmail(user.getEmail(), id);
			if (user.getdni() != null)
				manager.updateDNI(user.getdni(), id);

			User userTemp = manager.returnUser(id);

			return new ResponseEntity<>(userTemp, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}


}
