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

    public Router getInstance(){
        return this.instance;
    }

    public void init(){
        LoginView loginView = new LoginView();
        User loggedUser = loginView.loginPage();
        String role = loggedUser.getRole();
        if(role.equals("EMPLOYEE")){
            employeePage();
        } else {
            volunteerPage();
        }
    }

    public void employeePage(){
        EmployeeView employeeView = new EmployeeView();
        employeeView.showEmployeeMenu();
    }

    public void volunteerPage(){
        VolunteerView volunteerView = new VolunteerView();
        volunteerView.showVolunteerMenu();

    }
}
