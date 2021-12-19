package Controller;

import java.util.List;
import Model.Calendar;

public class CalendarController {
    Calendar calendar = null;

    public List<Integer> getAvailablePeriod(String date){
        return Calendar.periodsAvailable(date);
    }

    public boolean isValidDate(String date){
        return Calendar.isDateValid(date);
    }
}
