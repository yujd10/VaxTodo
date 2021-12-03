package Model;

import Controller.Controller;
import View.LoginView;
import View.EmployeeView;
import View.VolunteerView;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Router {
    private Router instance;
    private EmployeeView employeeView = new EmployeeView();
    private VolunteerView volunteerView = new VolunteerView();
    LoginView loginView = new LoginView();
    public Router getInstance(){
        return this.instance;
    }

    public void init(){
        loginPage();
    }

    public void loginPage(){
        User user = loginView.loginPage();
        if(user == null){
            loginPage();
        }else{
            String role = user.getRole();
            if(role.equals("EMPLOYEE")){
                employeeMain(this);
            } else {
                volunteerMain(this);
            }
        }
    }

    public void employeeMain(Router router){
        employeeView.showEmployeeMenu(router);
    }

    public void volunteerMain(Router router){
        volunteerView.showVolunteerMenu(router);
    }

    public void manageVisitor(Router router){
        employeeView.showManageVisitorMenu(router);
    }

    public void followUpPage(){

    }

    public void calendarPage(){

    }

    public void makeAppointment(){

    }

    public void surveyPage(){

    }
    public void showVisitorList(){

    }
}
