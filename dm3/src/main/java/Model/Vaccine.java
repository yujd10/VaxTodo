package Model;

public class Vaccine {
    private String name;
    private String code;
    private String lot;

    public Vaccine() {
    }

    public Vaccine(String name) {
        this.name = name;
        this.code = generateQR();
        this.lot = "FD3982";
    }

    public String generateQR(){
        String qrNumber = "";
        for(int i = 0; i < 24; i++) {
            qrNumber += Integer.toString((int) Math.floor(Math.random() * 10));
        }
        return qrNumber;
    }

    @Override
    public String toString() {
        return "Vaccine{" +
                "Vaccine :" + name + '\'' +
                ", code='" + code + '\'' +
                ", lot='" + lot + '\'' +
                '}';
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
