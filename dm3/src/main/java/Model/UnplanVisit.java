package Model;

public class UnplanVisit extends Visit{
    public UnplanVisit() {
    }

    public UnplanVisit(String firstName, String lastName, String dose, String date, String time) {
        super(firstName, lastName, dose, date, time);
    }

    @Override
    public String toString() {
        return "UnPlanVisit{" +
                " Name " + this.getLastName() + " " + this.getFirstName() +
                '}';
    }
}
