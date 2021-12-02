package package1;

public class Visite {
    private String reservationNumber;
    private String firstName;
    private String lastName;
    private String visitDate;
    private String visitHour;
    private Integer typeOfDose;

    public Visite() {
    }

    public Visite(String reservationNumber, String firstName, String lastName, String visitDate, String visitHour, Integer typeOfDose) {
        this.reservationNumber = reservationNumber;
        this.firstName = firstName;
        this.lastName = lastName;
        this.visitDate = visitDate;
        this.visitHour = visitHour;
        this.typeOfDose = typeOfDose;
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

    public String getVisitDate() {
        return visitDate;
    }

    public void setVisitDate(String visitDate) {
        this.visitDate = visitDate;
    }

    public String getVisitHour() {
        return visitHour;
    }

    public void setVisitHour(String visitHour) {
        this.visitHour = visitHour;
    }

    public Integer getTypeOfDose() {
        return typeOfDose;
    }

    public void setTypeOfDose(Integer typeOfDose) {
        this.typeOfDose = typeOfDose;
    }
}
