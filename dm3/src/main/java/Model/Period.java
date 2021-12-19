package Model;

import Controller.VisitController;
import java.util.List;

/**
 * Classe pour les périods
 */
public class Period {
    private String date;
    private int start;

    public Period() {
    }

    public Period(String date, int start) {
        this.date = date;
        this.start = start;
    }

    /**
     * Fonction permet de vérifier si ce period lui-même est rempli
     * @return le boolean si ce period est rempli
     */
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

    /**
     * Supprimer une particule visite qui existe déjà dans le database
     * @param visit la visite que vous voulez
     * @return La visite qui est supprimée
     */
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

    /**
     * Fonction permet d'ajouter une visite vers le database(json) en précisant son prénom, son nom, son dose et
     * si cela est une visite spontanée, puis si cela est pour la deuxième dose, on vérifie si la date est plus
     * que 30 jours plus tard que la première visite.
     * @param firstName prénom
     * @param lastName nom
     * @param dose dose
     * @param spontanee si elle est une visite spontanée
     */
    public void addVisit(String firstName,String lastName,String dose,boolean spontanee){
        VisitController vc = new VisitController();
        String date = this.date;
        String time = Integer.toString(this.start);
        String type = null;
        if(dose.equals("1")){
            if(!spontanee) {
                   vc.addNewVisit(true, firstName, lastName, dose, date, time);
                   type = "Rendez-vous ";
            }
            else {
                vc.addNewVisit(false, firstName, lastName, dose, date, time);
                type = "Visite spontanée ";
            }
            System.out.println(type+firstName+" "+lastName +" de "+ dose +"a"+ date+" "+time +" dose est ajouter avec succes !");
        }

        else if(dose.equals("2")){
            if(!spontanee) {
                Visit firstVisit = vc.findVisit(firstName,lastName);
                String oldDate = firstVisit.getDatetime().getDate();
                if(firstVisit!=null){
                    if(Calendar.getDayCount(oldDate,date)>=30) {
                        vc.addNewVisit(true, firstName, lastName, dose, date, time);
                        type = "Rendez-vous ";
                    }
                    else {
                        System.out.println("Le temps entre les deux doit etre plus que 1 mois");
                    }
                    System.out.println(type+firstName+" "+lastName +" de "+ dose +"a"+ date+" "+time +" dose est ajouter avec succes !");
                }
                else {
                    System.out.println("SVP faite la première dose d‘abord !");
                }
            }
            else {
                Visit firstVisit = vc.findVisit(firstName,lastName);
                String oldDate = firstVisit.getDatetime().getDate();
                if(firstVisit!=null) {
                    if (Calendar.getDayCount(oldDate, date) >= 30) {
                        vc.addNewVisit(false, firstName, lastName, dose, date, time);
                        type = "Visite spontanee ";
                    } else {
                        System.out.println("Le temps entre les deux doit etre plus que 1 mois");
                    }
                    System.out.println(type+firstName+" "+lastName +" de "+ dose +"a"+ date+" "+time +" dose est ajouter avec succes !");
                }
                else {
                    System.out.println("SVP faite la première dose d‘abord !");
                }
            }
        }

    }


    //Getters and Setters
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
