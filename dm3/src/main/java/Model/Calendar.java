package Model;

import Controller.VisitController;

import java.sql.Time;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

/**
 * Ce classe Calendrier sert a traiter les actions concernant au date et au temps.
 */
public class Calendar {

    /**
     * Consulter les prochaines 5 jours et peut choisir avancer dans le calendrier.
     * @param next Combien de jours a l'avance que vous voulez consuilter
     * @return
     */
    public List<String> consultationOfCalendar(int next){
        List<String> periods =nextNDays(next);
        return periods;
    }

    /**
     * La simulation de envoyer un rappel pour les rendez-vous dans le prochain 48 heures
     * @param date le date des rendez-vous que vous voulez rappeler
     */
    public void sendNotification(String date){
        VisitController vc = new VisitController();
        Person person = new Person();
        List<Visit> visits = vc.read();
        if(visits.size() == 0){
            System.out.println("Pas de visite trouvée.");
            return;
        }
        for(Visit visit:visits){
            if(visit.isWithRDV() && visit.getDatetime().getDate().equals(date)){
                person = person.search(visit.getFirstName()+":"+visit.getLastName());
                if(person != null){
                    String email = person.getEmailAddress();
                    System.out.println("Rappel for "+visit.getFirstName()+" "+visit.getLastName() + " at " +date + " is send to email address :" + email +" ! ");
                }
            }
        }
    }

    /**
     * Fonction permet de verifier si un jour choisi est rempli ( Pas disponible )
     * @param date le jour que vous voulez checker
     * @return Si le jour que vous avez choisi est rempli
     */
    public static boolean isDayFull(String date){
        boolean filled = true;
        for(int i = 8;i<=17;i++){
            Period period = new Period();
            period.setDate(date);
            period.setStart(i);
            if(!period.isFull()){filled = false;}
        }
        return filled;
    }

    /**
     * Fonction permet de trouver les period disponible dans un jour, cela est une liste de Integer qui est le type
     * de l'attribut "start" de la classe de Period.
     * @param date Date de period
     * @return List de periods qui sont encore disponible
     */
    public static List<Integer> periodsAvailable(String date){
        List<Integer> list = new ArrayList<>();
        for(int i = 8;i<=17;i++){
            Period period = new Period();
            period.setDate(date);
            period.setStart(i);
            if(!period.isFull()){list.add(i);}
            else {
                System.out.println("Cette période est plein. Veuillez choisir un autre.");
            }
        }
        return list;
    }

    /**
     *
     * @param next  Combien de jours a l'avance que vous voulez consulter
     * @return Une liste de date (String) des prochains 5 jours
     */
    public static List<String> nextNDays(int next){
        List<String> dates = new ArrayList<>();
        java.util.Calendar cal = java.util.Calendar.getInstance();
        SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");
        int counter = 0;
        cal.add(java.util.Calendar.DATE, next-1);
        while(counter < 7){
            cal.add(java.util.Calendar.DATE, 1);
            String date = format1.format(cal.getTime());
            if(!isDayFull(date)){
                if(cal.get(java.util.Calendar.DAY_OF_WEEK)!=7
                        &&cal.get(java.util.Calendar.DAY_OF_WEEK)!=1)
                {
                dates.add(date);
                }
            counter++;
            }
        }
        for(int i = 0; i < dates.size(); i++){
            System.out.println(i+1 + ". " +dates.get(i).toString());
        }
        return dates;
    }

    //Date Formatter
    final static String DATE_FORMAT = "yyyy-MM-dd";

    /**
     * Fonction permet de calculer le nombre de jours entre deux dates en utilisant les String de date
     * Principalement pour calculer le temps entre deux doses
     * @param start startDate
     * @param end endDate
     * @return Le nombre de jours entre les deux dates
     */
    public static long getDayCount(String start, String end) {
        DateFormat df = new SimpleDateFormat(DATE_FORMAT);
        long diff = -1;
        try {
            Date dateStart = df.parse(start);
            Date dateEnd = df.parse(end);
            diff = Math.round((dateEnd.getTime() - dateStart.getTime()) / (double) 86400000);
        } catch (Exception e) {
        }
        return diff;
    }

    /**
     * Fonction permet de verifier si un String de date est valide:
     * 1. Sous forme de yyyy-MM-dd
     * 2. Le jour existe dans la vraie vie ( d'eviter les date comme 2022-02-29 )
     * @param date
     * @return
     */
    public static boolean isDateValid(String date)
    {
        try {
            DateFormat df = new SimpleDateFormat(DATE_FORMAT);
            df.setLenient(false);
            df.parse(date);
            return true;
        } catch (ParseException e) {
            return false;
        }
    }

    /**
     * Fonction permet de checker si un visiteur est en retard pendant confirmer son rendez-vous
     * en calculant le temps entre deux temps(currentTime et startTime pour le rendez-vous)
     * @param start le temps pour rendez-vous
     * @return si un visiteur est en retard
     */
    public static boolean ifLate(int start){
        boolean late = false;
        DateTime dt = new DateTime();
        double currentTime = LocalDateTime.now().getHour()+(LocalDateTime.now().getHour())*0.017;;;
        double difference = (currentTime-(double) start)*60;
        if(difference>15){
            late = true;
        }
        return late;
    }
}
