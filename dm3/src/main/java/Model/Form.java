package Model;

public class Form{
    private Person visitor;
    private String visitDate;
    private Vaccine preferredVaccine;
    private boolean isFirstDose;
    private boolean hasCovid;
    private boolean hasSymptom;
    private boolean hasAllergies;
    private boolean hasCompleted;
    private boolean otherVaxTook;

    public Form() {
    }

    public Form(Person visitor, String visitDate, Vaccine preferredVaccine, boolean isFirstDose, boolean hasCovid, boolean hasSymptom, boolean hasAllergies, boolean hasCompleted, boolean otherVaxTook) {
        this.visitor = visitor;
        this.visitDate = visitDate;
        this.preferredVaccine = preferredVaccine;
        this.isFirstDose = isFirstDose;
        this.hasCovid = hasCovid;
        this.hasSymptom = hasSymptom;
        this.hasAllergies = hasAllergies;
        this.hasCompleted = hasCompleted;
        this.otherVaxTook = otherVaxTook;
    }

    

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

    public String getPreferredVaccine() {
        return preferredVaccine;
    }

    public void setPreferredVaccine(String preferredVaccine) {
        this.preferredVaccine = preferredVaccine;
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

    public boolean isHasCompleted() {
        return hasCompleted;
    }

    public void setHasCompleted(boolean hasCompleted) {
        this.hasCompleted = hasCompleted;
    }

    public Vaccine getVaxType() {
        return vaxType;
    }

    public void setVaxType(Vaccine vaxType) {
        this.vaxType = vaxType;
    }

    public boolean isOtherVaxTook() {
        return otherVaxTook;
    }

    public void setOtherVaxTook(boolean otherVaxTook) {
        this.otherVaxTook = otherVaxTook;
    }
}
