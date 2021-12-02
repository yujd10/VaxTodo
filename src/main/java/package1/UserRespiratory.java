package package1;


import org.json.simple.parser.JSONParser;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class UserRespiratory {

	public static void addAccount(User user) {
		List<User> currentUsers = read();
		currentUsers.add(user);
		JSONArray employeeList = new JSONArray();
		for (User u : currentUsers) {
			JSONObject employeeDetails = new JSONObject();
			employeeDetails.put("id", u.getId());
			employeeDetails.put("username", u.getUsername());
			employeeDetails.put("password", u.getPassword());
			employeeDetails.put("role", u.getRole().toString());
			employeeList.add(employeeDetails);
		}

		//Write JSON file
		try (FileWriter file = new FileWriter("employees.json")) {
			//We can write any JSONArray or JSONObject instance to the file
			file.write(employeeList.toJSONString());
			file.flush();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static List<User> read() {
		//JSON parser object to parse read file
		JSONParser jsonParser = new JSONParser();
		List<User> results = new ArrayList<>();

		try (FileReader reader = new FileReader("employees.json")) {
			//Read JSON file
			Object obj = jsonParser.parse(reader);

			JSONArray employeeList = (JSONArray) obj;

			employeeList.forEach(emp -> {
				results.add(parseEmployeeObject((JSONObject) emp));
			});

		} catch (IOException | ParseException e) {
			e.printStackTrace();
		}

		return results;
	}

	private static User parseEmployeeObject(JSONObject employee) {
		User user = new User();
		//Get employee object within list
		user.setId((String) employee.get("id"));
		user.setUsername((String) employee.get("username"));
		user.setPassword((String) employee.get("password"));
		user.setRole(Enum.valueOf(UserRole.class, (String) employee.get("role")));
		return user;
	}
}

