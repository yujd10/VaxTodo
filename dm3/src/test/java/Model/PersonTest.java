package Model;

import static org.junit.jupiter.api.Assertions.*;

import Controller.PersonController;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

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

    @Test
    public void testCreatePerson(){
        String[] personInfo = new String[]{"202112190001", "Jordan", "Tracy", "2000-10-18", "jordanT@gmail.com", "5147894561"};
        String address = "215;rue guy;Montreal;QC;H4F2S2";
        personController.createPerson(personInfo,false,address);
        Person person = new Person();
        boolean[] success = new boolean[6];
        boolean pass = true;
        List<Person> personList = person.readData();
        for(Person person1 : personList){
            if(person1.getId().equals(personInfo[0])){
                person = person1;
            }
        }
        success[0] = (person.getId().equals(personInfo[0])) ? true : false;
        success[1] = (person.getLastName().equals(personInfo[1])) ? true : false;
        success[2] = (person.getFirstName().equals(personInfo[2])) ? true : false;
        success[3] = (person.getBirthDate().equals(personInfo[3])) ? true : false;
        success[4] = (person.getEmailAddress().equals(personInfo[4])) ? true : false;
        success[5] = (person.getPhoneNumber().equals(personInfo[5])) ? true : false;
        for(boolean b : success){
            if (b == false){
                pass = false;
            }
        }
        assertTrue(pass);
    }

    @Test
    public void testCreatePersonFail(){
        String[] personInfo = new String[]{"1", "Jordan", "Tracy", "11", "jordanT@gmail.com", "115147894561"};
        String address = "215;rue guy;Montreal;QC;H4F2S2";
        personController.createPerson(personInfo,false,address);
        Person person = new Person();
        boolean[] success = new boolean[6];
        boolean pass = true;
        List<Person> personList = person.readData();
        for(Person person1 : personList){
            if(person1.getId().equals(personInfo[0])){
                person = person1;
            }
        }
        success[0] = (person.getId()==null) ? true : false;
        success[1] = (person.getLastName()==null) ? true : false;
        success[2] = (person.getFirstName()==null) ? true : false;
        success[3] = (person.getBirthDate()==null) ? true : false;
        success[4] = (person.getEmailAddress()==null) ? true : false;
        success[5] = (person.getPhoneNumber()==null) ? true : false;
        for(boolean b : success){
            if (b == false){
                pass = false;
            }
        }
        assertFalse(!pass);
    }
}
