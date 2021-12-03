package Model;

import java.time.Period;

public class Calendar {
    private static Calendar instance;
    private Period[] periods;

    public Calendar() {
    }

    public Calendar(Period[] periods) {
        this.periods = new Period[10];
    }

    public boolean isPeriodAvailable(){
        return false;
    }

    public Period[] getAvailablePeriods(String from, String to){
        return null;
    }

    public Period nextPeriod(){
        return null;
    }

    public static Calendar getInstance() {
        return instance;
    }

    public static void setInstance(Calendar instance) {
        Calendar.instance = instance;
    }

    public Period[] getPeriods() {
        return periods;
    }

    public void setPeriods(Period[] periods) {
        this.periods = periods;
    }
}
