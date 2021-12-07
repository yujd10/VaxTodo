package Model;

import java.util.List;

public class VaccineProfile {
    private String id;
    private DateTime receivedDate;
    private Vaccine vaccine;
    private String codeQR;

    public VaccineProfile() {
    }

    public VaccineProfile(String id, DateTime receivedDate, Vaccine vaccine) {
        this.id = id;
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
        Person person = null;
//        List<Person> personList= person.readData();
        person = person.search(this.id);
        System.out.println("- Name : "+ person.getFirstName() + " " + person.getLastName() + "\n" +
                            "- Date : " + this.receivedDate.getDate() + "\n" +
                            "- Vaccinns reçu " + "\n" +
                            "--------to add things----------" +
                            "- Code QR :" + this.codeQR);
    }

    public String getCodeQR() {
        return codeQR;
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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
