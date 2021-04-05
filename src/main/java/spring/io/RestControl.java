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

@RestController
public class RestControl {

	@Autowired
	UserService manager;

	@PostMapping("/api")
	@ResponseStatus(HttpStatus.CREATED)
	public User newUser(@RequestBody User user) {
		return manager.addUser(user);
	}

	@PutMapping("/api/{id}")
	public ResponseEntity<User> updateUser(@PathVariable Long id, @RequestBody User user) {

		if (manager.exists(id)) {
			manager.editUser(id, user);
			return new ResponseEntity<>(user, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@DeleteMapping("/api/{id}")
	public ResponseEntity<User> deleteUser(@PathVariable Long id) {

		if (manager.exists(id)) {
			manager.deleteUser(id);

			return new ResponseEntity<>(HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@GetMapping("/api/{id}")
	public ResponseEntity<User> getUser(@PathVariable Long id) {
		if (manager.exists(id)) {
			User userTemp = manager.returnUser(id);
			return new ResponseEntity<>(userTemp, HttpStatus.OK);
		} else
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}

	@GetMapping("/api")
	@ResponseStatus(HttpStatus.OK)
	public Collection<User> advertisements() {
		return manager.returnAll();
	}



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
