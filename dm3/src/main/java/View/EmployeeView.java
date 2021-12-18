package View;
import Controller.PersonController;
import Controller.VisitController;
import Model.*;
import com.sun.xml.internal.bind.v2.TODO;


import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;


public class EmployeeView extends View{

    public void showEmployeeMenu(Router router){
        System.out.println(
                "- [1] Gestion des visiteurs: Accédez à la liste des visiteurs et ajouter, modifier ou supprimer un visiteur.\n" +
                "- [2] Gestion des bénévoles: Accédez à la liste des bénévoles et ajouter, modifier ou supprimer un bénévole.\n" +
                        "- [3] Suivi: Créer,Envoyer les profils de vaccination\n" +
                        "- [4] Calendrier: Consultation du calendrier et ajouter les visits/rendez-vous\n" +
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
            router.followUpPage(router);
        }
        else if (input.trim().equals("4")){
            router.calendarPage(router);
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
                    "numéro de compte; nom de famille; prénom; date de naissance YYYY-MM-DD;courriel; numéro de téléphone;");

            try {
                input = reader.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
            String[] personInfo = input.split(";");
            System.out.println("Entrer l'addresse de la personne: numéro;rue;ville;province;code postal");
            String address = null;
            try {
                address = reader.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
            String inputVerification = (personController.createPerson(personInfo,isVolunteer,address));
            if(!inputVerification.equals("")){
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

    public void calendarOptionMenu(Router router)  {
        Calendar calendar = new Calendar();
        GregorianCalendar cal = new GregorianCalendar();
        VisitController vc =new VisitController();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date todayDate = cal.getTime();
        String today = sdf.format(todayDate);
        System.out.println(
                "- [1] Ajouter une visite\n"+
                "- [2] Confirmer une visite\n"+
                "- [3] Remplir une formulaire\n"+
                "- [4] Récupérer et imprimer un formulaire d'identification-\n"+
                "- [5] Consulter les Periods libres\n"+
                "- [6] Consulter les cinq prochaines jours disponible\n"+
                "- [7] Envoyer les　notifications de rappel aux patients\n"+
                "- [0] Retourner au menu principale\n");

        try {
            input = reader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (input.trim().equals("1")){
            String date =null;
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
                    if(Calendar.isDateValid(date)){break;}
                    else {
                        System.out.println("Entrez svp une date valide");
                    }
            }
            System.out.println("Les temps disponibles pour ce date :");
            List<Integer> days =Calendar.periodsAvailable(date);
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
            try {
                input = reader.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
            String dose = input.trim();

            if(dose.equals("1")){
                period.addVisit(firstName,lastName,dose,visitePlanifiee);
            }
            else if(dose.equals("2")){
                period.addVisit(firstName,lastName,dose,visitePlanifiee);
            }
            calendarOptionMenu(router);
        }
        else if (input.trim().equals("2")){
            boolean visitePlanifiee;
            System.out.println("Confirmer une visite spontanée (1) ou une visite planifiée(2)?");
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
            if(visitePlanifiee){
                System.out.println("Numéro de réservation :");
                try {
                    input = reader.readLine();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                int number = Integer.parseInt(input.trim());
                vc.confirmerVisitRDV(number);
                Visit visit = vc.findVisitByNumber(number);
                if(visit.getDose().equals("2")) {
                    Form form = new Form();
                    form = form.findForm(visit.getFirstName(), visit.getLastName());
                    if (form != null) {
                        form.change(false,visit.getDatetime().getDate());
                    }
                }
            }else {
                System.out.println("First name:");
                try {
                    input = reader.readLine();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                String firstName = input.trim();
                System.out.println("Last name:");
                try {
                    input = reader.readLine();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                String lastName = input.trim();
                vc.confirmerVisitSpontane(firstName,lastName);
                Visit visit = vc.findVisit(firstName,lastName);
                if(visit.getDose().equals("2")) {
                    Form form = new Form();
                    form = form.findForm(visit.getFirstName(), visit.getLastName());
                    if (form != null) {
                        form.change(false,visit.getDatetime().getDate());
                    }
                }
                calendarOptionMenu(router);
            }
        }
        else if (input.trim().equals("3")){
            System.out.println("Est-ce que vous avez le numéro de compte ? Y/N");
            try {
                input = reader.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
            if((input.trim().equals("Y")||input.trim().equals("y"))){
                System.out.println("Entrer le numéro de compte");
            }else {
                System.out.println("Entrer le email ou la date de naissance");
            }
            try {
                input = reader.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
            String info = input.trim();
            Person person = new Person();
            person = person.search(info);
            if(person==null){
                calendarOptionMenu(router);
            }
            System.out.println("Vous traitez maintenan "+person.getFirstName()+" "+
                    person.getLastName() +"\n"
                    +"la date naissance est " + person.getBirthDate() +"\n"
                    +"le numero de compte est " + person.getId());
            System.out.println("Entrer votre vaccine Prefere :");
            try {
                input = reader.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
            String vax = input.trim();

            System.out.println("Entrer votre vaccine numero d'assurance Maladie :");
            try {
                input = reader.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
            String numeroDeAssurance = input.trim();

            System.out.println("Est-ce que c'est votre premier dose ? Y/N");
            try {
                input = reader.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
            boolean ifFirst;
            if(input.trim().equals("Y") ||input.trim().equals("y") ){ ifFirst= true;}
            else {ifFirst = false;}

            System.out.println("Avez-vous COVID avant ? Y/N");
            try {
                input = reader.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
            boolean covided;
            if(input.trim().equals("Y") ||input.trim().equals("y") ){ covided= true;}
            else {covided = false;}

            System.out.println("Avez-vous symptom maintenant ? Y/N");
            try {
                input = reader.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
            boolean symptom;
            if(input.trim().equals("Y") ||input.trim().equals("y") ){ symptom= true;}
            else {symptom = false;}

            System.out.println("Avez-vous allergies? Y/N");
            try {
                input = reader.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
            boolean allergies;
            if(input.trim().equals("Y") ||input.trim().equals("y") ){ allergies= true;}
            else {allergies = false;}


            System.out.println("Avez-vous procédé à la vaccination? Y/N");
            try {
                input = reader.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
            boolean proccededAutre;
            if(input.trim().equals("Y") ||input.trim().equals("y") ){ proccededAutre= true;}
            else {proccededAutre = false;}

            Vaccine vaccine = new Vaccine(vax);
            Form form = new Form(person,today,vaccine,numeroDeAssurance,ifFirst,covided,symptom,allergies,proccededAutre);
            form.addNewForm(form);

            if(ifFirst==true){
                System.out.println("Voulez-vous faire rendez-vous pour la deuxieme dose ? Y/N");
                try {
                    input = reader.readLine();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                if(input.trim().equals("Y")||input.trim().equals("y")){
                    List<String> datesAvailable = calendar.consultationOfCalendar(32);
                    System.out.println("Choisir index d'un jour:");
                    try {
                        input = reader.readLine();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    String date = datesAvailable.get(Integer.parseInt(input.trim()));

                    System.out.println("Les temps disponibles pour ce date :");
                    List<Integer> days =Calendar.periodsAvailable(date);
                    System.out.println(days);
                    try {
                        input = reader.readLine();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    Integer time = days.get(days.indexOf(Integer.parseInt(input.trim())));
                    Period secondPeriod = new Period(date,time);
                    secondPeriod.addVisit(person.getFirstName(), person.getLastName(),"2",true);
                }
            }
            System.out.println(form.toString());
            calendarOptionMenu(router);
        }
        else if (input.trim().equals("4")){
            //TODO Récupérer et imprimer un formulaire d'identification
        }
        else if (input.trim().equals("5")){
            System.out.println("Entrez une date sous format yyyy-MM-dd ");
            try {
                input = reader.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
            String date = input.trim();
            System.out.println("consulter les periodes libres");
            System.out.println("Periods available for " + date +" are ");
            List<Integer> days =Calendar.periodsAvailable(date);
            System.out.println(days.toString()+"\n");
            calendarOptionMenu(router);
        }
        else if (input.trim().equals("6")){
            List<String> next5days=null;
            String date = null;
            int count = 0;
            next5days = calendar.consultationOfCalendar(count);
            System.out.println("Voulez-vous consulter les 5 prochaines jours ? Y/N");
            try {
                input = reader.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
            while(true){
                if((input.trim().equals("Y")||input.trim().equals("y"))){
                    next5days = calendar.consultationOfCalendar(count+=7);
                    System.out.println("Voulez-vous consulter les 5 prochaines jours ? Y/N");
                    try {
                        input = reader.readLine();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                else if((input.trim().equals("N")||input.trim().equals("n"))){
                    System.out.printf("Chosir un jour que vous voulez traiter : ");
                    try {
                        input = reader.readLine();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    break;
                }
            }
            calendarOptionMenu(router);
        }
        else if(input.trim().equals("7")){
            //TODO Envoyer les　notifications de rappel aux patients
        }
        else if(input.trim().equals("0")){
            showEmployeeMenu(router);
        }
        else {
            System.out.println("Choix non-valide !");
            calendarOptionMenu(router);
        }
    }

    public void showSuiviMenu(Router router){
        System.out.println( "- [1] Ajouter une Profil de vaccine(et la premier dose)\n"+
                "- [2] Ajouter la deuxieme dose\n"+
                "- [3] Envoyer le rapport de vaccination\n"+
                "- [0] Retourner au menu principale\n");
        try {
            input = reader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        if(input.trim().equals("1")){
            Form form = new Form();
            System.out.println("Entrer la date que vous souhaitez de chercher yyyy-MM-dd");
            try {
                input = reader.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
            String date = input.trim();
            List<Form> forms = form.formOfADay(date);
            if(forms.isEmpty()){showSuiviMenu(router);}
            System.out.println("Chosir le forme a qui vous voulez ajouter un profil");
            try {
                input = reader.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
            int index = Integer.parseInt(input.trim());
            form = forms.get(index);
            Person person = form.getVisitor();
            Vaccine vaccine = form.getPreferredVaccine();
            List<Vaccine> vaccines = new ArrayList<>();
            vaccines.add(vaccine);
            VaccineProfile vaccineProfile = new VaccineProfile(person,date,vaccines);
            vaccineProfile.addProfile(vaccineProfile);
            System.out.println("Le profile de vaccine pour "+person.getFirstName()+person.getLastName()+" est genere! ");
            showSuiviMenu(router);
        }
        else if(input.trim().equals("2")){
            Form form = new Form();
            System.out.println("Entrer la date que vous souhaitez de chercher yyyy-MM-dd");
            try {
                input = reader.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
            String date = input.trim();
            List<Form> forms = form.formOfADay(date);
            if(forms.isEmpty()){showSuiviMenu(router);}
            System.out.println("Chosir le forme a qui vous voulez ajouter un profil");
            try {
                input = reader.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
            int index = Integer.parseInt(input.trim());
            form = forms.get(index);
            Vaccine vaccine = form.getPreferredVaccine();
            Person person = form.getVisitor();
            VaccineProfile vaccineProfile = VaccineProfile.read().get(VaccineProfile.findProfile(person));
            vaccineProfile.addVaccine(vaccine);
            showSuiviMenu(router);
        }
        else if(input.trim().equals("3")){
            Form form = new Form();
            System.out.println("Entrer la date que vous souhaitez de chercher yyyy-MM-dd");
            try {
                input = reader.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
            String date = input.trim();

            List<VaccineProfile> profiles = VaccineProfile.read();
            if(profiles.isEmpty()){showSuiviMenu(router);}
            System.out.println("Chosir la personne a qui vous voulez envoyer le profile");
            for(int i = 0; i < profiles.size(); i++){
                System.out.println(i+1 +". "+ profiles.get(i).getPerson().getFirstName()+" "+
                        profiles.get(i).getPerson().getLastName()+" "+ profiles.get(i).getPerson().getEmailAddress() +"\n");
            }
            try {
                input = reader.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
            int index = Integer.parseInt(input.trim());
            VaccineProfile profile = profiles.get(index-1);
            profile.sendProfil();
            showSuiviMenu(router);
        }
        else if(input.trim().equals("0")){
            showEmployeeMenu(router);
        }
    }

}
