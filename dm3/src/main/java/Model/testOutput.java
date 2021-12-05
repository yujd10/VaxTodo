package Model;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class testOutput {
    public static void main(String[] args) {
//        Calendar cal = new Calendar("2020-09-10");
        WorkingDay cal = new WorkingDay("2020-09-10");
        System.out.println(cal.isPeriodFull(8));;
//        cal.gotoPeriod(8).AddVisit(new PlanVisit("Jiadi","Yu","1",cal.getDate().getDate(),"8",12345));
        cal.gotoPeriod(8).AddVisit(new PlanVisit("Jiadi","Yu","1",cal.getDate().getDate(),"8",12345));

//        System.out.println(cal.gotoPeriod(8).getVisits().size());
//        System.out.println(cal.gotoPeriod(8).toString());
        System.out.println(cal.printTheCalender());

    }
}
