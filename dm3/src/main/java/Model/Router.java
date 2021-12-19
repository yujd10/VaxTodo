/**
 * @author Xiang Long Gao
 *
 * Classe pour controller la page de vue.
 */
package Model;

import View.LoginView;
import View.EmployeeView;
import View.VolunteerView;

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

    /**
     * Fonction pour la page de login et afficher le menu principal selon le role d'utilisateur connecté.
     */
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

    public void managePerson(Router router, String choice){
        employeeView.showManagePersonMenu(router, choice);
    }

    public void followUpPage(Router router){ employeeView.showSuiviMenu(router);}

    public void calendarPage(Router router){ employeeView.calendarOptionMenu(router);}

    public void showVisitorList(Router router){
        volunteerView.showVisitorList(router);
    }

    public void makeAppointment(Router router){volunteerView.volunteerMakeAppointment(router);}


}
