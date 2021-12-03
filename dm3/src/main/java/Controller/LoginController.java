package Controller;

import Model.User;

import java.util.List;
import java.util.regex.Pattern;

public class LoginController extends Controller{

    public User login(String username, String password){
        boolean verified = verifyLogin(username, password);
//        if(!verified){
//            System.out.println("Username or password error. Please try again.");
//            return null;
//        }
        List<User> currentUsers = User.readData();
        for (User user : currentUsers) {
            if (user.getUsername().equals(username) && user.getPassword().equals(password)) {
                super.setLoggedUser(user);
                System.out.println("Login successfully!");
                return user;
            }
        }
        System.out.println("User not found, please try again.");
        return null;
    }

    public boolean verifyLogin(String username, String password){
        String pattern = "(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}";
        if(Pattern.matches("\\d{9}",username) && Pattern.matches(pattern, password)) {
            return true;
        }
        return false;
    }

    public boolean logout(User user){
        return false;
    }
}
