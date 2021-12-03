package Model;

public class Visit {
    private String reservationNumber;
    private String firstName;
    private String lastName;
    private String dose;
    private DateTime datetime;

    public Visit() {
    }

    public Visit(String reservationNumber, String firstName, String lastName, String dose, DateTime datetime) {
        this.reservationNumber = reservationNumber;
        this.firstName = firstName;
        this.lastName = lastName;
        this.dose = dose;
        this.datetime = datetime;
    }

    public String getReservationNumber() {
        return reservationNumber;
    }

    public void setReservationNumber(String reservationNumber) {
        this.reservationNumber = reservationNumber;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getDose() {
        return dose;
    }

    public void setDose(String dose) {
        this.dose = dose;
    }

    public DateTime getDatetime() {
        return datetime;
    }

    public void setDatetime(DateTime datetime) {
        this.datetime = datetime;
    }
    public void setDatetime(String date,String time) {
        this.datetime.setDate(date);
        this.datetime.setTime(time);
    }
}
