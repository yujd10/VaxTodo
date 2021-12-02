package package1;


import static package1.UserRole.EMPLOYEE;

public class test {
    public static void main(String[] args) {
        UserRespiratory.addAccount(new User(EMPLOYEE,"GG","gg","gg","aa","aa","gg","gg","gg","gg"));
//        System.out.println(UserRespiratory.ifUserExist("gg","aa"));;
//        System.out.println(UserRespiratory.read().get(1).getLastName());
    }
}
