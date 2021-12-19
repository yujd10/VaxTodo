package Model;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class VaccineProfile {
    private Person person;
    private String date;
    private List<Vaccine> vaccines;
    private String codeQR;

    public VaccineProfile() {
    }

    public VaccineProfile(Person person, String date, List<Vaccine> vaccine) {
        this.person = person;
        this.date = date;
        this.vaccines = vaccine;
        //code QR est généré automatiquement en créant l'Objet de Profile
        this.codeQR = generateQR();
    }

    /**
     * Génerer une code QR pour le Vaccine Profile sous forme de 26 digits
     * @return Le code QR généré
     */
    public String generateQR(){
        String qrNumber = "";
        for(int i = 0; i < 26; i++) {
            qrNumber += Integer.toString((int) Math.floor(Math.random() * 10));
        }
        return qrNumber;
    }

    /**
     * Fonction permet de simuler l'action d'envoyer un profil de Vaccination au visiteur
     * en imprimant les informations du profil
     */
    public void sendProfil(){
        System.out.println("- Name : "+ person.getFirstName() + " " + person.getLastName() + "\n" +
                            "- Date : " + this.date + "\n" +
                            "- Vaccinns received " + "\n" +
                            vaccines.toString() +
                            "- Code QR :" + this.codeQR +"\n"+
                            "is sent to "+person.getEmailAddress() +" ! ");
    }

    /**
     * Fonction permet d'ajouter une deuxième dose à cette profil de vaccination et sauvegarder le profil de vaccination
     * @param vaccine la deuxième dose à ajouter
     */
    public void addVaccine(Vaccine vaccine){
        List<VaccineProfile> vaccineProfiles = read();
        Integer index = findProfile(this.person);
        if(index.equals(null)){
            System.out.println("Profile pas trouvé !");
        }else {
            this.vaccines.add(vaccine);
            vaccineProfiles.set(index,this );
            System.out.println("La deuxième dose de "+ vaccine.getName() + " pour "+person.getFirstName()+" "+person.getLastName() +" est ajouter dans le profile !");
        }
        saveData(vaccineProfiles);
    }

    /**
     * Fonction permet de chercher une
     * @param person
     * @return
     */
    public static Integer findProfile(Person person){
        List<VaccineProfile> currentProfils = read();
        VaccineProfile profile = null;
        Integer index = null;
        for(VaccineProfile vaccineProfile:currentProfils){
            if(vaccineProfile.getPerson().getFirstName().equals(person.getFirstName())
                    &&vaccineProfile.getPerson().getLastName().equals(person.getLastName())){
                profile=vaccineProfile;
                index = currentProfils.indexOf(vaccineProfile);
            }
        }
        if(index.equals(null)){
            System.out.println("profile not found");
        }
        return index;
    }

    public static void addProfile(VaccineProfile profile){
        List<VaccineProfile> currentProfils = read();
        currentProfils.add(profile);
        saveData(currentProfils);
    }

    public static List<VaccineProfile> read(){
        List<VaccineProfile> results = new ArrayList<>();
        try {
            Reader reader = Files.newBufferedReader(Paths.get("survey.json"));
            results = new Gson().fromJson(reader,new TypeToken<List<VaccineProfile>>() {}.getType());
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return results;
    }

    public static void saveData(List<VaccineProfile> currentlist){
        Gson gson = new Gson();
        try {
            Writer writer = Files.newBufferedWriter(Paths.get("survey.json"));
            writer.write(gson.toJson(currentlist));
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getCodeQR() {
        return codeQR;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public List<Vaccine> getVaccines() {
        return vaccines;
    }

    public void setVaccines(List<Vaccine> vaccines) {
        this.vaccines = vaccines;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public List<Vaccine> getVaccine() {
        return vaccines;
    }

    public void setVaccine(List<Vaccine> vaccine) {
        this.vaccines = vaccine;
    }

    public void setCodeQR(String codeQR) {
        this.codeQR = codeQR;
    }
}
