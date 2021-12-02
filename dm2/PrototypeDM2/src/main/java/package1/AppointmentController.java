package package1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

public class AppointmentController {

	public static void getTime(String lastName, String firstName) {
		if (LoginController.loggedInUser == null) {
			System.out.println("You are not logged in.");
			return;
		}
		List<Appointment> appointmentList = AppointmentRespiratory.read();
		for (Appointment appointment : appointmentList) {
			if (appointment.lastName.equals(lastName) && appointment.firstName.equals(firstName)) {
				System.out.println(appointment.date+" "+appointment.hour);
				return;
			}
		}

		System.out.println("appointment not found");
	}
}
