package Model;

import java.util.ArrayList;
import java.util.List;

public class Calendar {
    private DateTime date;
    private static List<Model.Period> periods;

    public Calendar() {
    }

    public Calendar(String date) {
        this.date = new DateTime(date,null);
        this.periods = new ArrayList<>();
        for(int hour=8;hour<=17;hour++){
            this.periods.add(new Period(date, hour));
        }
    }

    public boolean isPeriodFull(int time){
        boolean isFull = true;
        for(Model.Period period:periods){
            if(period.getStart() == time){
                if(!period.isFull()){
                    isFull = false;
                }
            }
        }
        return isFull;
    }

    public List<Period> getAvailablePeriods(int from, int to){
        List<Period> availablePeriods = new ArrayList<>();
        for(Period period:periods){
            if(period.getStart()>=from && period.getStart()<=to){
            if(!period.isFull()){
                availablePeriods.add(period);
            }}
        }
        return availablePeriods;
    }

    public String printTheCalender(DateTime date){
        return  "1";
    }

    public Period nextPeriod(){
        return null;
    }

    public DateTime getDate() {
        return date;
    }

    public void setDate(DateTime date) {
        this.date = date;
    }

    public List<Period> getPeriods() {
        return periods;
    }

    public void setPeriods(List<Period> periods) {
        this.periods = periods;
    }
}
