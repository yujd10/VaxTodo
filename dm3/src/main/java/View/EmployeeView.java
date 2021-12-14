package View;
import Controller.PersonController;
import Controller.VisitController;
import Model.Calendar;
import Model.Period;
import Model.Person;
import Model.Router;

import java.io.IOException;
import java.util.List;


public class EmployeeView extends View{

    public void showEmployeeMenu(Router router){
        System.out.println(
                "- [1] Gestion des visiteurs: Accédez à la liste des visiteurs et ajouter, modifier ou supprimer un visiteur.\n" +
                "- [2] Gestion des bénévoles: Accédez à la liste des bénévoles et ajouter, modifier ou supprimer un bénévole.\n" +
                        "- [3] Envoi courriel de suivi: Envoyez un courriel de suivi à un visiteur pour lui rappeler\n" +
                        "- [4] Calendrier: Consultation du calendrier et ajouter les visits/rendez-vous\n" +
                        "- [5] Traitement des visits: Confirmer les visits et Remplir les formulaire\n" +
                        "- [6] Remplir questionnaire: Remplir le questionnaire pour avoir les informations personnelles du visiteur\n" +
                        "- [0] Quitter l'application");
        try {
            input = reader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (input.trim().equals("1") || input.trim().equals("2")) {
            String role = (input.trim().equals("1")) ? "visiteurs" : "bénévoles";
            System.out.println("current role is "+ role);
            router.managePerson(router, role);
        }
        else if (input.trim().equals("3")){
            router.followUpPage();
        }
        else if (input.trim().equals("4")){
            router.calendarPage(router);
        }
        else if (input.trim().equals("5")){
            router.visitPage(router);
        }
        else if (input.trim().equals("6")){
            router.surveyPage();
        }
        else if(input.trim().equals(("0"))){
            exit();
        }
        else{
            System.out.println("Invalid option");
            router.employeeMain(router);
        }
    }

    public void showManagePersonMenu(Router router, String role) {
        boolean isVolunteer = (role.equals("bénévoles")) ? true : false;
        System.out.println(
                        "- [1] Consulter la liste des "+role+"\n" +
                        "- [2] Créer un compte\n" +
                        "- [3] Modifier un compte\n" +
                        "- [4] Supprimer un compte\n" +
                        "- [0] Retour au menu principal");
        PersonController personController = new PersonController();
        try {
            input = reader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (input.trim().equals("1")){
            personController.printPersonList(isVolunteer);
            router.managePerson(router,role);
        } else if (input.trim().equals("2")) {
            System.out.println("Entrer les informations du " + role + " en format de \n" +
                    "numéro de compte; nom de famille; prénom ; date de naissance YYYY-MM-DD ; courriel ; numéro de téléphone");

            try {
                input = reader.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
            System.out.println("Entrer l'addresse de la personne: numéro;rue;ville;province;code postal");
            String address = null;
            try {
                address = reader.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
            String[] personInfo = input.split(";");
            String inputVerification = (personController.createPerson(personInfo,isVolunteer,address));
            if(inputVerification != ""){
                System.out.println("Création du compte échouer!");
                System.out.println(inputVerification);
            }
            router.managePerson(router,role);
        } else if (input.trim().equals("3")) {
            System.out.printf("Entrer le numéro de compte du "+role+" ou son nom au complet (prénom:Nom de famille) ou son courriel: ");
            try {
                input = reader.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
            Person person = personController.search(input);
            if(person == null){
                router.managePerson(router, role);
            }
            System.out.println("Veuillez choisir l'option que vous désirez modifier: ");
            System.out.println(
                    "- [1] Numéro de compte\n" +
                            "- [2] Prénom\n" +
                            "- [3] Nom de famille\n" +
                            "- [4] Date de naissance\n" +
                            "- [5] Adresse courriel\n" +
                            "- [6] Numéro de téléphone\n" +
                            "- [7] Adresse\n" +
                            "- [0] Annuler\n");
            try {
                input = reader.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
            if (input.trim().equals("1")) {
                System.out.printf("Entrer le nouveau numéro de compte: ");
            }
            else if (input.trim().equals("2")){
                System.out.printf("Entrer le nouveau prénom: ");
            }
            else if (input.trim().equals("3")){
                System.out.printf("Entrer le nouveau nom de famille: ");
            }
            else if (input.trim().equals("4")){
                System.out.printf("Enter le nouveau date de naissance: ");
            }
            else if (input.trim().equals("5")){
                System.out.printf("Enter le nouveau addresse courriel: ");
            }
            else if (input.trim().equals("6")){
                System.out.printf("Enter le nouveau numéro de téléphone: ");
            }
            else if (input.trim().equals("7")){
                System.out.printf("Enter la adresse: (numéro;rue;ville;province;code postal) ");
            }
            else{
                router.employeeMain(router);
            }
            try {
                String option = input.trim();
                input = reader.readLine();
                if(person != null){
                    String inputVerification = (personController.updatePerson(person, option, input));
                    if(inputVerification != ""){
                        System.out.println("Modification échoué");
                        System.out.println(inputVerification);
                    }
                }
                //need data validation
            } catch (IOException e) {
                e.printStackTrace();
            }
            router.managePerson(router,role);
        }
        else if (input.trim().equals("4")) {
            System.out.println("Entrer le numéro de compte du visiteur pour supprimer: ");
            try {
                input = reader.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
            personController.deletePerson(input);
            router.managePerson(router,role);
        }
        else {
            router.employeeMain(router);
        }
    }

    public void showCalendarMenu(Router router){
        Calendar calendar = new Calendar();
        VisitController vc =new VisitController();
        List<String> next5days=null;
        String date = null;
        calendar.consultationOfCalendar(0);
        System.out.println("Voulez-vous consulter les 5 prochaines jours ? Y/N");
        try {
            input = reader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (input.trim().equals("N")||input.trim().equals("n")){
            next5days = calendar.consultationOfCalendar(0);
            System.out.printf("Chosir un jour que vous voulez traiter : ");
            }
        else if (input.trim().equals("Y")||input.trim().equals("y")) {
            next5days = calendar.consultationOfCalendar(5);
            System.out.printf("Chosir un jour que vous voulez traiter : ");
        }
        try {
            input = reader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }


        date = next5days.get(Integer.parseInt(input.trim())-1);
//        try {
//            input = reader.readLine();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

        System.out.println("Vous avez choisi : " + date +"\n"+" Choisir ce que vous voulez faire :");
        System.out.println( "- [1] Ajouter une rendez-vous(Visite non-spontané)\n"+
                "- [2] Ajouter une visite\n"+
                "- [3] Consulter les Periods libres\n"+
                "- [4] Envoyer les　notifications de rappel aux patients\n" );
        try {
            input = reader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (input.trim().equals("1")){
            System.out.println("Periods available for " + date +" are ");
            List<Integer> days =Calendar.periodsAvailable(date);
            System.out.println(days.toString());
            System.out.println("Choisir un temps :");
            try {
                input = reader.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
            int time = days.get(Integer.parseInt(input.trim())-1);
            Period period = new Period(date,time);
            System.out.println("Ajouter un nouveau rendez-vous");
            System.out.println("Entrer les information de rendez-vous:");
            System.out.println("Firstname :");
            try {
                input = reader.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
            String firstName = input.trim();
            System.out.println("Firstname :");
            try {
                input = reader.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
            String lastName = input.trim();
            System.out.println("Dose Number :");
            try {
                input = reader.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
            String dose = input.trim();
            period.addVisitAvecRDV(firstName,lastName,dose);

            showEmployeeMenu(router);
        }
        else if (input.trim().equals("2")){
            System.out.println("Periods available for " + date +" are ");
            List<Integer> days =Calendar.periodsAvailable(date);
            System.out.println(days.toString());
            System.out.println("Choisir un temps :");
            try {
                input = reader.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
            int time = days.get(Integer.parseInt(input.trim())-1);
            Period period = new Period(date,time);
            System.out.println("Ajouter un nouveau visit spontané");
            System.out.println("Entrer les information de visit:");
            System.out.println("Firstname :");
            try {
                input = reader.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
            String firstName = input.trim();
            System.out.println("Firstname :");
            try {
                input = reader.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
            String lastName = input.trim();
            System.out.println("Dose Number :");
            try {
                input = reader.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
            String dose = input.trim();
            period.addVisitSpontane(firstName,lastName,dose);

            showEmployeeMenu(router);
        }

    }

    public void showVisitMenu(Router router) {
        Calendar calendar = new Calendar();
        VisitController vc =new VisitController();
        System.out.println( "- [1] Confirmer un rendez-vous(Visite non-spontané)\n"+
                "- [2] Confirmer un visite(Sans rendez-vous)\n"+
                "- [3] Remplir une formulaire\n"+
                "- [4] shit it real---------------------------------------------------\n" );
        try {
            input = reader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (input.trim().equals("1")){
            System.out.println("Reservation Number :");
            try {
                input = reader.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
            int number = Integer.parseInt(input.trim());
            vc.confirmerVisitRDV(number);
        }
        else if (input.trim().equals("2")){
                System.out.println("First name:");
                try {
                    input = reader.readLine();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                String firstName = input.trim();
                System.out.println("Lase name:");
                try {
                    input = reader.readLine();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                String lastName = input.trim();
                vc.confirmerVisitSpontane(firstName,lastName);
            }
        }
}
