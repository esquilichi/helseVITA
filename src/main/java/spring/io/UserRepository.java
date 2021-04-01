package spring.io;

import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.stereotype.Repository;

@Repository
public class UserRepository {
	private Map<Long, Usuario> mapa = new ConcurrentHashMap<Long, Usuario>();

	UserRepository() {
	}

	public Usuario agregarUsuario(Usuario user, Long id) {
		user.setId(id);
		this.mapa.put(id, user);
		return user;
	}

	public boolean existe(Long id) {
		return this.mapa.get(id) != null;
	}

	public void editarUsuario(Long id, Usuario usuario) {
		if (this.mapa.get(id) != null) {
			usuario.setId(id);
			this.mapa.put(id, usuario);
		}
	}

	public void eliminarUsuario(Long id) {
		this.mapa.remove(id);
	}

	public Usuario devolverUsuario(Long id) {
		return this.mapa.get(id);
	}

	public Collection<Usuario> devolverTodos() {
		return this.mapa.values();
	}

	// FUNCIONA <3
	public Usuario buscar(String username) {
		Iterator<Entry<Long, Usuario>> iterator = mapa.entrySet().iterator();
		while (iterator.hasNext()) {
			Entry<Long, Usuario> entry = iterator.next();
			// si tocais el .equals os parto un brazo <3
			if (entry.getValue().getUsername().equals(username)) {
				System.out.println(entry.getValue().getUsername());
				return entry.getValue();
			}
		}
		return null;
	}

	public void actualizarUsuario(String usuario, Long id) {
		Usuario usuarioTemp = mapa.get(id);
		usuarioTemp.setUsername(usuario);
		mapa.put(id, usuarioTemp);
	}

	public void actualizarPassword(String password, Long id) {
		Usuario usuarioTemp = mapa.get(id);
		usuarioTemp.setPassword(password);
		mapa.put(id, usuarioTemp);
	}

	public void actualizarCorreo(String correo, Long id) {
		Usuario usuarioTemp = mapa.get(id);
		usuarioTemp.setCorreo(correo);
		mapa.put(id, usuarioTemp);
	}

	public void actualizarDNI(String dni, Long id) {
		Usuario usuarioTemp = mapa.get(id);
		usuarioTemp.setdni(dni);
		mapa.put(id, usuarioTemp);
	}
}