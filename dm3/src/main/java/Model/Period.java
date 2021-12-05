package Model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Period {
    private List<Visit> visits ;
    private String date;
    private int start;
    private int end;



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
                "date='" + date + '\'' +
                ", start=" + start +
                ", end=" + end +
                ", visits" + showVisits() +
                '}';
    }

    public boolean isFull(){
        boolean listFull = false;
        if(this.visits.size() >= 15)
            listFull = true;
        return listFull;
    }

    public Period() {
        this.visits = new ArrayList<>();
    }

    public Period(String date, int start, int end) {
        this.date = date;
        this.start = start;
        this.end = end;
        this.visits = new ArrayList<>();
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

    public int getEnd() {
        return end;
    }

    public void setEnd(int end) {
        this.end = end;
    }
}
