package Model;

import Controller.VisitController;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

public class Calendar {
    private int periodLenght = 1;
    private int startTime = 8;
    private int endTime = 17;

    public static boolean isPeriodAvailable(String date,String time){
        boolean isAvailable = true;
        Period period = new Period(date,Integer.parseInt(time));
        if(period.isFull()){
            isAvailable = false;
        }
        return isAvailable;
    }

    public List<Period> getAvailablePeriods(DateTime from,DateTime to){return null;}

    public List<Period> getNextFiveDays(){
        //TODO:check https://stackoverflow.com/questions/428918/how-can-i-increment-a-date-by-one-day-in-java for Calendar
        //TODO:check https://stackoverflow.com/questions/5270272/how-to-determine-day-of-week-by-passing-specific-date for Working day
        return null;
    }

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

    public Period nextPeriod(DateTime from){return null;}

    public boolean isDayFull(String date){
        boolean filled = true;
        for(int i = 8;i<17;i++){
            Period period = new Period();
            period.setDate(date);
            period.setStart(i);
            if(!period.isFull()){filled = false;}
        }
        return filled;
    }

    public Visit makeRDV(Visit visit){
        Period period = new Period();
        period.setDate(visit.getDatetime().getDate());
        period.setStart(Integer.parseInt(visit.getDatetime().getTime()));
        if(!period.isFull()) period.addVisit(visit);
        return visit;
    }

    public List<String> nextFiveDays(){GregorianCalendar cal = new GregorianCalendar();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        int day = cal.get(GregorianCalendar.DAY_OF_MONTH);
        int counter = 0;
        List<String> dates = new ArrayList<>();
        while(counter<5){
            cal.set(GregorianCalendar.DAY_OF_MONTH, day);
            if(cal.get(GregorianCalendar.DAY_OF_WEEK)!=GregorianCalendar.SATURDAY
                    &&cal.get(GregorianCalendar.DAY_OF_WEEK)!=GregorianCalendar.SUNDAY){
                Date date = cal.getTime();
                System.out.println(sdf.format(date));
                dates.add(sdf.format(date));
                counter++;}
            day++;}
        return dates;
    }


}
