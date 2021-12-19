package Model;

import Controller.VisitController;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.reflect.TypeToken;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Period {
    private String date;
    private int start;

    public Period() {
    }

    public Period(String date, int start) {
        this.date = date;
        this.start = start;
    }

    public boolean isFull(){
        int counter = 0;
       Boolean filled = false;
       VisitController vs = new VisitController();
       List<Visit> visits = vs.read();
       for(Visit visit:visits){
           if(visit.getDatetime().getDate().equals(this.date)
                   &&Integer.parseInt(visit.getDatetime().getTime()) == this.start){
               counter ++;
           }
       }

       if(counter >=15) { filled = true; }
       return filled;
    }

    public Visit removeVisit(Visit visit){
        VisitController vc = new VisitController();
        List<Visit> visits = vc.read();
        for(Visit visit1:visits){
            if(visit1.equals(visit)){
                visits.remove(visit);
                System.out.println(visit.toString()+" is removed ");
            }
        }
        return visit;
    }

    public void addVisit(String firstName,String lastName,String dose,boolean spontanee){
        VisitController vc = new VisitController();
        String date = this.date;
        String time = Integer.toString(this.start);
        String type = null;
        Visit visit = null;
        if(dose.equals("1")){
            if(spontanee) {
                  visit =  vc.addNewVisit(true, firstName, lastName, dose, date, time);
                   type = "Rendez-vous ";
            }
            else {
               visit =  vc.addNewVisit(false, firstName, lastName, dose, date, time);
                type = "Visite spontanée ";
            }
            System.out.println(type+"pour "+firstName+" "+lastName +" du dose "+dose+" le "+ date+" à "+time +"heure est ajouter avec succès !");
            System.out.println("Le numéro de réservation est " + visit.getReservationNumber() );
        }

        else if(dose.equals("2")){
            if(!spontanee) {
                Visit firstVisit = vc.findVisit(firstName,lastName);
                String oldDate = firstVisit.getDatetime().getDate();
                if(firstVisit!=null){
                    if(Calendar.getDayCount(oldDate,date)>=30) {
                        visit = vc.addNewVisit(true, firstName, lastName, dose, date, time);
                        type = "Rendez-vous ";
                        System.out.println(type+"pour "+firstName+" "+lastName +" de "+ dose +" dose le"+ date+" à "+time +"heure est ajouter avec succès !");
                        System.out.println("Le numéro de réservation est " + visit.getReservationNumber() );
                    }
                    else {
                        System.out.println("Le temps entre les deux doit etre plus que 1 mois");
                    }
                }
                else {
                    System.out.println("SVP faite la première dose d‘abord !");
                }
            }
            else if(spontanee) {
                Visit firstVisit = vc.findVisit(firstName,lastName);
                String oldDate = firstVisit.getDatetime().getDate();
                if(firstVisit!=null) {
                    if (Calendar.getDayCount(oldDate, date) >= 30) {
                        visit = vc.addNewVisit(false, firstName, lastName, dose, date, time);
                        type = "Visite spontanee ";
                    } else {
                        System.out.println("Le temps entre les deux doit etre plus que 1 mois");
                    }
                    System.out.println(type+"pour "+firstName+" "+lastName +" de "+ dose +" dose le"+ date+" à "+time +"heure est ajouter avec succès !");
                    System.out.println("Le numéro de réservation est " + visit.getReservationNumber() );
                }
                else {
                    System.out.println("SVP faite la première dose d‘abord !");
                }
            }
        }

    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getStart() {
        return start;
    }

    public void setStart(int start) {
        this.start = start;
    }
}
