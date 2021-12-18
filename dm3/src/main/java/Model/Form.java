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

public class Form{
    private Person visitor;
    private String visitDate;
    private Vaccine preferredVaccine;
    private String numeroDeAssurance;
    private boolean isFirstDose;
    private boolean hasCovid;
    private boolean hasSymptom;
    private boolean hasAllergies;
    private boolean otherVaxTook;

    public String Form(Form form) {
        return form.toString();
    }

    public Form(Person visitor, String visitDate, Vaccine preferredVaccine,String numeroDeAssurance, boolean isFirstDose, boolean hasCovid, boolean hasSymptom, boolean hasAllergies, boolean otherVaxTook) {
        this.visitor = visitor;
        this.visitDate = visitDate;
        this.preferredVaccine = preferredVaccine;
        this.isFirstDose = isFirstDose;
        this.hasCovid = hasCovid;
        this.hasSymptom = hasSymptom;
        this.hasAllergies = hasAllergies;
        this.otherVaxTook = otherVaxTook;
        this.numeroDeAssurance = numeroDeAssurance;
    }

    public Form recoverForm(){return null;}

    public void addNewForm(Form form){
        List<Form> currentForms = read();
        currentForms.add(form);
        saveData(currentForms);
    }
    //File//////////////////////////////////////////////////
    public List<Form> read(){
        List<Form> results = new ArrayList<>();
        try {
            Reader reader = Files.newBufferedReader(Paths.get("form.json"));
            results= new Gson().fromJson(reader,new TypeToken<List<Form>>() {}.getType());
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return results;
    }

    public void saveData(List<Form> currentlist){
        Gson gson = new Gson();
        try {
            Writer writer = Files.newBufferedWriter(Paths.get("form.json"));
            writer.write(gson.toJson(currentlist));
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    //ENF OF FILE/////////////////////////////

    @Override
    public String toString() {
        return "Form{" +
                "visitor=" + visitor.getFirstName() + " " + visitor.getLastName() +
                ", visitDate='" + visitDate  +
                ", preferredVaccine=" + preferredVaccine.getName() +
                ", isFirstDose=" + isFirstDose +
                ", hasCovid=" + hasCovid +
                ", hasSymptom=" + hasSymptom +
                ", hasAllergies=" + hasAllergies +
                ", otherVaxTook=" + otherVaxTook +
                '}';
    }


    //Getters and Setters

    public Person getVisitor() {
        return this.visitor;
    }

    public void setVisitor(Person visitor) {
        this.visitor = visitor;
    }

    public String getVisitDate() {
        return visitDate;
    }

    public void setVisitDate(String visitDate) {
        this.visitDate = visitDate;
    }

    public boolean isFirstDose() {
        return isFirstDose;
    }

    public void setFirstDose(boolean firstDose) {
        isFirstDose = firstDose;
    }

    public boolean isHasCovid() {
        return hasCovid;
    }

    public void setHasCovid(boolean hasCovid) {
        this.hasCovid = hasCovid;
    }

    public boolean isHasSymptom() {
        return hasSymptom;
    }

    public void setHasSymptom(boolean hasSymptom) {
        this.hasSymptom = hasSymptom;
    }

    public boolean isHasAllergies() {
        return hasAllergies;
    }

    public void setHasAllergies(boolean hasAllergies) {
        this.hasAllergies = hasAllergies;
    }

    public boolean isOtherVaxTook() {
        return otherVaxTook;
    }

    public void setOtherVaxTook(boolean otherVaxTook) {
        this.otherVaxTook = otherVaxTook;
    }

    public Vaccine getPreferredVaccine() {
        return preferredVaccine;
    }

    public void setPreferredVaccine(Vaccine preferredVaccine) {
        this.preferredVaccine = preferredVaccine;
    }
}
