package Model;

/**
 * La classe pour une composition de date et temps, utilisé comme un attribut des visites.
 */
public class DateTime {
    private String date;
    private String time;

    public DateTime() {
    }

    public DateTime(String date, String time) {
        //De voir si la date est valide
        if(Calendar.isDateValid(date)){
        this.date = date;
        this.time = time;}
    }

    //Getters and Setters
    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
