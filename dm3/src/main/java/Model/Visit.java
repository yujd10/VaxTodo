package Model;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Visit {
    private String reservationNumber;
    private String firstName;
    private String lastName;
    private String dose;
    private DateTime datetime;


    public Visit() {
    }

    public Visit(String reservationNumber, String firstName, String lastName, String dose) {
        this.reservationNumber = reservationNumber;
        this.firstName = firstName;
        this.lastName = lastName;
        this.dose = dose;
    }

    public void createVisit(String[] visitInfo, String dateTime){
        List<Visit> currentVisits = readData();
        Visit visit = new Visit(visitInfo[0], visitInfo[1],visitInfo[2], visitInfo[3]);
        String[] dateTime_Split = dateTime.split(";");
        visit.datetime = new DateTime(dateTime_Split[0],dateTime_Split[1]);
        currentVisits.add(visit);
        save(currentVisits);
        System.out.println("Visit for "+ visit.firstName+" "+visit.lastName + " added");
    }

    private void save(List<Visit> currentVisit) {
        JSONArray visitList = new JSONArray();
        for(Visit v : currentVisit){
            JSONObject visitDetails = new JSONObject();
            visitDetails.put("reservation number", v.getReservationNumber());
            visitDetails.put("first name", v.getFirstName());
            visitDetails.put("last name", v.getLastName());
            visitDetails.put("type of dose", v.getDose());
            visitDetails.put("date",v.datetime.getDate());
            visitDetails.put("time",v.datetime.getTime());
            visitList.add(visitDetails);
        }

        //Write JSON file
        try (FileWriter file = new FileWriter("visits.json")){
            file.write((visitList.toJSONString()));
            file.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<Visit> readData() {
        //JSON parser object to parse read file
        JSONParser jsonParser = new JSONParser();
        List<Visit> results = new ArrayList<>();

        try (FileReader reader = new FileReader("visits.json")) {

            //Read JSON file
            Object obj = jsonParser.parse(reader);

            JSONArray visitList = (JSONArray) obj;
            if(visitList.size() == 0 ){
                return results;
            }

            visitList.forEach(visit -> {
                results.add(parseVisitObject((JSONObject) visit));
            });

        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }

        return results;
    }

    private static Visit parseVisitObject(JSONObject visit1) {
        Visit visit = new Visit();
        visit.datetime = new DateTime();
        //Get Person object within list
        visit.reservationNumber = ((String) visit1.get("reservation number"));
        visit.firstName = ((String) visit1.get("first name"));
        visit.lastName = ((String) visit1.get("last name"));
        visit.dose = ((String) visit1.get("type of dose"));
        visit.datetime.setDate((String) visit1.get("date"));
        visit.datetime.setDate((String) visit1.get("time"));
        return visit;
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

    public DateTime getDatetime() {
        return datetime;
    }

    public void setDatetime(DateTime datetime) {
        this.datetime = datetime;
    }

    public String getDose() {
        return dose;
    }

    public void setDose(String dose) {
        this.dose = dose;
    }

}
