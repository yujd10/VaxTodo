package Model;

import Controller.VisitController;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

public class Calendar {
    private int periodLenght = 1;
    private int startTime = 8;
    private int endTime = 17;

    public List<String> consultationOfCalendar(int next){
        List<String> periods =nextNDays(next);
        return periods;
    }

    public List<Period> getAvailablePeriods(DateTime from,DateTime to){return null;}

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


    final static String DATE_FORMAT = "yyyy-MM-dd";

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
}
