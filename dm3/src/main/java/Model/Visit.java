package Model;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class Visit implements Serializable {
//    private String reservationNumber;
    private String firstName;
    private String lastName;
    private String dose;
    private DateTime datetime;
    private boolean isConfirmed;




    public Visit() {
    }

    public Visit(String firstName, String lastName, String dose,String date,String time) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.dose = dose;
        this.datetime=new DateTime(date,time);
        this.isConfirmed = false;
    }

    @Override
    public String toString(){
        return " Visit { " +
                " Name : " + firstName + " " +lastName.toUpperCase(Locale.ROOT)  +
                " } ";
    }


    public boolean confirm(){
        //TODO:check availability in calender
        this.isConfirmed = true;
        return this.isConfirmed;
    }

    public void Cancel(){
        //TODO:delete the visit
    }

    public boolean isValide(){
        boolean isValide = false;
        //TODO:check if valide
        return isValide;
    }

    public static void showCurrentVisits(){
        List<Visit> visits = readVisits();
        for (Visit visit:visits){
            System.out.println(visit.toString());
        }
    }


    //File ///////////////////////////////////////////////
    public static void addNewVisit(String reservationNumber, String firstName, String lastName, String dose,String date,String time){
        List<Visit> currentVisits = readVisits();
        Visit visit = new Visit(firstName,lastName,dose,date,time);
        currentVisits.add(visit);
        saveVisits(currentVisits);
    }

    public static void saveVisits(List<Visit> visits){
        PrintWriter writer = null;
        try {
            writer = new PrintWriter("visit.out");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        try{
            FileOutputStream writeData = new FileOutputStream("visit.out");
            ObjectOutputStream writeStream = new ObjectOutputStream(writeData);

            writeStream.writeObject(visits);
            writeStream.flush();
            writeStream.close();

        }catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static List<Visit> readVisits(){
        List<Visit> listIn= new ArrayList<>();
        try{
            FileInputStream readData = new FileInputStream("visit.out");
            ObjectInputStream readStream = new ObjectInputStream(readData);

            listIn = (List<Visit>) readStream.readObject();
            readStream.close();
        }catch (Exception e) {
            e.printStackTrace();
        }

        return listIn;
    }
    //ENF OF FILE/////////////////////////////



    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public DateTime getDatetime() {
        return datetime;
    }

    public void setDatetime(DateTime datetime) {
        this.datetime = datetime;
    }

    public String getDose() {
        return dose;
    }

    public void setDose(String dose) {
        this.dose = dose;
    }

}
