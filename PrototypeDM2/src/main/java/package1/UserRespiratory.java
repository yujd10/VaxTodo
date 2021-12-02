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
		if(!ifUserExist(user.getFirstName(),user.getLastName())){
		List<User> currentUsers = read();
		currentUsers.add(user);
		JSONArray employeeList = new JSONArray();
		for (User u : currentUsers) {
			JSONObject employeeDetails = new JSONObject();
			employeeDetails.put("id", u.getId());
			employeeDetails.put("firstname",u.getFirstName());
			employeeDetails.put("lastName",u.getLastName());
			employeeDetails.put("birthday",u.getBirthday());
			employeeDetails.put("username", u.getUsername());
			employeeDetails.put("password", u.getPassword());
			employeeDetails.put("telephone",u.getTelephone());
			employeeDetails.put("address",u.getAddress());
			employeeDetails.put("postalcode",u.getPostalCode());
			employeeDetails.put("city",u.getCity());
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
		}}
		else {
			System.out.println("already exists ");
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

	/***
	 * @param firstname
	 * @param lastname
	 * @return if this user Exists
	 */
	public static boolean ifUserExist(String firstname, String lastname){
		boolean ifExist = false;
		List<User> users = read();
		for(User user:users){
			if(user.getFirstName().equals(firstname) && user.getLastName().equals(lastname)){
				ifExist = true;
			}
		}
		return ifExist;
	}

	private static User parseEmployeeObject(JSONObject employee) {
		User user = new User();
		//Get employee object within list
		user.setId((String) employee.get("id"));
		user.setFirstName((String) employee.get("firstname"));
		user.setLastName((String) employee.get("lastName"));
		user.setUsername((String) employee.get("username"));
		user.setPassword((String) employee.get("password"));

		user.setBirthday((String) employee.get("birthday"));
		user.setTelephone((String) employee.get("telephone"));
		user.setAddress((String) employee.get("address"));

		user.setPostalCode((String) employee.get("postalcode"));
		user.setCity((String) employee.get("city"));
		user.setRole(Enum.valueOf(UserRole.class, (String) employee.get("role")));
		return user;
	}


}

