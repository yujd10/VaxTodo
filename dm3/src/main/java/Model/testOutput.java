package Model;

import java.util.ArrayList;
import java.util.List;

public class testOutput {
    public static void main(String[] args) {
        Period period = new Period();
        period.addVisit(new UnplanVisit("Jiadi","Yu","1","2020","999"));
        period.addVisit(new PlanVisit("Jiadi","Yu","1","2020","229",12345));
        period.addVisit(new PlanVisit("Jiadi","Yu","1","2020","349",12345));
        period.addVisit(new PlanVisit("KIKO","Santana","1","2020","349",12345));

        System.out.println(period.read().get(0).getClass());
    }
}
