package spring.io;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


//This class is in charge of the logic part
@Service
public class UserService {

	@Autowired
	UserRepository repository;
	private Long lastId = (long) -1;

	public User addUser(User user) {
		lastId++;
		return repository.addUser(user, lastId);
	}

	public boolean exists(Long id) {
		return repository.exists(id);
	}

	public void editUser(Long id, User User) {
		if (repository.exists(id)) {
			repository.editUser(id, User);
		}
	}

	public void deleteUser(Long id) {
		repository.deleteUser(id);
	}

	public User returnUser(Long id) {
		return repository.returnUser(id);
	}

	public Collection<User> returnAll() {
		return repository.returnAll();
	}

	public User search(String username) {
		return repository.search(username);
	}

	public void updateUser(String User, long id) {
		repository.updateUser(User, id);
	}

	public void updatePassword(String password, Long id) {
		repository.updatePassword(password, id);
	}

	public void updateEmail(String correo, Long id) {
		repository.updateEmail(correo, id);
	}

	public void updateDNI(String dni, Long id) {
		repository.updateDNI(dni, id);
	}
}
