package Model;

import java.util.List;

public class VaccineProfile {
    private Person person;
    private DateTime receivedDate;
    private Vaccine vaccine;
    private String codeQR;

    public VaccineProfile() {
    }

    public VaccineProfile(Person person, DateTime receivedDate, Vaccine vaccine) {
        this.person = person;
        this.receivedDate = receivedDate;
        this.vaccine = vaccine;
        this.codeQR = this.generateQR();
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
                            "- Date : " + this.receivedDate.getDate() + "\n" +
                            "- Vaccinns received " + "\n" +
                            "--------to add things----------" +
                            "- Code QR :" + this.codeQR +
                            "is sent to "+person.getEmailAddress() +" ! ");
    }

    public String getCodeQR() {
        return codeQR;
    }

    public DateTime getReceivedDate() {
        return receivedDate;
    }

    public void setReceivedDate(DateTime receivedDate) {
        this.receivedDate = receivedDate;
    }

    public Vaccine getVaccine() {
        return vaccine;
    }

    public void setVaccine(Vaccine vaccine) {
        this.vaccine = vaccine;
    }
}
