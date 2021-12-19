package View;

import Controller.CalendarController;
import Controller.PeriodeController;
import Controller.PersonController;
import Model.Period;
import Model.Router;
import java.io.IOException;
import java.util.List;

public class VolunteerView extends View{
    private PersonController personController = new PersonController();
    private PeriodeController periodeController = new PeriodeController();
    private CalendarController calendarController = new CalendarController();

    public void showVolunteerMenu(Router router){
        System.out.println(
                "- [1] Consultation du calendrier: Accédez au calendrier et cherchez les rendez-vous à venir\n" +
                        "- [2] Consulation de la liste des visiteurs: Accédez à la liste des visiteurs.\n" +
                        "- [3] Réserver un rendez-vous: faire une réservation pour un visiteur\n" +
                        "- [0] Retour au menu principal");
        try {
            input = reader.readLine();
        } catch (
        IOException e) {
            e.printStackTrace();
        }
        if (input.trim().equals("1")) {
            router.calendarPage(router);
        }
        else if(input.trim().equals("2")){
            router.showVisitorList(router);
        }
        else if(input.trim().equals("3")){
            router.makeAppointment(router);
        }
        else{
            router.volunteerMain(router);
        }
    }
    public void showVisitorList(Router router){
        personController.printPersonList(false);
        router.volunteerMain(router);
    }

    public void volunteerMakeAppointment(Router router){
        String date = null;
        boolean visitePlanifiee;
        System.out.println("Veuillez choisir: Visite spontanée (1) ou Rendez-vous planifié (2)?");
        while (true){
            try {
                input = reader.readLine();
                if(input.equals("1")){
                    visitePlanifiee = false;
                    break;
                }else if(input.equals("2")){
                    visitePlanifiee = true;
                    break;
                }else {
                    System.out.println("Entrée invalide, choisir 1 ou 2.");
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        System.out.println("Entrez une date sous format yyyy-MM-dd ");
        while(true){
            try {
                input = reader.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
            date = input.trim();
            if(calendarController.isValidDate(date)){break;}
            else {
                System.out.println("Entrez svp une date valide");
            }
        }
        System.out.println("Les temps disponibles pour ce date :");
        List<Integer> days = calendarController.getAvailablePeriod(date);
        System.out.println(days.toString());
        System.out.println("Choisir un temps :");
        try {
            input = reader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        int time = days.get(days.indexOf(Integer.parseInt(input)));
        Period period = new Period(date,time);
        System.out.println("Ajouter une nouvelle visite");
        System.out.println("Entrer les informations de la visite:");
        System.out.println("Prénom :");
        try {
            input = reader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        String firstName = input.trim();
        System.out.println("Nom de Famille :");
        try {
            input = reader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        String lastName = input.trim();
        System.out.println("Dose 1 ou 2:");
        String dose;
        while(true){
            try {
                input = reader.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
            if(input!= "1" || input!="2"){
                System.out.println("Entrer invalide, veuillez choisir 1 ou 2.");
            }else{
                dose = input.trim();
                break;
            }
        }
        periodeController.addVisit(time,firstName,lastName,dose,visitePlanifiee);
        router.volunteerMain(router);
    }
}
