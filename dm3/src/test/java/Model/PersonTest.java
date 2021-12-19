package Model;

import static org.junit.jupiter.api.Assertions.*;

import Controller.PersonController;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class PersonTest {
    private PersonController personController;

    @BeforeEach
    public void setPerson(){
        this.personController = new PersonController();
    }

    @Test
    public void testVerification(){
        String[] personInfo = new String[]{"202112190001", "Jordan", "Tracy", "2000-10-18", "jordanT@gmail.com", "5147894561"};
        String address = "215;rue guy;Montreal;QC;H4F2S2";
        String verification = personController.createPerson(personInfo,false,address);
        assertTrue(verification.equals(""));
    }

    @Test
    public void testVerificationFail(){
        String[] personInfo = new String[]{"1", "Jordan", "Tracy", "2", "jordanT@gmail.com", "121321321321321"};
        String address = "215;rue guy;Montreal;QC;H4F2S2";
        String address1 = "215;rue guy;Montreal;QC;11H4F2S2";
        String verification = personController.createPerson(personInfo,false,address);
        String verification1 = personController.createPerson(personInfo,false,address1);
        assertFalse((verification.equals("")));
        assertFalse((verification1.equals("")));
    }
}
