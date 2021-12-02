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
		System.out.println("Visitor " + user.getFirstName()+" "+user.getLastName()+ " added");
	}

	private static void save(List<Visitor> currentUsers) {
		JSONArray visitorList = new JSONArray();
		for (Visitor u : currentUsers) {
			JSONObject visitorDetails = new JSONObject();
			visitorDetails.put("account number", u.getAccountNumber());
			visitorDetails.put("last name", u.getLastName());
			visitorDetails.put("first name", u.getFirstName());
			visitorDetails.put("birthday", u.getBirthday());
			visitorDetails.put("email", u.getEmail());
			visitorDetails.put("telephone number", u.getTelephone());
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
		user.setAccountNumber(((String) visitor.get("account number")));
		user.setLastName(((String) visitor.get("last name")));
		user.setFirstName(((String) visitor.get("first name")));
		user.setBirthday(((String) visitor.get("birthday")));
		user.setEmail(((String) visitor.get("email")));
		user.setTelephone(((String) visitor.get("telephone number")));
		return user;
	}

	public static void modify(String oldinfo, String newInfo, String type) {
		List<Visitor> currentUsers = read();
		for (Visitor v : currentUsers) {
			switch(type) {
				case "1":
					if (v.getAccountNumber() == oldinfo) {
						v.setAccountNumber(newInfo);
						System.out.println(oldinfo + " modified to " + newInfo+"\n");
					}
					break;
				case "2":
					if (v.getLastName().equals(oldinfo)) {
						v.setLastName(newInfo);
						System.out.println(oldinfo + " modified to " + newInfo+"\n");
					}
					break;
				case "3":
					if (v.getFirstName().equals((oldinfo))) {
						v.setFirstName(newInfo);
						System.out.println(oldinfo + " modified to " + newInfo+"\n");
					}
					break;
				case "4":
					if (v.getBirthday().equals(oldinfo)) {
						v.setBirthday(newInfo);
						System.out.println(oldinfo + " modified to " + newInfo+"\n");
					}
					break;
				case "5":
					if (v.getEmail().equals(oldinfo)) {
						v.setEmail(newInfo) ;
						System.out.println(oldinfo + " modified to " + newInfo+"\n");
					}
					break;
				case "6":
					if(v.getTelephone().equals((oldinfo))){
						v.setTelephone(newInfo);
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
			if (v.getAccountNumber().equals(accountNumber)) {
				System.out.println("Visitor " + v.getFirstName()+ " "+v.getLastName() + " removed\n");
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
			if (v.getEmail().equals(info)) {
				found = v;
			}
			else if (v.getAccountNumber().equals(info)) {
				found = v;
			}
			else if(split[0].equals(v.getLastName()) && split[1].equals(v.getFirstName())){
				found = v;
			}
		}

		if (found == null) {
			System.out.println("Visitor not found");
		} else {
			System.out.println("name " + found.getLastName()+" "+found.getFirstName()+"\n");
		}
		return found;
	}
}
