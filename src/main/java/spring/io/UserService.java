package spring.io;

import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.ConcurrentHashMap;
import org.springframework.stereotype.Service;

@Service
public class UserService {

	private Map<Long, User> map = new ConcurrentHashMap<Long, User>();
	private Long lastId = (long) -1;

	public User addUser(User user) {
		lastId++;
		user.setId(lastId);
		this.map.put(lastId, user);
		return user;
	}

	public boolean exists(Long id) {
		return this.map.get(id) != null;
	}

	public void editUser(Long id, User user) {
		if (this.exists(id)) {
			this.map.put(id, user);
		}
	}

	public void deleteUser(Long id) {
		this.map.remove(id);
	}

	public User returnUser(Long id) {
		return this.map.get(id);
	}

	public Collection<User> returnAll() {
		return this.map.values();
	}

	public User search(String username) {
		Iterator<Entry<Long, User>> iterator = map.entrySet().iterator();
		while (iterator.hasNext()) {
			Entry<Long, User> entry = iterator.next();
			if (entry.getValue().getUsername().equals(username)) {
				return entry.getValue();
			}
		}
		return null;
	}

	public void updateUser(String user, long id) {
		User userTemp = map.get(id);
		userTemp.setUsername(user);
		map.put(id, userTemp);
	}

	public void updatePassword(String password, Long id) {
		User userTemp = map.get(id);
		userTemp.setPassword(password);
		map.put(id, userTemp);
	}

	public void updateEmail(String correo, Long id) {
		User userTemp = map.get(id);
		userTemp.setEmail(correo);
		map.put(id, userTemp);
	}

	public void updateDNI(String dni, Long id) {
		User userTemp = map.get(id);
		userTemp.setdni(dni);
		map.put(id, userTemp);
	}
}
