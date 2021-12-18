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
        System.out.println(dates);
        return dates;
    }


    final static String DATE_FORMAT = "yyyy-MM-dd";

    public static long getDayCount(String start, String end) {
        DateFormat df = new SimpleDateFormat(DATE_FORMAT);
        long diff = -1;
        try {
            Date dateStart = df.parse(start);
            Date dateEnd = df.parse(end);

            //time is always 00:00:00, so rounding should help to ignore the missing hour when going from winter to summer time, as well as the extra hour in the other direction
            diff = Math.round((dateEnd.getTime() - dateStart.getTime()) / (double) 86400000);
        } catch (Exception e) {
            //handle the exception according to your own situation
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
