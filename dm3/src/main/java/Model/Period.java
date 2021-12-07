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
       Boolean filled = true;
       VisitController vs = new VisitController();
       List<Visit> visits = vs.read();
       for(Visit visit:visits){
           if(visit.getDatetime().getDate().equals(this.date)
                   &&Integer.parseInt(visit.getDatetime().getTime()) == this.start){
               counter ++;
               System.out.println(counter);
           }
       }

       if(counter >=15) { filled = false; }
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

    public void addVisit(Visit visit){
        VisitController vc = new VisitController();
        String firstName = visit.getFirstName();
        String lastName = visit.getLastName();
        String dose = visit.getDose();
        String date = this.date;
        String time = Integer.toString(this.start);

        if(visit.isWithRDV()){
            vc.addNewVisit(true,999999,firstName,lastName,dose,date,time);
        }
        else {vc.addNewVisit(false,null,firstName,lastName,dose,date,time);}
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
