package Model;

public class Vaccine {
    private String name;
    private String code;
    private String lot;

    public Vaccine() {
    }

    public Vaccine(String name, String code, String lot) {
        this.name = name;
        this.code = code;
        this.lot = lot;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getLot() {
        return lot;
    }

    public void setLot(String lot) {
        this.lot = lot;
    }
}
