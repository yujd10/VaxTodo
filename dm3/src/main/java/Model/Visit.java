package Model;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Random;

/**
 * Classe pour les visites y inclut les informations du visiteur et vaccine à prendre
 */
public class Visit implements Serializable {
    private String firstName;
    private String lastName;
    private String dose;
    private DateTime datetime;
    private boolean isConfirmed;
    private boolean withRDV;
    private Integer reservationNumber;


    public Visit() {
    }

    public Visit(boolean withRDV,String firstName, String lastName, String dose,String date,String time) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.dose = dose;
        this.datetime=new DateTime();
        this.datetime.setDate(date);
        this.datetime.setTime(time);
        this.isConfirmed = false;
        this.withRDV = withRDV;
        //Si la Visite est avec rendez-vous le numéro de reservation va être généré automatiquement et aléatoirement
        if(withRDV){
            Random rnd = new Random();
            int number = rnd.nextInt(999999);
            this.reservationNumber = number;
        }
    }

    @Override
    public String toString() {
        return "Visit{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", dose='" + dose + '\'' +
                ", datetime=" + datetime.getDate() + " " + datetime.getTime() +
                ", isConfirmed=" + isConfirmed +
                ", withRDV=" + withRDV +
                ", reservationNumber=" + reservationNumber +
                '}';
    }

    /**
     * Confirmer une liste va changer son attribut isConfirmed à "true"
     * @return  La visite qui viens d'être confirmé
     */
    public Visit confirm(){
        if(isWithRDV()) {
            if (!Calendar.ifLate(Integer.parseInt(this.datetime.getTime()))) {
                this.isConfirmed = true;
                System.out.println("This visit for " +
                        this.firstName + " " +
                        this.lastName + " at " +
                        this.datetime.getDate() + " " +
                        this.datetime.getTime() + " " +
                        "is confirmed successfully !");
            } else {
                this.setWithRDV(false);
                this.reservationNumber = null;
                String time = this.getDatetime().getTime();
                String newTime = Integer.toString(Integer.parseInt(time) + 1);
                this.datetime.setTime(newTime);
                System.out.println("Vous êtes en retard, votre visite est spontanée maintenant !");
            }
        }
        else {
            this.isConfirmed = true;
            System.out.println("This visit for " +
                    this.firstName + " " +
                    this.lastName + " at " +
                    this.datetime.getDate() + " " +
                    this.datetime.getTime() + " " +
                    "is confirmed successfully !");
        }
        return this;
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

    public boolean isConfirmed() {
        return isConfirmed;
    }

    public void setConfirmed(boolean confirmed) {
        isConfirmed = confirmed;
    }

    public boolean isWithRDV() {
        return withRDV;
    }

    public void setWithRDV(boolean withRDV) {
        this.withRDV = withRDV;
    }

    public Integer getReservationNumber() {
        return reservationNumber;
    }

    public void setReservationNumber(int reservationNumber) {
        this.reservationNumber = reservationNumber;
    }
}
