package Model;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.reflect.TypeToken;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Period {
    private String date;
    private int start;

    public Period() {
    }

    public Period(String date, int start) {
        this.date = date;
        this.start = start;
    }

    public boolean isFull(){
       return true;
    }

    public Visit removeVisit(Visit visit){
        List<Visit> visits = read();
        for(Visit visit1:visits){
            if(visit1.equals(visit)){
                visits.remove(visit);
                System.out.println(visit.toString()+" is removed ");
            }
        }
        return visit;
    }

    public Visit addVisit(Visit visit){
        List<Visit> visits = read();
        visits.add(visit);
        System.out.println(visit.toString()+" is added ");
        saveData(visits);
        return visit;
    }

    public List<Visit> read(){
        JSONArray personList = new JSONArray();

        List<Visit> results = new ArrayList<>();
        try {
            Reader reader = Files.newBufferedReader(Paths.get("visits.json"));
            results= new Gson().fromJson(reader,new TypeToken<List<Visit>>() {}.getType());
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return results;
    }

    public void saveData(List<Visit> currentlist){
//        JSONArray personList = new JSONArray();
        Gson gson = new Gson();
        try {
            Writer writer = Files.newBufferedWriter(Paths.get("visits.json"));

            writer.write(gson.toJson(currentlist));
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getStart() {
        return start;
    }

    public void setStart(int start) {
        this.start = start;
    }
}
