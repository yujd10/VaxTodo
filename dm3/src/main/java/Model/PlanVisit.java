package Model;

public class PlanVisit extends Visit{
    private int reservationNumber;

    public PlanVisit() {
    }

    public PlanVisit(String firstName, String lastName, String dose, String date, String time, int reservationNumber) {
        super(firstName, lastName, dose, date, time);
        this.reservationNumber = reservationNumber;
    }

    public int getReservationNumber() {
        return reservationNumber;
    }

    public void setReservationNumber(int reservationNumber) {
        this.reservationNumber = reservationNumber;
    }

    @Override
    public String toString() {
        return "PlanVisit{" +
                "reservationNumber=" + reservationNumber +
                '}';
    }


}
