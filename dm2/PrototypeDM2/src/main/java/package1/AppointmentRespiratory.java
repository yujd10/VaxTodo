package package1;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class AppointmentRespiratory {
	public static void addAppointment(Appointment appointment) {
		List<Appointment> currentAppoinments = read();
		currentAppoinments.add(appointment);
		JSONArray employeeList = new JSONArray();
		for (Appointment u : currentAppoinments) {
			JSONObject employeeDetails = new JSONObject();
			employeeDetails.put("id", u.id);
			employeeDetails.put("last name", u.lastName);
			employeeDetails.put("first name", u.firstName);
			employeeDetails.put("date", u.date);
			employeeDetails.put("hour", u.hour);
			employeeDetails.put("dose", u.dose);
			employeeList.add(employeeDetails);
		}

		//Write JSON file
		try (FileWriter file = new FileWriter("appointments.json")) {
			//We can write any JSONArray or JSONObject instance to the file
			file.write(employeeList.toJSONString());
			file.flush();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static List<Appointment> read() {
		//JSON parser object to parse read file
		JSONParser jsonParser = new JSONParser();
		List<Appointment> results = new ArrayList<>();

		try (FileReader reader = new FileReader("appointments.json")) {
			//Read JSON file
			Object obj = jsonParser.parse(reader);

			JSONArray employeeList = (JSONArray) obj;

			employeeList.forEach(emp -> {
				results.add(parseAppointmentObject((JSONObject) emp));
			});

		} catch (IOException | ParseException e) {
			e.printStackTrace();
		}

		return results;
	}

	private static Appointment parseAppointmentObject(JSONObject employee) {
		Appointment appointment = new Appointment();
		//Get employee object within list
		appointment.id = (String) employee.get("id");
		appointment.lastName = (String) employee.get("last name");
		appointment.firstName = (String) employee.get("first name");
		appointment.date = (String) employee.get("date");
		appointment.hour = (String) employee.get("hour");
		appointment.dose = (String) employee.get("dose");
		return appointment;
	}
}
