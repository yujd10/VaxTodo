package Model;

import Controller.VisitController;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class testOutput {
    public static void main(String[] args) throws ParseException {
//        Person person = new Person("123456789012","GG","QQ","1234567890","jjjjjjjj@jj.com","1999-09-09",false);
//
//        Form form = new Form(person,"2000-09-09",new Vaccine("Pfizer","123","123"),true,true,true,true,true,true);
//        Form form2 = null;
////        form.addNewForm(form);
//
//        List<Form> forms = form.read();
//        for(Form form1:forms){
//            if(form1.getVisitor().getFirstName().equals("GG")){
//                form2=form1;
//            }
//        }
////        System.out.println(form2.getVisitor().getFirstName());
//        System.out.println(form2.toString());
//        Period period = new Period("2020-09-10",10);
//        period.addVisit(new Visit(false,"Kiko","Loureino","1","",""));
//        System.out.println(Calendar.isDayFull("2020-09-10"));;
//        System.out.println(Calendar.periodsAvailable("2020-09-10").toString());;
//        VisitController vc = new VisitController();
//        vc.confirmerVisitSpontane("Jiadi","Yu");
//        Calendar cal = Calendar.getInstance();
//        cal.add(java.util.Calendar.DATE,0);
//        cal.consultationOfCalendar(5);
//        String date = "2021-12-17";
//        String nextDate = "2021-12-19";
//        SimpleDateFormat dtf =new SimpleDateFormat("yyyy-MM-dd");
//        Date oldDate = dtf.parse(date);
//        Date newDate = dtf.parse(nextDate);
//        long diff = newDate.getTime() - oldDate.getTime();
////        System.out.println(diff/ (1000*60*60*24));
//        Calendar.getDate1();
//        Model.Calendar.nextNDays(5,30);
//        System.out.println(cal.get(Calendar.DAY_OF_WEEK));
        VisitController vc = new VisitController();
        Visit visit = new Visit();
        visit = vc.findVisitByNumber(316640);
}}
