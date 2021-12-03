package Model;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Person {
    private String id;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String emailAddress;
    private String birthDate;
    private boolean isVolunteer;

    public Person(){

    }
    public Person(String id, String firstName, String lastName, String phoneNumber, String emailAddress, String birthDate, boolean isVolunteer){
        this.id = id;
        this.lastName = lastName;
        this.firstName = firstName;
        this.birthDate = birthDate;
        this.emailAddress = emailAddress;
        this.phoneNumber = phoneNumber;
        this.isVolunteer = isVolunteer;

    }
    public String getId() {
        return id;
    }
    public String getFirstName() {
        return firstName;
    }
    public String getLastName() {
        return lastName;
    }
    public String getPhoneNumber() {
        return phoneNumber;
    }
    public String getEmailAddress() {
        return emailAddress;
    }
    public String getBirthDate() {
        return birthDate;
    }
    public boolean isVolunteer() {
        return isVolunteer;
    }

    public void delete(String id) {
        List<Person> currentPerson = readData();
        List<Person> newList = new ArrayList<>();
        for (Person p : currentPerson) {
            if (p.id.equals(id)) {
                System.out.println((p.isVolunteer ? "Volunteer ":"Visitor ")+ p.firstName+ " "+p.lastName + " removed\n");
            } else {
                newList.add(p);
            }
        }
        this.save(newList);
    }

    public void update(String oldInfo, String newInfo, String type){
        List<Person> currentPerson = readData();
        for(Person p : currentPerson){
            switch(type) {
                case "1":
                    if (p.id.equals(oldInfo)) {
                        p.id = newInfo;
                        System.out.println(oldInfo + " modified to " + newInfo+"\n");
                    }
                    break;
                case "2":
                    if (p.lastName.equals(oldInfo)) {
                        p.lastName = newInfo;
                        System.out.println(oldInfo + " modified to " + newInfo+"\n");
                    }
                    break;
                case "3":
                    if (p.firstName.equals((oldInfo))) {
                        p.firstName = newInfo;
                        System.out.println(oldInfo + " modified to " + newInfo+"\n");
                    }
                    break;
                case "4":
                    if (p.birthDate.equals(oldInfo)) {
                        p.birthDate = newInfo;
                        System.out.println(oldInfo + " modified to " + newInfo+"\n");
                    }
                    break;
                case "5":
                    if (p.emailAddress.equals(oldInfo)) {
                        p.emailAddress = newInfo;
                        System.out.println(oldInfo + " modified to " + newInfo+"\n");
                    }
                    break;
                case "6":
                    if(p.phoneNumber.equals((oldInfo))){
                        p.phoneNumber = newInfo;
                        System.out.println(oldInfo + " modified to " + newInfo+"\n");
                    }
                    break;
            }
        }
        this.save(currentPerson);
    }

    public void createPerson(String[] personInfo, boolean isVolunteer){
        List<Person> currentPerson = readData();
        Person person = new Person(personInfo[0], personInfo[1], personInfo[2], personInfo[3],personInfo[4],personInfo[5],isVolunteer);
        currentPerson.add(person);
        save(currentPerson);
        System.out.println((person.isVolunteer ? "Volunteer ":"Visitor ")+ person.firstName+" "+person.lastName + " added");
    }

    private void save(List<Person> currentPerson) {
        JSONArray personList = new JSONArray();
        for(Person p : currentPerson){
            JSONObject personDetails = new JSONObject();
            personDetails.put("id", p.getId());
            personDetails.put("first name", p.getFirstName());
            personDetails.put("last name", p.getLastName());
            personDetails.put("phone number", p.getPhoneNumber());
            personDetails.put("email", p.getEmailAddress());
            personDetails.put("birthday", p.getBirthDate());
            personDetails.put("isVolunteer", p.isVolunteer());
        }

        //Write JSON file
        try (FileWriter file = new FileWriter("person.json")){
            file.write((personList.toJSONString()));
            file.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<Person> readData() {
        //JSON parser object to parse read file
        JSONParser jsonParser = new JSONParser();
        List<Person> results = new ArrayList<>();

        try (FileReader reader = new FileReader("person.json")) {
            //Read JSON file
            Object obj = jsonParser.parse(reader);

            JSONArray personList = (JSONArray) obj;

            personList.forEach(person -> {
                results.add(parsePersonObject((JSONObject) person));
            });

        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }

        return results;
    }
    private static Person parsePersonObject(JSONObject person1) {
        Person person = new Person();
        //Get Person object within list
        person.id = ((String) person1.get("account number"));
        person.firstName = ((String) person1.get("first name"));
        person.lastName = ((String) person1.get("last name"));
        person.phoneNumber = ((String) person1.get("phone number"));
        person.emailAddress = ((String) person1.get("email"));
        person.birthDate = ((String) person1.get("birthday"));
        person.isVolunteer = ((boolean) person1.get("isVolunteer"));
        return person;
    }
}
