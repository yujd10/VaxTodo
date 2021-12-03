package Controller;

import Model.DateTime;
import Model.Visit;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import package1.User;
import package1.UserRole;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class VisitController extends Controller{

    public static void addVisit(Visit visit){
        List<Visit> currentVisits = read();
        currentVisits.add(visit);
        save(currentVisits);
        System.out.println("Visit for" + visit.getFirstName() + visit.getLastName() + "is added" );
    }

    public static void save(List<Visit> currentVisits){
        JSONArray visitList = new JSONArray();
        for(Visit v: currentVisits){
            JSONObject visitDetails = new JSONObject();
            visitDetails.put("reservationNumber",v.getReservationNumber());
            visitDetails.put("firstname",v.getFirstName());
            visitDetails.put("lastname",v.getLastName());
            visitDetails.put("dose",v.getDose());
            visitDetails.put("datetime",new JSONObject());
            visitList.add(visitDetails);
        }

        try (FileWriter file = new FileWriter("visits.json")) {
            //We can write any JSONArray or JSONObject instance to the file
            file.write(visitList.toJSONString());
            file.flush();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void cancelVisit(){

    }

    public void getUpComingVisits(int limit){

    }

    public static List<Visit> read(){
        JSONParser jsonParser = new JSONParser();
        List<Visit> results = new ArrayList<>();

        try (FileReader reader = new FileReader("visits.json")) {
            //Read JSON file
            Object obj = jsonParser.parse(reader);

            JSONArray visitList = (JSONArray) obj;

            visitList.forEach(visit -> {
                results.add(parseVisitObject((JSONObject) visit));
            });

        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }

        return results;
    }

    private static Visit parseVisitObject(JSONObject visit) {
        Visit visit1 = new Visit();
        //Get employee object within list
        visit1.setReservationNumber((String) visit.get("reservationNumber"));
        visit1.setFirstName((String) visit.get("firstname"));
        visit1.setLastName((String) visit.get("lastname"));
        visit1.setDose((String) visit.get("dose"));
        visit1.setDatetime((DateTime) visit.get("datetime"));

        return visit1;
    }

}
