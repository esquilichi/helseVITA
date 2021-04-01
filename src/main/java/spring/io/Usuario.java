package spring.io;

public class Usuario {
	private String username;
	private String password;
	private String correo;
	private String dni;
	private long id;

	public Usuario() {
	}

	public Usuario(String username, String password, String correo, String dni, Long id) {
		this.username = username;
		this.password = password;
		this.correo = correo;
		this.dni = dni;
		this.id = id;
	}

	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public long getId() {
		return this.id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getCorreo() {
		return this.correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}

	public String getdni() {
		return this.dni;
	}

	public void setdni(String dni) {
		this.dni = dni;
	}

	@Override
	public String toString() {
		return this.username;
	}

}