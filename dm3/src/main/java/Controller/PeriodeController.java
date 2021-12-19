package Controller;

import Model.Period;

public class PeriodeController {
    Period period;
    public void addVisit(int time, String firstName, String lastName, String dose, boolean spontanee){
        period = new Model.Period(null, time);
    }
}
