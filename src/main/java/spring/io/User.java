package spring.io;

public class User {
	private String username;
	private String password;
	private String email;
	private String dni;
	private long id;

	public User() {
	}

	public User(String username, String password, String email, String dni, Long id) {
		this.username = username;
		this.password = password;
		this.email = email;
		this.dni = dni;
		this.id = id;
	}

	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Long getId() {
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

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
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