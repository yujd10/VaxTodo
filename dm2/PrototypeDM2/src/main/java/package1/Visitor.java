package package1;

import java.util.ArrayList;
import java.util.List;

public class Visitor {
	private String accountNumber;
	private String lastName;
	private String firstName;
	private String birthday;
	private String email;
	private String telephone;
	private String address;
	private String postalCode;
	private String city;

	private Survey survey;

	public Visitor() {
	}

	public Visitor(String accountNumber, String lastName, String firstName, String birthday, String email, String telephone, String address, String postalCode, String city) {
		this.accountNumber = accountNumber;
		this.lastName = lastName;
		this.firstName = firstName;
		this.birthday = birthday;
		this.email = email;
		this.telephone = telephone;
		this.address = address;
		this.postalCode = postalCode;
		this.city = city;
	}

//	public Visitor(String accountNumber, String lastName, String firstName, String birthday, String email, String telephone) {
//		this.accountNumber = accountNumber;
//		this.lastName = lastName;
//		this.firstName = firstName;
//		this.birthday = birthday;
//		this.email = email;
//		this.telephone = telephone;
//	}

	public List<Appointment> getAppointment(){
		List<Appointment> appointmentList = AppointmentRespiratory.read();
		List<Appointment> result = new ArrayList<>();
		for (Appointment appointment : appointmentList) {
			if (appointment.firstName.equals(this.firstName) && appointment.lastName.equals(this.lastName)) {
				result.add(appointment);
			}
		}
		return result;
	}

	public String getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getBirthday() {
		return birthday;
	}

	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
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

	public Survey getSurvey() {
		return survey;
	}

	public void setSurvey(Survey survey) {
		this.survey = survey;
	}
}
