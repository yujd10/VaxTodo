package package1;

import java.util.ArrayList;
import java.util.List;

public class Visitor {
	public String accountNumber;
	public String lastName;
	public String firstName;
	public String birthday;
	public String email;
	public String telephone;
	public Survey survey;

	public Visitor() {
	}

	public Visitor(String accountNumber, String lastName, String firstName, String birthday, String email, String telephone) {
		this.accountNumber = accountNumber;
		this.lastName = lastName;
		this.firstName = firstName;
		this.birthday = birthday;
		this.email = email;
		this.telephone = telephone;
	}

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

}
