package Model;

import java.util.List;

public class Period {
    private Visit[] visits;
    private String date;
    private Integer start;
    private Integer end;

    public Period() {
    }

    public Period(Visit[] visits, String date, Integer start, Integer end) {
        this.visits = new Visit[15];
        this.date = date;
        this.start = start;
        this.end = end;
    }

    public boolean isFull(){
        return false;
    }

    public Visit[] getVisits() {
        return visits;
    }

    public void setVisits(Visit[] visits) {
        this.visits = visits;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Integer getStart() {
        return start;
    }

    public void setStart(Integer start) {
        this.start = start;
    }

    public Integer getEnd() {
        return end;
    }

    public void setEnd(Integer end) {
        this.end = end;
    }
}
