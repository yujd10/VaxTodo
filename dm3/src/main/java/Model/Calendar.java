package Model;

import Controller.VisitController;

import java.util.ArrayList;
import java.util.List;

public class Calendar {
    private int periodLenght = 1;
    private int startTime = 8;
    private int endTime = 17;

    public boolean isPeriodAvailable(DateTime dateTime){
        return true;
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

    public Visit makeRDV(Visit visit){
        Period period = new Period();
        period.setDate(visit.getDatetime().getDate());
        period.setStart(Integer.parseInt(visit.getDatetime().getTime()));
        if(!period.isFull()) period.addVisit(visit);
        return visit;
    }

    public static void getCalendar(){}


}
