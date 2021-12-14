package Controller;

import Model.DateTime;
import Model.Visit;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class VisitController extends Controller{
    private Visit visit = new Visit();


    public void showCurrentVisits(){
        List<Visit> visits = read();
        for (Visit visit:visits){
            System.out.println(visit.toString());
        }
    }


    //File ///////////////////////////////////////////////
    public void addNewVisit(boolean withRDV,String firstName, String lastName, String dose,String date,String time){
        List<Visit> currentVisits = read();
        Visit visit = new Visit(withRDV,firstName,lastName,dose,date,time);
        System.out.println(visit.getDatetime().getDate());
        currentVisits.add(visit);
        saveData(currentVisits);
    }

    public void CancelVisit(int reservNumber){
        List<Visit> currentVisits = read();
        for(Visit visit:currentVisits){
            if(visit.getReservationNumber() == reservNumber){
                currentVisits.remove(visit);
            }
        }
    }

    public void confirmerVisitSpontane(String firstName,String lastName){
        List<Visit> visits=read();
        Integer index = null;
        for(Visit visit:visits){
            if(!visit.isWithRDV()
                    &&visit.getFirstName().equals(firstName)
                    &&visit.getLastName().equals(lastName)){
                index =visits.indexOf(visit);
                visits.set(index,visit.confirm());
            }
        }
        if(index == null){
            System.out.println("Visit n'existe pas ! ");
        }
        saveData(visits);
    }

    public void confirmerVisitRDV(int reserverNumber){
        List<Visit> visits=read();
        Integer index = null;
        for(Visit visit:visits){
            if(visit.isWithRDV()
                    &&visit.getReservationNumber() == reserverNumber){
                index =visits.indexOf(visit);
                visits.set(index,visit.confirm());
            }
        }
        if(index == null){
            System.out.println("Visit n'existe pas ! ");
        }
        saveData(visits);
    }



    public void getUpComingVisits(DateTime from, DateTime to){
    }

    public List<Visit> read(){
        List<Visit> results = new ArrayList<>();
        try {
            Reader reader = Files.newBufferedReader(Paths.get("visits.json"));
            results = new Gson().fromJson(reader,new TypeToken<List<Visit>>() {}.getType());
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return results;
    }

    public void saveData(List<Visit> currentlist){
        Gson gson = new Gson();
        try {
            Writer writer = Files.newBufferedWriter(Paths.get("visits.json"));
            writer.write(gson.toJson(currentlist));
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    //ENF OF FILE/////////////////////////////

    public void getUpComingVisits(int limit){

    }
}
