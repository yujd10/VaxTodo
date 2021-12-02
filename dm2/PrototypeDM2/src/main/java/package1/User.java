package package1;

import java.util.UUID;

public class User {
	private String id;
	private String firstName;
	private String lastName;
	private String username;
	private String password;

	private String birthday;
	private String telephone;
	private String address;
	private String postalCode;
	private String city;
	private UserRole role;

	public User() {
	}

	public User(UserRole role, String password, String username, String firstName, String lastName, String birthday, String telephone, String address, String postalCode, String city) {
		this.password = password;
		this.username = username;
		this.firstName = firstName;
		this.lastName = lastName;
		this.birthday = birthday;
		this.telephone = telephone;
		this.address = address;
		this.postalCode = postalCode;
		this.city = city;
		this.role = role;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
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

	public String getBirthday() {
		return birthday;
	}

	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPostalCode() {
		return postalCode;
	}

	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public UserRole getRole() {
		return role;
	}

	public void setRole(UserRole role) {
		this.role = role;
	}
}