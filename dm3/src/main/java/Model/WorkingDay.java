package Model;

import java.util.ArrayList;
import java.util.List;

public class WorkingDay {
    private DateTime date;
    private static List<Period> periods;

    public WorkingDay() {
    }

    public WorkingDay(String date) {
        this.date = new DateTime(date,null);
        this.periods = new ArrayList<>();
        for(int hour=8;hour<=17;hour++){
            this.periods.add(new Period(date, hour));
        }
    }

    public boolean isPeriodFull(int time){
        boolean isFull = true;
        for(Model.Period period:periods){if(period.getStart() == time){if(!period.isFull()){isFull = false;}}}
        return isFull;
    }

    public Period gotoPeriod(int time){
        Period period1 = null;
        for(Period period:periods){if(period.getStart() == time){period1 = period;}}
        return period1;
    }

    public List<Period> getAvailablePeriods(int from, int to){
        List<Period> availablePeriods = new ArrayList<>();
        for(Period period:periods){
            if(period.getStart()>=from && period.getStart()<=to){
                if(!period.isFull()){
                    availablePeriods.add(period);}
            }
        }
        return availablePeriods;}

    public String printTheCalender(){
        return periods.toString();
    }

    public Period nextPeriod(Visit visit){
        return gotoPeriod(Integer.parseInt(visit.getDatetime().getTime())+1);
    }

    public DateTime getDate() {
        return date;
    }

    public void setDate(DateTime date) {
        this.date = date;
    }

    public static List<Period> getPeriods() {
        return periods;
    }

    public static void setPeriods(List<Period> periods) {
        WorkingDay.periods = periods;
    }
}

