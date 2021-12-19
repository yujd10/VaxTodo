package Model;

/**
 * La classe pour les vaccines
 */
public class Vaccine {
    private String name;
    private String code;
    private String lot;

    public Vaccine() {
    }

    public Vaccine(String name) {
        this.name = name;
        //code QR est généré automatiquement
        this.code = generateQR();
        this.lot = "FD3982";
    }

    /**
     * Fonction permet de générer un code de 24 digits pour le vaccine de façon aléatoire
     * @return un code QR
     */
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
}
