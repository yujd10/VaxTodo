package Controller;

import Model.*;

import java.util.List;

public class AdminController extends Controller{
    Form form = new Form();
    public Form getForm(String firstName, String lastName){
        return form.findForm(firstName, lastName);
    }

    public void updateForm(boolean isFirstDose, String visitDate){
        form.change(isFirstDose, visitDate);
    }

    public Form createForm(Person person, String today, Vaccine vaccine, String numeroDeAssurance,boolean ifFirst, boolean covided, boolean symptom, boolean allergies,boolean proccededAutre){
        return new Form(person,today,vaccine,numeroDeAssurance,ifFirst,covided,symptom,allergies,proccededAutre);
    }

    public void addFormToJSON(Form form){
        form.addNewForm(form);
    }

    public void sendReminder(String date){
        Calendar calendar = new Calendar();
        calendar.sendNotification(date);
    }
}
