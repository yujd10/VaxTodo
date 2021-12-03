package View;

import Controller.LoginController;
import Model.User;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class LoginView extends View{

    public User loginPage(){
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        LoginController loginController = new LoginController();

        try{

            System.out.printf("Please login: username:password -> ");
            String input[] = reader.readLine().split(":");
            loginController.setLoggedUser(loginController.login(input[0], input[1]));

        } catch (IOException e){
            System.out.println("There's error in your input, please retry.");
            loginPage();
        }
        return loginController.getLoggedUser();
    }
}
