package package1;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Survey {
    public String accountNumber;
    public String lastName;
    public String firstName;
    public String birthday;
    public String medicalCardNumber;
    public String visiteDate;
    public String isSecondDose;
    public String isCovid;
    public String hasSymptome;
    public String hasAllergy;
    public String doseType;

    public Survey(){

    }

    public Survey(Visitor visitor, String medicalCardNumber, String visiteDate, String isSecondDose, String isCovid, String hasSymptome, String hasAllergy, String doseType) throws ParseException {
        this.accountNumber = visitor.accountNumber;
        this.lastName = visitor.lastName;
        this.firstName = visitor.firstName;
        this.birthday = visitor.birthday;
        this.medicalCardNumber = medicalCardNumber;
        this.visiteDate = visiteDate;
        this.isSecondDose = isSecondDose;
        this.isCovid = isCovid;
        this.hasSymptome = hasSymptome;
        this.hasAllergy = hasAllergy;
        this.doseType = doseType;
    }
}
