package package1;

import java.util.List;

public class LoginController {
	public static User loggedInUser = null;

	public static void login(String username, String pass) {
		List<User> currentUsers = UserRespiratory.read();
		for (User user : currentUsers) {
			if (user.getUsername().equals(username) && user.getPassword().equals(pass)) {
				loggedInUser = user;
				System.out.println("Login successfully as a "+ user.getRole() + "!");
				return;
			}
		}
			System.out.println("Username or Password is not valide, pleas retry !");

	}

	public static void logout() {
		loggedInUser = null;
	}
}