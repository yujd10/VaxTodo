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
        this.codeQR = generateQR();
    }

    public String generateQR(){
        String qrNumber = "";
        for(int i = 0; i < 26; i++) {
            qrNumber += Integer.toString((int) Math.floor(Math.random() * 10));
        }
        return qrNumber;
    }

    public void sendProfil(){
        System.out.println("- Name : "+ person.getFirstName() + " " + person.getLastName() + "\n" +
                            "- Date : " + this.date + "\n" +
                            "- Vaccinns received " + "\n" +
                            vaccines.toString() +
                            "- Code QR :" + this.codeQR +"\n"+
                            "is sent to "+person.getEmailAddress() +" ! ");
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

    public void addVaccine(Vaccine vaccine){
        this.vaccines.add(vaccine);
    }

    public static VaccineProfile findProfile(Person person){
        List<VaccineProfile> currentProfils = read();
        VaccineProfile profile = null;
        for(VaccineProfile vaccineProfile:currentProfils){
            if(vaccineProfile.getPerson().equals(person)){
                profile=vaccineProfile;
            }
        }
        if(profile==null){
            System.out.println("profile not found");
        }
        return profile;
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
}
