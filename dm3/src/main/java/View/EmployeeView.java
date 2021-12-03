package View;

import Controller.Controller;
import Controller.PersonController;
import Model.Router;
import Model.User;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class EmployeeView extends View{
    public void showEmployeeMenu(Router router){
        System.out.println(
                "- [1] Gestion des visiteurs: Accédez à la liste des visiteurs et ajouter, modifier ou supprimer un visiteur.\n" +
                        "- [2] Envoi courriel de suivi: Envoyez un courriel de suivi à un visiteur pour lui rappeler\n" +
                        "- [3] Consultation du calendrier: Accédez au calendrier et cherchez les rendez-vous à venir\n" +
                        "- [4] Réserver un rendez-vous: Faire la réservation d'un rendez vous\n" +
                        "- [5] Remplir questionnaire: Remplir le questionnaire pour avoir les informations personnelles du visiteur\n" +
                        "- [0] Retour au menu principal");
        try {
            input = reader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (input.trim().equals("1")) {
            router.manageVisitor(router);
        }
        else if (input.trim().equals("2")){
            router.followUpPage();
        }
        else if (input.trim().equals("3")){
            router.calendarPage();
        }
        else if (input.trim().equals("4")){
            router.makeAppointment();
        }
        else if (input.trim().equals("5")){
            router.surveyPage();
        }
        else{
            router.employeeMain(router);
        }
    }

    public void showManageVisitorMenu(Router router){
        System.out.println(
                "- [1] Créer un compte\n" +
                        "- [2] Modifier un compte\n" +
                        "- [3] Supprimer un compte\n" +
                        "- [4] Chercher un compte\n" +
                        "- [0] Retour au menu principal");

        if (input.trim().equals("1")) {
            System.out.println("Entrer les informations du visiteurs en format de \n" +
                    "numéro de compte; nom de famille; prénom ; date de naissance YYYY-MM-DD ; courriel ; numéro de téléphone");

            try {
                input = reader.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
            PersonController personController = new PersonController();
            String[] visitorInfo = input.split(";");
            personController.createPerson(visitorInfo, false);
            router.manageVisitor(router);
        }
    }
}
