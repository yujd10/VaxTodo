package package1;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class VisitorRespiratory {

	public static void addAccount(Visitor user) {
		List<Visitor> currentUsers = read();
		currentUsers.add(user);
		save(currentUsers);
		System.out.println("Visitor " + user.firstName+" "+user.lastName + " added");
	}

	private static void save(List<Visitor> currentUsers) {
		JSONArray visitorList = new JSONArray();
		for (Visitor u : currentUsers) {
			JSONObject visitorDetails = new JSONObject();
			visitorDetails.put("account number", u.accountNumber);
			visitorDetails.put("last name", u.lastName);
			visitorDetails.put("first name", u.firstName);
			visitorDetails.put("birthday", u.birthday);
			visitorDetails.put("email", u.email);
			visitorDetails.put("telephone number", u.telephone);
			visitorList.add(visitorDetails);
		}

		//Write JSON file
		try (FileWriter file = new FileWriter("visitors.json")) {
			//We can write any JSONArray or JSONObject instance to the file
			file.write(visitorList.toJSONString());
			file.flush();

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public static List<Visitor> read() {
		//JSON parser object to parse read file
		JSONParser jsonParser = new JSONParser();
		List<Visitor> results = new ArrayList<>();

		try (FileReader reader = new FileReader("visitors.json")) {
			//Read JSON file
			Object obj = jsonParser.parse(reader);

			JSONArray visitorList = (JSONArray) obj;

			visitorList.forEach(visitor -> {
				results.add(parseVisitorObject((JSONObject) visitor));
			});

		} catch (IOException | ParseException e) {
			e.printStackTrace();
		}

		return results;
	}

	private static Visitor parseVisitorObject(JSONObject visitor) {
		Visitor user = new Visitor();
		user.accountNumber = ((String) visitor.get("account number"));
		user.lastName = ((String) visitor.get("last name"));
		user.firstName = ((String) visitor.get("first name"));
		user.birthday = ((String) visitor.get("birthday"));
		user.email = ((String) visitor.get("email"));
		user.telephone = ((String) visitor.get("telephone number"));
		return user;
	}

	public static void modify(String oldinfo, String newInfo, String type) {
		List<Visitor> currentUsers = read();
		for (Visitor v : currentUsers) {
			switch(type) {
				case "1":
					if (v.accountNumber == oldinfo) {
						v.accountNumber = newInfo;
						System.out.println(oldinfo + " modified to " + newInfo+"\n");
					}
					break;
				case "2":
					if (v.lastName.equals(oldinfo)) {
						v.lastName = newInfo;
						System.out.println(oldinfo + " modified to " + newInfo+"\n");
					}
					break;
				case "3":
					if (v.firstName.equals((oldinfo))) {
						v.firstName = newInfo;
						System.out.println(oldinfo + " modified to " + newInfo+"\n");
					}
					break;
				case "4":
					if (v.birthday.equals(oldinfo)) {
						v.birthday = newInfo;
						System.out.println(oldinfo + " modified to " + newInfo+"\n");
					}
					break;
				case "5":
					if (v.email.equals(oldinfo)) {
						v.email = newInfo;
						System.out.println(oldinfo + " modified to " + newInfo+"\n");
					}
					break;
				case "6":
					if(v.telephone.equals((oldinfo))){
						v.telephone = newInfo;
						System.out.println(oldinfo + " modified to " + newInfo+"\n");
					}
					break;
			}
		}
		save(currentUsers);
	}

	public static void delete(String accountNumber) {
		List<Visitor> currentUsers = read();
		List<Visitor> newList = new ArrayList<>();
		for (Visitor v : currentUsers) {
			if (v.accountNumber.equals(accountNumber)) {
				System.out.println("Visitor " + v.firstName+ " "+v.lastName + " removed\n");
			} else {
				newList.add(v);
			}
		}
		save(newList);
	}
	public static Visitor search(String info) {
		List<Visitor> currentUsers = read();
		Visitor found = null;
		String[] split = info.split(";");
		for (Visitor v : currentUsers) {
			if (v.email.equals(info)) {
				found = v;
			}
			else if (v.accountNumber.equals(info)) {
				found = v;
			}
			else if(split[0].equals(v.lastName) && split[1].equals(v.firstName)){
				found = v;
			}
		}

		if (found == null) {
			System.out.println("Visitor not found");
		} else {
			System.out.println("name " + found.lastName+" "+found.firstName+"\n");
		}
		return found;
	}
}
