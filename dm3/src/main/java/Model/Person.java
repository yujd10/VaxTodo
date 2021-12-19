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
    public Person(String id, String lastName, String firstName, String birthDate, String emailAddress, String phoneNumber, boolean isVolunteer){
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

    /**
     * Cette fonction permet de retrouver une personne qui soit un bénévole ou une visiteur dans notre base de donnéee
     * par ses informations personnelles.
     * Les informations peuvent être son nom au complet, son adresse couriel, son numéro de compte ou sa date d'anniversaire.
     * @param info les informations personnelles de la personne.
     * @return retourne l'instance de la personne trouvé sinon null.
     */
    public Person search(String info){
        List<Person> currentPerson = readData();
        Person found = null;
        String[] split = info.split(":");
        for (Person p : currentPerson) {
            if (split.length == 2){
                if(split[0].equals(p.firstName) && split[1].equals(p.lastName)){
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
                else if(p.getBirthDate().equals(info)){
                    found = p;
                }
            }
        }
        if (found == null) {
            System.out.println("Visitor not found");
        } else {
//            System.out.println("name " + found.lastName+" "+found.firstName+"\n");
        }
        return found;
    }

    /**
     * Cette fonction permet de supprimer une personne qui soit un bénévole ou une visiteur dans notre base de donnée.
     * @param id le numéro de compte de la personne qu'on désire de supprimer.
     * @throws ConcurrentModificationException
     */
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

    /**
     * Cette fonction permet de modifier les données personnelles d'une personne qui soit un bénévole ou une visiteur dans
     * notre base de donnée.
     * @param person L'instance de la personne ciblée.
     * @param choice Une choix entre les options fournis: numéro de compte, prénom, nom de famille, date de naissance, addresse courriel,
     *               numéro de téléphone.
     * @param info
     */
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

    /**
     * Cette fonction permet d'ajouter une personne avec ses informations personnelles détaillées dans notre base de donnée.
     * @param personInfo Liste des informations personnelles.
     * @param isVolunteer Si la personne est un bénévole ou une visiteur.
     * @param address l'adresse de la personne
     */
    public void createPerson(String[] personInfo, boolean isVolunteer, String address){
        List<Person> currentPerson = readData();
        Person person = new Person(personInfo[0], personInfo[1], personInfo[2], personInfo[3],personInfo[4],personInfo[5],isVolunteer);
        String[] address_Split = address.split(";");
        person.address = new Address(address_Split[0],address_Split[1],address_Split[2],address_Split[3],address_Split[4]);
        currentPerson.add(person);
        save(currentPerson);
        System.out.println((person.isVolunteer ? "Volunteer ":"Visitor ")+ person.firstName+" "+person.lastName + " added");
    }

    /**
     * Cette fonction permet de vérifier les données entré par l'utilisateur lors de l'ajout d'une personne dans la base de donnée
     * @param info l'information à vérifier
     * @param index l'option correspondant au type d'information.
     * @return retourne un String pour les informaitons qui ne sont pas conformes à la contrainte.
     */
    public String verifyInfo(String info, int index){
        String result = "";
            switch (index) {
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

    /**
     * Cette fonction permet de d'ajouter les informations d'une instance dans un fichier JSON.
     * @param currentPerson L'instance de la personne dans une liste de personne.
     */
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
/**
 * Cette fonction permet d'enregistrer les données dans le fichier JSON.
 */
        //Write JSON file
        try (FileWriter file = new FileWriter("person.json")){
            file.write((personList.toJSONString()));
            file.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Cette fonction permet de sortir les informations d'un fichier JSON en une instance Java.
     * @return retourner une liste de personne.
     */
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

    /**
     * Cette fonction permet de transformer les données de JSON en attribut d'une instance.
     * @param person1 une instance de personne dans le fichier JSON.
     * @return retourne une instance de Persone.
     */
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

    /**
     * Cette fonction permet de voir tous les personnes dans la base de donnée.
     * @param isVolunteer Choisir la liste des visiteurs ou la liste des bénévoles.
     */
    public void printPersonList(boolean isVolunteer) {
        List<Person> personList = readData();
        for(Person p : personList){
            if(p.isVolunteer == isVolunteer){
                System.out.println(p.toString());
            }
        }
    }


}
