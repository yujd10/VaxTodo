package Model;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class DateTime {
    private String date;
    private String time;

    public DateTime() {
    }

    public DateTime(String date, String time) {
        if(isDateValid(date)){
        this.date = date;
        this.time = time;}
    }

    final static String DATE_FORMAT = "YYYY-MM-DD";

    public static boolean isDateValid(String date)
    {
        try {
            DateFormat df = new SimpleDateFormat(DATE_FORMAT);
            df.setLenient(false);
            df.parse(date);
            return true;
        } catch (ParseException e) {
            return false;
        }
    }

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
