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


//    public void showCurrentVisits(){
//        List<Visit> visits = read();
//        for (Visit visit:visits){
//            System.out.println(visit.toString());
//        }
//    }


//    public String lookForFirstDose(String firstName,String lastName){
//        Visit visit = null;
//        String date = null;
//        List<Visit> visits = read();
//        for(Visit visit1:visits){
//            if(visit1.getFirstName().equals(firstName)
//                    &&visit1.getLastName().equals(lastName)){
//                visit = visit1;
//            }
//        }
//        if(visit != null){
//            date=visit.getDatetime().getDate();
//        }
//        return date;
//    }

    /**
     * Ajouter une visite en précisant son status de rendez-vous, prénom, nom, dose, date de visite et son temps de visite
     * @param withRDV status de rendez-vous
     * @param firstName prénom
     * @param lastName nom
     * @param dose
     * @param date date de visite
     * @param time temps de visite
     */
    public void addNewVisit(boolean withRDV,String firstName, String lastName, String dose,String date,String time){
        List<Visit> currentVisits = read();
        Visit visit = new Visit(withRDV,firstName,lastName,dose,date,time);
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

    /**
     * Fonction permet de chercher une visite spontanée en précisant le nom de la personne de cette visite
     * @param firstName prénom
     * @param lastName nom
     * @return la visite trouvé
     */
    public Visit findVisit(String firstName,String lastName){
        List<Visit> visits=read();
        Visit visit1 = new Visit();
        for(Visit visit:visits){
            if(visit.getFirstName().equals(firstName)
                    &&visit.getLastName().equals(lastName)){
                visit1 = visit;
            }
        }
        return visit1;
    }

    /**
     * Fonction permet de chercher une rendez-vous en précisant son numéro de reservation
     * @param number numéro de reservation
     * @return la visite trouvé
     */
    public Visit findVisitByNumber(Integer number){
        List<Visit> visits=read();
        Visit visit1 = null;
        for(Visit visit:visits){
            if(visit.getReservationNumber()!=null && visit.getReservationNumber().equals(number)){
                visit1 = visit;
            }
        }
        return visit1;
    }


    /**
     * Fonction permet de confirmer une visite spontanée en précisant le prénom et le nom de la personne de
     * cette visite, si la visite avec cette personne n'existe pas, imprimer un error message
     * @param firstName prénom
     * @param lastName nom
     */
    public void confirmerVisitSpontane(String firstName,String lastName){
        List<Visit> visits=read();
        Integer index = null;
        for(Visit visit:visits){
            if(!visit.isConfirmed()&&!visit.isWithRDV()
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

    /**
     * Fonction permet de confirmer une rendez-vous en précisant son numéro de reservation
     * et si la visite avec cette numéro de compte n'existe pas, imprimer un error message
     * @param reserverNumber la numéro de reservation de la visite à être confirmée
     */
    public void confirmerVisitRDV(int reserverNumber){
        List<Visit> visits=read();
        Integer index = null;
        for(Visit visit:visits){
            if(visit.getReservationNumber()!=null&&!visit.isConfirmed()&&visit.isWithRDV()
                    &&visit.getReservationNumber() == reserverNumber){
                index =visits.indexOf(visit);
                visits.set(index,visit.confirm());
            }
            if(visit.getReservationNumber()!=null&&visit.getReservationNumber() == reserverNumber&&visit.isConfirmed()){
                System.out.println("La visite est déjà confirmée.");
                index = -1;
            }
        }
        if(index == null){
            System.out.println("La visite n'existe pas ! ");
        }
        saveData(visits);
    }



    public void getUpComingVisits(DateTime from, DateTime to){
    }
    //File ///////////////////////////////////////////////
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
}
