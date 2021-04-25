package spring.io;

import javax.persistence.Column;

public class User {
	@Column(unique = true, nullable = false)
	private String username;
	@Column(unique = true, nullable = false)
	private String password;
	@Column(unique = true, nullable = false)
	private String email;
	@Column(unique = true, nullable = false)
	private String dni;

	public User() {
	}

	public User(String username, String password, String email, String dni, Integer id) {
		this.username = username;
		this.password = password;
		this.email = email;
		this.dni = dni;
	}

	public User(String username, String password, String email, String dni) {
	}

	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
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