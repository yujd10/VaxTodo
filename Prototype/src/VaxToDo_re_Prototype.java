import java.io.Console;
import java.util.Scanner;

public class VaxToDo_re_Prototype {
    static Scanner scanner = new Scanner(System.in);
    public static void main(String[] args) {
//        login();
//        main_menu();
        calendrier();
//        liste_visiteur();
//        liste_benevole();
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
        System.out.println("+\t\t3. Modifier un rendez-vous\t\t+");
        System.out.println("+\t\t4. Annuler un rendez-vous\t\t+");
        System.out.println("+\t\t5. Retour à la page principal\t+");
        System.out.println("+\t\t6. Quitter\t\t\t\t\t\t+");
        System.out.println("+++++++++++++++++++++++++++++++++++++++++");
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
}
