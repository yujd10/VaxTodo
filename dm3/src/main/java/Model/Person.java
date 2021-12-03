package Model;

import com.sun.xml.internal.bind.v2.TODO;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.ConcurrentModificationException;
import java.util.List;

public class Person {
    private String id;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String emailAddress;
    private String birthDate;
    private boolean isVolunteer;
    private Address address;

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

    public Person search(String info){
        List<Person> currentPerson = readData();
        Person found = null;
        String[] split = info.split(":");
        for (Person p : currentPerson) {
            if (split.length == 2){
                if(split[0].equals(p.lastName) && split[1].equals(p.firstName)){
                    found = p;
                }
            }
            else{
                if (p.emailAddress.equals(info)) {
                    found = p;
                }
                else if (p.id.equals(info)) {
                    found = p;
                }
            }
        }
        if (found == null) {
            System.out.println("Visitor not found");
        } else {
            System.out.println("name " + found.lastName+" "+found.firstName+"\n");
        }
        return found;
    }

    public void delete(String id) throws ConcurrentModificationException {
        List<Person> currentPerson = readData();
        boolean found = false;
        for (Person p : currentPerson) {
            if (p.id.equals(id)) {
                found = true;
                currentPerson.remove(p);
                System.out.println((p.isVolunteer ? "Volunteer ":"Visitor ")+ p.firstName+ " "+p.lastName + " removed\n");
                break;
            }
        }
        if(found){
            this.save(currentPerson);
        }else{
            System.out.println("account number not found, please try again");
        }
    }

    public void update(Person person, String choice, String info){
        String old;
        List<Person> currentPerson = readData();
        for(Person p : currentPerson){
            if(p.id.equals(person.id)){
                switch (choice){
                    case "1":
                        old = p.id;
                        p.id = info;
                        System.out.println(old + " modified to "+ info+"\n");
                        break;
                    case "2":
                        old = p.firstName;
                        p.firstName = info;
                        System.out.println(old + " modified to "+ info+"\n");
                        break;
                    case "3":
                        old = p.lastName;
                        p.lastName = info;
                        System.out.println(old + " modified to "+ info+"\n");
                        break;
                    case "4":
                        old = p.birthDate;
                        p.birthDate = info;
                        System.out.println(old + " modified to "+ info+"\n");
                        break;
                    case "5":
                        old = p.emailAddress;
                        p.emailAddress = info;
                        System.out.println(old + " modified to "+ info+"\n");
                        break;
                    case "6":
                        old = p.phoneNumber;
                        p.phoneNumber = info;
                        System.out.println(old + " modified to "+ info+"\n");
                        break;
                }
            }
        }
        this.save(currentPerson);
    }

    public void createPerson(String[] personInfo, boolean isVolunteer){
        List<Person> currentPerson = readData();
        Person person = new Person(personInfo[0], personInfo[1], personInfo[2], personInfo[3],personInfo[4],personInfo[5],isVolunteer);
        currentPerson.add(person);
        //TODO validate person variables.
        save(currentPerson);
        System.out.println((person.isVolunteer ? "Volunteer ":"Visitor ")+ person.firstName+" "+person.lastName + " added");
    }

    private void save(List<Person> currentPerson) {
        JSONArray personList = new JSONArray();
        for(Person p : currentPerson){
            JSONObject personDetails = new JSONObject();
            personDetails.put("account number", p.getId());
            personDetails.put("first name", p.getFirstName());
            personDetails.put("last name", p.getLastName());
            personDetails.put("phone number", p.getPhoneNumber());
            personDetails.put("email", p.getEmailAddress());
            personDetails.put("birthday", p.getBirthDate());
            personDetails.put("isVolunteer", p.isVolunteer());
            personList.add(personDetails);
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
            if(personList.size() == 0 ){
                return results;
            }

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
