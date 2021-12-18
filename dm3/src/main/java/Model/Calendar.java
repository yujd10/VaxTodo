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
        List<String> periods =nextNDays(5,next);
        return periods;
    }

    public List<Period> getAvailablePeriods(DateTime from,DateTime to){return null;}

    public void sendNotification(DateTime dateTime){
        VisitController vc = new VisitController();
        Person person = null;
        List<Visit> visits = vc.read();
        for(Visit visit:visits){
            if(visit.isWithRDV() && visit.getDatetime().getDate().equals(dateTime.getDate())){
                person = person.search(visit.getFirstName()+":"+visit.getLastName());
                String email = person.getEmailAddress();
                System.out.println("Rappel for "+visit.getFirstName()+" "+visit.getLastName() + " at " +dateTime.getDate() + " is send to email address :" + email +" ! ");
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
        }
        return list;
    }

    public static List<String> nextNDays(int n,int next){
        GregorianCalendar cal = new GregorianCalendar();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        List<String> dates = new ArrayList<>();

        int day = cal.get(GregorianCalendar.DAY_OF_MONTH)+next;
        int counter = 0;

        while(counter<n){
            cal.set(GregorianCalendar.DAY_OF_MONTH, day);
            if(cal.get(GregorianCalendar.DAY_OF_WEEK)!=GregorianCalendar.SATURDAY
                    &&cal.get(GregorianCalendar.DAY_OF_WEEK)!=GregorianCalendar.SUNDAY){
                Date date = cal.getTime();
                if(!isDayFull(sdf.format(date)))
                {
                    System.out.println((counter+1)+". "+sdf.format(date));
                    dates.add(sdf.format(date));
                }
                counter++;
            }
            day++;
        }
        return dates;
    }


    final static String DATE_FORMAT = "yyyy-MM-dd";
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
