import java.io.Console;
import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class VaxToDo_re_Prototype {
    static Scanner scanner = new Scanner(System.in);
    static Random random = new Random();
    public static void main(String[] args) {
        login();
//        main_menu();
//        calendrier();
//        showCalendar();
//          liste_visiteur();
//        liste_benevole();
//        takeAppointment();
//        cancelAppointment();
    }

    public static void login(){
        System.out.println("\t\tPage Login");
        System.out.printf("Entrer votre numéro d'employé: ");
        String userid = scanner.nextLine();
        System.out.printf("Entrer votre mot de passe: ");
        String password = scanner.nextLine();
        System.out.println("Utilisateur vérifié!");
        main_menu();
    }
    public static void main_menu(){
        System.out.println("+++++++++++++++++++++++++++++++++++++++++");
        System.out.println("+\t\t\tMenu Principal\t\t\t\t+");
        System.out.println("+\t\t1. Le calendrier\t\t\t\t+");
        System.out.println("+\t\t2. Liste des visiteurs\t\t\t+");
        System.out.println("+\t\t3. Liste des bénévoles\t\t\t+");
        System.out.println("+\t\t4. Quitter\t\t\t\t\t\t+");
        System.out.println("+++++++++++++++++++++++++++++++++++++++++");
        System.out.printf("Choisir votre option: ");
        byte choice = scanner.nextByte();
        switch (choice){
            case 1:
                calendrier();
                break;
            case 2:
                liste_visiteur();
                break;
            case 3:
                liste_benevole();
                break;
            case 4:
                System.exit(0);
            default:
                System.out.println("option non valide!\n");
                main_menu();
        }
    }
    public static void calendrier(){
        System.out.println("+++++++++++++++++++++++++++++++++++++++++");
        System.out.println("+\t\t\t Le Calendrier\t\t\t\t+");
        System.out.println("+\t\t1. Voir le Calendrier\t\t\t+");
        System.out.println("+\t\t2. Prendre un rendez-vous\t\t+");
        System.out.println("+\t\t3. Annuler un rendez-vous\t\t+");
        System.out.println("+\t\t4. Retour à la page principal\t+");
        System.out.println("+\t\t5. Quitter\t\t\t\t\t\t+");
        System.out.println("+++++++++++++++++++++++++++++++++++++++++");
        System.out.printf("Choisir votre option: ");
        byte choice = scanner.nextByte();
        switch (choice){
            case 1:
                showCalendar();
                break;
            case 2:
                takeAppointment();
            case 3:
                cancelAppointment();
            case 4:
                main_menu();
            case 5:
                System.exit(0);
            default:
                System.out.println("option non valide!");
                calendrier();
        }
    }
    public static void liste_visiteur(){
        System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++++");
        System.out.println("+\t\t\t Liste des visiteurs\t\t\t\t\t+");
        System.out.println("+\t\t1. Voir la liste des comptes visiteurs\t\t+");
        System.out.println("+\t\t2. Créer un compte\t\t\t\t\t\t\t+");
        System.out.println("+\t\t3. Modifier un compte visiteur\t\t\t\t+");
        System.out.println("+\t\t4. Supprimer un compte visiteur\t\t\t\t+");
        System.out.println("+\t\t5. Consulter le profil de vaccination\t\t+");
        System.out.println("+\t\t6. Retour à la page principal\t\t\t\t+");
        System.out.println("+\t\t7. Quitter\t\t\t\t\t\t\t\t\t+");
        System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++++");
        System.out.printf("Choisir votre option: ");
        byte choice = scanner.nextByte();
        switch (choice){
            case 1:
            case 2:
            case 3:
            case 4:
            case 5:
            case 6:
                main_menu();
            case 7:
                System.exit(0);
            default:
                System.out.println("option non valide!");
                liste_visiteur();
        }
    }
    public static void liste_benevole(){
        System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++++");
        System.out.println("+\t\t\t La liste des bénévoles\t\t\t\t\t+");
        System.out.println("+\t\t1. Voir la liste des bénévoles\t\t\t\t+");
        System.out.println("+\t\t2. Ajouter un bénévole\t\t\t\t\t\t+");
        System.out.println("+\t\t3. Modifier un bénévole\t\t\t\t\t\t+");
        System.out.println("+\t\t4. Supprimer un bénévole\t\t\t\t\t+");
        System.out.println("+\t\t5. Retour à la page principal\t\t\t\t+");
        System.out.println("+\t\t6. Quitter\t\t\t\t\t\t\t\t\t+");
        System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++++");
        System.out.printf("Choisir votre option: ");
        byte choice = scanner.nextByte();
        switch (choice){
            case 1:
            case 2:
            case 3:
            case 4:
            case 5:
                main_menu();
            case 6:
                System.exit(0);
            default:
                System.out.println("option non valide!");
                liste_benevole();
        }
    }


    public static void showCalendar(){
        System.out.println("+++++++++++++++++++++++++++++++++");
        System.out.println("+\t\tAnnée 2021\t\t\t\t+");
        System.out.println("+\t\t1. Janvier\t\t\t\t+");
        System.out.println("+\t\t2. Fevrier\t\t\t\t+");
        System.out.println("+\t\t3. Mars\t\t\t\t\t+");
        System.out.println("+\t\t4. Avril\t\t\t\t+");
        System.out.println("+\t\t5. Mai\t\t\t\t\t+");
        System.out.println("+\t\t6. Juin\t\t\t\t\t+");
        System.out.println("+\t\t7. Juillet\t\t\t\t+");
        System.out.println("+\t\t8. Août\t\t\t\t\t+");
        System.out.println("+\t\t9. Septembre\t\t\t+");
        System.out.println("+\t\t10. Octobre\t\t\t\t+");
        System.out.println("+\t\t11. Novembre\t\t\t+");
        System.out.println("+\t\t12. Décembre\t\t\t+");
        System.out.println("+++++++++++++++++++++++++++++++++");
        System.out.printf("Choisir votre option: ");
        byte choice = scanner.nextByte();
        byte day = -1;
        switch (choice){
            case 1:
            case 3:
            case 5:
            case 7:
            case 8:
            case 10:
            case 12:
                System.out.println();
                for(int i = 1; i <= 31; i++){
                    System.out.printf("%d ",i);
                    if(i%5==0){
                        System.out.println();
                    }
                }
                System.out.printf("\nChoisir une date: ");
                day = scanner.nextByte();
                if(day < 0 || day > 31){
                    System.out.println("date invalide! retourner au calendrier.");
                    showCalendar();
                }
                showCalendar_day(choice, day);
                break;
            case 4:
            case 6:
            case 9:
            case 11:
                System.out.println();
                for(int i = 1; i <= 30; i++){
                    System.out.printf("%d ",i);
                    if(i%5==0){
                        System.out.println();
                    }
                }
                System.out.printf("\nChoisir une date: ");
                day = scanner.nextByte();
                if(day < 0 || day > 30){
                    System.out.println("date invalide! retourner au calendrier.");
                    showCalendar();
                }
                showCalendar_day(choice, day);
                break;
            case 2:
                System.out.println();
                for(int i = 1; i <= 28; i++){
                    System.out.printf("%d ",i);
                    if(i%5==0){
                        System.out.println();
                    }
                }
                System.out.printf("\nChoisir une date: ");
                day = scanner.nextByte();
                if(day < 0 || day > 28){
                    System.out.println("date invalide! retourner au calendrier.");
                    showCalendar();
                }
                showCalendar_day(choice, day);
                break;
            default:
                System.out.println("option non valide!");
                showCalendar();
        }
    }
    static void showCalendar_day(byte month, byte day){
        System.out.printf("Date: %d/%d/2021\n",day,month);
        System.out.println("Date: dd/mm/yyyy");
        for(int i = 8; i <= 17; i++){
            int r = random.nextInt(16);
            System.out.printf("Heure: %dh00 Disponibilité: %d/15\n",i,r);
        }
        System.out.println("\nChoisir 1 pour choisir un autre date");
        System.out.println("Choisir 2 pour retourner à la page calendrier");
        System.out.println("Choisir 3 pour retourner au Menu principal.");
        byte choice = scanner.nextByte();
        switch (choice){
            case 1:
                showCalendar();
                break;
            case 2:
                calendrier();
                break;
            case 3:
                main_menu();
                break;
            default:
                System.out.println("option non valide! Rertourné au calendrier");
                showCalendar();
        }
    }
    static void takeAppointment(){
        System.out.println("Nouveau rendez-vous\n");
        System.out.printf("Prénom du visiteur: ");
        String firstName = scanner.nextLine();
        System.out.printf("Nom du visiteur: ");
        String LastName = scanner.nextLine();
        System.out.printf("Date de visite (YYYY-MM-DD): ");
        String visitDay = scanner.nextLine();
        System.out.printf("Heure de la visite (HH:MM): ");
        String visitTime = scanner.nextLine();
        System.out.printf("Type de dose (1,2): ");
        byte doseType = scanner.nextByte();
        String reservationNumber = "VAX" + random.nextInt(1000000);
        System.out.println("Numéro de réservation: "+ reservationNumber);
    }
    static void cancelAppointment(){
        System.out.println("Annuler un rendez-vous\n");
        System.out.printf("Entrer le numéro de réservation: ");
        String reservationNumber = scanner.nextLine();
        System.out.printf("Entrer votre numéro d'employé: ");
        String username = scanner.nextLine();
        System.out.printf("Entrer votre mot de passe: ");
        String password = scanner.nextLine();
        System.out.println("Réservation a été annulé avec succès!");
    }



}
