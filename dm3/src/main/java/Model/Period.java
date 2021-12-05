package Model;

import java.util.ArrayList;
import java.util.List;

public class Period {
    private List<Visit> visits ;
    private String date;
    private int start;

    public Period() {
        this.visits = new ArrayList<>();
    }

    public Period(String date, int start) {
        this.date = date;
        this.start = start;
        this.visits = new ArrayList<>();
    }

    public void AddVisit(Visit visit){
        if(!isFull())
            this.visits.add(visit);
    }

    public String showVisits(){
      String list = "[";
      for(Visit visit:visits){
          list = list+ visit.toString();
      }
        list = list + " ] ";

        return list;
    }

    public String toString() {
        return "Period{" +
                "date : '" + date + '\'' +
                ", start at" + start +
                ", visits " + showVisits() +
                '}';
    }

    public boolean isFull(){
        boolean listFull = false;
        if(this.visits.size() >= 15)
            listFull = true;
        return listFull;
    }

    public List<Visit> getVisits() {
        return visits;
    }

    public void setVisits(List<Visit> visits) {
        this.visits = visits;
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
