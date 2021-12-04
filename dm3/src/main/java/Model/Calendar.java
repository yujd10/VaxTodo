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

//    public static void createCalendar(String[] visitInfo, String dateTime){
//        List<Visit> currentVisits = readData();
//        Visit visit = new Visit(visitInfo[0], visitInfo[1],visitInfo[2], visitInfo[3]);
//        String[] dateTime_Split = dateTime.split(";");
//        visit.datetime = new DateTime(dateTime_Split[0],dateTime_Split[1]);
//        currentVisits.add(visit);
//        save(currentVisits);
//        System.out.println("Visit for "+ visit.firstName+" "+visit.lastName + " added");
//    }

    public static void save(List<Calendar> currentCalendar) {
        JSONArray calendarList = new JSONArray();
        JSONArray listPeriod = new JSONArray();
        for(Calendar c : currentCalendar){
            JSONObject calendarDetails = new JSONObject();
            calendarDetails.put("date", c.getDate().getDate());
            for(Period period:c.getPeriods()){
                listPeriod.add(new Gson().toJson(period));
            }
            calendarDetails.put("periods",listPeriod);
            calendarList.add(calendarDetails);
        }
        //Write JSON file
        try (FileWriter file = new FileWriter("calendar.json")){
            file.write((calendarList.toJSONString()));
            file.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static List<Calendar> readData() {
        //JSON parser object to parse read file
        JSONParser jsonParser = new JSONParser();
        List<Calendar> results = new ArrayList<>();

        try (FileReader reader = new FileReader("calendar.json")) {

            //Read JSON file
            Object obj = jsonParser.parse(reader);

            JSONArray calendarList = (JSONArray) obj;
            if(calendarList.size() == 0 ){
                return results;
            }

            calendarList.forEach(person -> {
                results.add(parseCalenderObject((JSONObject) person));
            });

        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }

        return results;
    }

    private static Calendar parseCalenderObject(JSONObject calendar1) {

        Calendar calendar = new Calendar();
        calendar.date = new DateTime();
        periods = new ArrayList<>();
        JSONArray periodJSON = (JSONArray) calendar1.get("periods");
        //Get Person object within list
        calendar.date.setDate((String) calendar1.get("date"));
        for(int i=0;i<periodJSON.size();i++){
            Period period = new Gson().fromJson((JSONObject) periodJSON.get(i),Period.class);
            periods.add(period);
        }
        calendar.setPeriods(periods);
        return calendar;
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
