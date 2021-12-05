package Model;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Visit implements Serializable {
    private String reservationNumber;
    private String firstName;
    private String lastName;
    private String dose;
    private DateTime datetime;


    public Visit() {
    }

    public Visit(String reservationNumber, String firstName, String lastName, String dose,String date,String time) {
        this.reservationNumber = reservationNumber;
        this.firstName = firstName;
        this.lastName = lastName;
        this.dose = dose;
        new DateTime(date,time);
    }
    @Override
    public String toString(){

        return "Visit{" +
                "firtName='" + reservationNumber+ '\'' +
                "}\n";
    }

    public static void showCurrentVisits(){
        List<Visit> visits = readVisits();
        for (Visit visit:visits){
            System.out.println(visit.toString());
        }
    }

    public static void addNewVisit(String reservationNumber, String firstName, String lastName, String dose,String date,String time){
        List<Visit> currentVisits = readVisits();
        Visit visit = new Visit(reservationNumber,firstName,lastName,dose,date,time);
        currentVisits.add(visit);
        saveVisits(currentVisits);
    }

    public static void saveVisits(List<Visit> visits){
        PrintWriter writer = null;
        try {
            writer = new PrintWriter("card.out");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        writer.print("");
// other operations
        writer.close();
        try{
            FileOutputStream writeData = new FileOutputStream("card.out");
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
            FileInputStream readData = new FileInputStream("card.out");
            ObjectInputStream readStream = new ObjectInputStream(readData);

            listIn = (List<Visit>) readStream.readObject();
            readStream.close();
//            for(Visit visit:listIn){
//                System.out.println(visit.toString());
//            }
        }catch (Exception e) {
            e.printStackTrace();
        }

        return listIn;
    }

    public String getReservationNumber() {
        return reservationNumber;
    }

    public void setReservationNumber(String reservationNumber) {
        this.reservationNumber = reservationNumber;
    }

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
