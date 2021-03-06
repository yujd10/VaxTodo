package View;

import Controller.PersonController;
import Model.Router;
import Model.User;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class VolunteerView extends View{
    private PersonController personController = new PersonController();

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
            router.makeAppointment();
        }
        else{
            router.volunteerMain(router);
        }
    }
    public void showVisitorList(Router router){
        personController.printPersonList(false);
        router.volunteerMain(router);
    }
}
