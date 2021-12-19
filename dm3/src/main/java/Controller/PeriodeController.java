package Controller;

import Model.Period;

public class PeriodeController {
    Period period;
    public void addVisit(String date, int time, String firstName, String lastName, String dose, boolean spontanee){
        period = new Period(date, time);
        period.addVisit(firstName,lastName,dose,spontanee);
        System.out.println("visite ajoutée avec succès!");
    }
}
