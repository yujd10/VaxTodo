package Model;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class Calendar {
    private Calendar instance;
    private DateTime date;
    private static List<Model.Period> periods;

    public Calendar() {
    }

    public Calendar(DateTime date) {
//        this.instance = instance;

        this.date = date;
        this.periods = new ArrayList<>();
        for(int i=8;i<=17;i++){
            this.periods.add(new Period(date.getDate(), i,i+1));
        }
    }

    public Calendar getInstance(){
        return instance;
    }

    public boolean isPeriodFull(){
        boolean isFull = true;
        for(Model.Period period:periods){
            if(!period.isFull()){isFull = false;}
        }
        return isFull;
    }

    public Period[] getAvailablePeriods(String from, String to){
        return null;
    }

    public Period nextPeriod(){
        return null;
    }

    public void setInstance(Calendar instance) {
        this.instance = instance;
    }

    public DateTime getDate() {
        return date;
    }

    public void setDate(DateTime date) {
        this.date = date;
    }

    public List<Period> getPeriods() {
        return periods;
    }

    public void setPeriods(List<Period> periods) {
        this.periods = periods;
    }
}
