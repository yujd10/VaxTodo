package Model;

import Controller.PersonController;
import Controller.VisitController;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class VisitTest {
    private VisitController visitController;

    @BeforeEach
    public void setPerson(){
        this.visitController = new VisitController();
    }

    @Test
    void testAddRDV() {
        Period period = new Period("2021-12-19",15);
        String firstName1 = "Kirk";
        String lastName1 = "Hanmmet";
        String dose1 = "1";
        boolean spontanee1 = false;
        period.addVisit(firstName1,lastName1,dose1,spontanee1);
        Visit visit1 = new Visit();
        boolean[] success = new boolean[7];
        boolean pass = true;
        List<Visit> visits = visitController.read();
        for(Visit visit:visits){
            if(visit.getFirstName().equals(firstName1)
                    &&visit.getLastName().equals(lastName1)){
                visit1 = visit;
            }
        }
        success[0]=visit1.getFirstName().equals(firstName1);
        success[1]=visit1.getLastName().equals(lastName1);
        success[2]=!visit1.getReservationNumber().equals(null);
        success[3]=visit1.isWithRDV();
        success[4]=visit1.getDose().equals(dose1);
        success[5]=visit1.getDatetime().getDate().equals(period.getDate());
        success[6]=period.getStart()==Integer.parseInt(visit1.getDatetime().getTime());
        for(boolean b : success){
            if (b == false){
                pass = false;
            }
        }
        assertTrue(pass);
    }

    @Test
    void testAddVisite() {
        Period period = new Period("2021-12-20",16);
        String firstName1 = "James";
        String lastName1 = "Hefield";
        String dose1 = "1";
        boolean spontanee1 = true;
        period.addVisit(firstName1,lastName1,dose1,spontanee1);
        Visit visit1 = new Visit();
        boolean[] success = new boolean[7];
        boolean pass = true;
        List<Visit> visits = visitController.read();
        for(Visit visit:visits){
            if(visit.getFirstName().equals(firstName1)
                    &&visit.getLastName().equals(lastName1)){
                visit1 = visit;
            }
        }
        success[0]=visit1.getFirstName().equals(firstName1);
        success[1]=visit1.getLastName().equals(lastName1);
        success[2]=(visit1.getReservationNumber()==null)? true : false;
        success[3]=!visit1.isWithRDV();
        success[4]=visit1.getDose().equals(dose1);
        success[5]=visit1.getDatetime().getDate().equals(period.getDate());
        success[6]=period.getStart()==Integer.parseInt(visit1.getDatetime().getTime());
        for(boolean b : success){
            if (b == false){
                pass = false;
            }
        }
        assertTrue(pass);
    }

    @Test
    void testAddVisitFail() {
        Period period = new Period("2022-12-28",8);
        for(int i=0;i<15;i++){
            period.addVisit("Dave","Mustain","1",true);
        }
        period.addVisit("Eric","Clapton","1",true);
        Visit visit1=visitController.findVisit("Eric","Clapton");
        boolean[] success = new boolean[5];
        boolean pass = true;
        success[0]=visit1.getFirstName()==null;
        success[1]=visit1.getLastName()==null;
        success[2]=visit1.getReservationNumber()==null;
        success[3]=visit1.getDose()==null;
        success[4]=visit1.getDatetime()==null;
        for(boolean b : success){
            if (b == false){
                pass = false;
            }
        }
        assertTrue(pass);
    }

    @Test
    void testConfirmeVisiteSpontanee() {
        boolean[] success = new boolean[5];
        boolean pass = true;
        Period period = new Period("2022-02-23",10);
        period.addVisit("Dave","Mustain","1",true);
        Visit visit1=visitController.findVisit("Dave","Mustain");
        success[0]= visit1.getFirstName().equals("Dave");
        success[1]= visit1.getLastName().equals("Mustain");
        success[2]= !visit1.isWithRDV();
        success[3]= !visit1.isConfirmed();
        visit1.confirm();
        success[4]= visit1.isConfirmed();
        for(boolean b : success){
            if (b == false){
                pass = false;
            }
        }
        assertTrue(pass);
    }

    @Test
    void testConfirmRDVFail() {

    }
}