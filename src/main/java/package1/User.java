package package1;

import java.util.UUID;

public class User {
	private String id;
	private UserRole role;
	private String password;
	private String username;

	public User() {
	}

	public User(UserRole role, String username, String password) {
		this.id = generateID();
		this.role = role;
		this.username = username;
		this.password = password;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public UserRole getRole() {
		return role;
	}

	public void setRole(UserRole role) {
		this.role = role;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String generateID() {
		return UUID.randomUUID().toString();
	}
}
