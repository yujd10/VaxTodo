package Model;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class testOutput {
    public static void main(String[] args) {
        Period period = new Period();
        List<Visit> visits = new ArrayList<>();
        visits.add(new Visit("555","aaa","ggg","1","2077","13:30"));
        visits.add(new Visit("666","aaa","ggg","1","2077","13:30"));
        visits.add(new Visit("888","aaa","ggg","1","2077","13:30"));
        visits.add(new Visit("342","aaa","ggg","1","2077","13:30"));
        period.setVisits(visits);
        System.out.println(period.showVisits());
//        visits.add(new Visit("555","aaa","ggg","1","2077","13:30"));
//        visits.add(new Visit("666","aaa","ggg","1","2077","13:30"));
//        visits.add(new Visit("777","aaa","ggg","1","2077","13:30"));
//        visits.add(new Visit("888","aaa","ggg","1","2077","13:30"));
//        visits.add(new Visit("999","aaa","ggg","1","2077","13:30"));
//
//        Visit.addNewVisit("555","aaa","ggg","1","2077","13:30");
//        Visit.showCurrentVisits();



    }
}
