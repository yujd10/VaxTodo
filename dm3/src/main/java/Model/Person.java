package Model;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.ConcurrentModificationException;
import java.util.List;
import java.util.regex.Pattern;

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

    @Override
    public String toString() {
        return  "id='" + id + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", emailAddress='" + emailAddress + '\'' +
                ", birthDate='" + birthDate + '\'' +
                ", isVolunteer=" + isVolunteer +
                ", address=" + address +
                '}';
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

    public void createPerson(String[] personInfo, boolean isVolunteer, String address){
        List<Person> currentPerson = readData();
        Person person = new Person(personInfo[0], personInfo[1], personInfo[2], personInfo[3],personInfo[4],personInfo[5],isVolunteer);
        String[] address_Split = address.split(";");
        person.address = new Address(address_Split[0],address_Split[1],address_Split[2],address_Split[3],address_Split[4]);
        currentPerson.add(person);
        save(currentPerson);
        System.out.println((person.isVolunteer ? "Volunteer ":"Visitor ")+ person.firstName+" "+person.lastName + " added");
    }
    public String verifyInfo(String info, int index){
        String result = "";
            switch (index - 1) {
                case 0:
                    if (info.length() != 12) {
                        result += "Numéro de compte doit être 12 caractères\n";
                    }
                    break;
                case 1:
                    if (info.length() > 50) {
                        result += "Le prénom ne doit pas dépasser 50 caractères\n";
                    }
                    break;
                case 2:
                    if (info.length() > 50) {
                        result += "Le nom de famille ne doit pas dépasser 50 caractères\n";
                    }
                    break;
                case 3:
                    String[] temp = info.split("-");
                    if (!info.contains("-") || temp[0].length() != 4 || temp[1].length() != 2 || temp[2].length() != 2) {
                        result += "Le format de la date de naissance doit être YYYY-MM-DD\n";
                    }
                    break;
                case 5:
                    if (!Pattern.matches("\\d{10}", info)) {
                        result += "Le numéro de téléphone doit être 10 chiffres\n";
                    }
                    break;
                case 6:
                    String[] split = info.split(";");
                    if ((split[0] + split[1]).length() > 100) {
                        result += "L'adresse ne doit pas dépasser 100 caractères\n";
                    }
                    if (split[2].length() > 50) {
                        result += "Le nom de la ville ne peut pas dépasser 50 caractères\n";
                    }
                    if (split[4].length() != 6) {
                        result += "Le code postal doit avoir 6 caractères\n";
                    }
                    break;
            }
        return result;
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
            personDetails.put("street number",p.address.getStreetNumber());
            personDetails.put("street name",p.address.getStreetName());
            personDetails.put("city",p.address.getCity());
            personDetails.put("province",p.address.getProvince());
            personDetails.put("postal code",p.address.getPostalCode());
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
        person.address = new Address();
        //Get Person object within list
        person.id = ((String) person1.get("account number"));
        person.firstName = ((String) person1.get("first name"));
        person.lastName = ((String) person1.get("last name"));
        person.phoneNumber = ((String) person1.get("phone number"));
        person.emailAddress = ((String) person1.get("email"));
        person.birthDate = ((String) person1.get("birthday"));
        person.isVolunteer = ((boolean) person1.get("isVolunteer"));
        person.address.setStreetNumber((String)person1.get("street number"));
        person.address.setStreetName((String) person1.get("street name"));
        person.address.setCity((String) person1.get("city"));
        person.address.setProvince((String) person1.get("province"));
        person.address.setPostalCode((String) person1.get("postal code"));
        return person;
    }

    public void printPersonList(boolean isVolunteer) {
        List<Person> personList = readData();
        for(Person p : personList){
            if(p.isVolunteer == isVolunteer){
                System.out.println(p.toString());
            }
        }
    }
}
