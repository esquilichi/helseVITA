package spring.io;

import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.ConcurrentHashMap;
import org.springframework.stereotype.Service;

@Service
public class UserService {

	private Map<Integer, User> map = new ConcurrentHashMap<Integer, User>();
	private Integer lastId = -1;



	public boolean exists(Integer id) {
		return this.map.get(id) != null;
	}

	public void editUser(Integer id, User user) {
		if (this.exists(id)) {
			this.map.put(id, user);
		}
	}

	public void deleteUser(Integer id) {
		this.map.remove(id);
	}

	public User returnUser(Integer id) {
		return this.map.get(id);
	}

	public Collection<User> returnAll() {
		return this.map.values();
	}


	
	public User search(String username) {
		Iterator<Entry<Integer, User>> iterator = map.entrySet().iterator();
		while (iterator.hasNext()) {
			Entry<Integer, User> entry = iterator.next();
			if (entry.getValue().getUsername().equals(username)) {
				//System.out.println(entry.getValue().getUsername());

				return entry.getValue();
			}
		}
		return null;
	}
	
	public User searchUsername(String username) {
		Iterator<Entry<Integer, User>> iterator = map.entrySet().iterator();
		while (iterator.hasNext()) {
			Entry<Integer, User> entry = iterator.next();
			if (entry.getValue().getUsername().equals(username)) {
				return entry.getValue();
			}
		}
		return null;
	}

	public User searchEmail(String email) {
		Iterator<Entry<Integer, User>> iterator = map.entrySet().iterator();
		while (iterator.hasNext()) {
			Entry<Integer, User> entry = iterator.next();
			if (entry.getValue().getEmail().equals(email)) {
				return entry.getValue();
			}
		}
		return null;
	}
	
	public User searchDni(String dni) {
		for(Map.Entry<Integer, User> entry: map.entrySet()){
			if (entry.getValue().getdni().equals(dni)) {
				return entry.getValue();
			}
		}
		return null;
	}
	
	public void updateUser(String user, Integer id) {
		User userTemp = map.get(id);
		userTemp.setUsername(user);
		map.put(id, userTemp);
	}

	public void updatePassword(String password, Integer id) {
		User userTemp = map.get(id);
		userTemp.setPassword(password);
		map.put(id, userTemp);
	}

	public void updateEmail(String correo, Integer id) {
		User userTemp = map.get(id);
		userTemp.setEmail(correo);
		map.put(id, userTemp);
	}

	public void updateDNI(String dni, Integer id) {
		User userTemp = map.get(id);
		userTemp.setdni(dni);
		map.put(id, userTemp);
	}

}
