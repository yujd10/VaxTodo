package Model;

import Controller.VisitController;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class testOutput {
    public static void main(String[] args) {
        Period period = new Period("2020-09-10",10);
        period.addVisit(new Visit(false,"Kiko","Yu","1","2020-09-10","10"));
        period.addVisit(new Visit(false,"Jiadi","Yu","1","2020-09-10","10"));
        
        VisitController vc = new VisitController();
        vc.showCurrentVisits();
////
////        System.out.println(period.isFull());
//        System.out.println(java.time.LocalDate.now());
//        Calendar calendar = Calendar.getInstance();

    }
}