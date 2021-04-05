package spring.io;

import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.stereotype.Repository;

@Repository
public class UserRepository {
	private Map<Long, User> map = new ConcurrentHashMap<Long, User>();

	UserRepository() {
	}

	public User addUser(User user, Long id) {
		user.setId(id);
		this.map.put(id, user);
		return user;
	}

	public boolean exists(Long id) {
		return this.map.get(id) != null;
	}

	public void editUser(Long id, User User) {
		User.setId(id);
		this.map.put(id, User);
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
				//System.out.println(entry.getValue().getUsername());
				return entry.getValue();
			}
		}
		return null;
	}

	public void updateUser(String User, Long id) {
		User UserTemp = map.get(id);
		UserTemp.setUsername(User);
		map.put(id, UserTemp);
	}

	public void updatePassword(String password, Long id) {
		User UserTemp = map.get(id);
		UserTemp.setPassword(password);
		map.put(id, UserTemp);
	}

	public void updateEmail(String email, Long id) {
		User UserTemp = map.get(id);
		UserTemp.setEmail(email);
		map.put(id, UserTemp);
	}

	public void updateDNI(String dni, Long id) {
		User UserTemp = map.get(id);
		UserTemp.setdni(dni);
		map.put(id, UserTemp);
	}
}