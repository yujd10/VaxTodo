package Model;

public class Visit {
    private Integer reservationNumber;
    private Person personVisiting;
    private String dose;
    private DateTime datetime;

    public Visit() {
    }

    public Visit(Integer reservationNumber, Person personVisiting, String dose, DateTime datetime) {
        this.reservationNumber = reservationNumber;
        this.personVisiting = personVisiting;
        this.dose = dose;
        this.datetime = datetime;
    }

    public Integer getReservationNumber() {
        return reservationNumber;
    }

    public void setReservationNumber(Integer reservationNumber) {
        this.reservationNumber = reservationNumber;
    }

    public Person getPersonVisiting() {
        return personVisiting;
    }

    public void setPersonVisiting(Person personVisiting) {
        this.personVisiting = personVisiting;
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
}
