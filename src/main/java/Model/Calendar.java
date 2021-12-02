package Model;

import java.time.Period;

public class Calendar {
    private static Calendar instance;

    public static Calendar getInstance(){
        return null;
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
}
