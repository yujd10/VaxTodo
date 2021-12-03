package Controller;

import Model.DateTime;
import Model.Visit;

public class testControllers {
    public static void main(String[] args) {
        VisitController.addVisit(new Visit(1234,"Mike","Jordan","2",new DateTime()));
    }
}
