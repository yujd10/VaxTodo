package package1;
import package1.User;
import package1.UserRole;
public class Benevole extends User {
    public Benevole() {}

    public Benevole(UserRole role, String id, String password, String username, String birthday, String telephone, String address, String postalCode, String city) {
        super(role, id, password, username, birthday, telephone, address, postalCode, city);
    }
}
