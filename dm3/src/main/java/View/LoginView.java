package View;

import Controller.LoginController;
import Model.User;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class LoginView extends View{
    LoginController loginController = new LoginController();
    public User loginPage(){
        try{
            System.out.printf("Please login: username:password -> ");
            String input[] = reader.readLine().split(":");
            boolean success = loginController.login(input[0], input[1]);
            if(!success){
                return null;
            }

        } catch (IOException | ArrayIndexOutOfBoundsException e){
            System.out.println("There's error in your input, please retry.");
            loginPage();
        }
        return loginController.getLoggedUser();
    }
}
