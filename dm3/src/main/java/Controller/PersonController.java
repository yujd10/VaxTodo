package Controller;

import Model.Person;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class PersonController extends Controller{
    private Person person = new Person();
    public String createPerson(String[] personInfo, boolean isVolunteer,String address){
        String verification = "";
        for(int i = 0; i < personInfo.length; i++){
            verification += person.verifyInfo(personInfo[i],i);
        }
        verification += person.verifyInfo(address,6);
        if(verification.equals("")){
            person.createPerson(personInfo,isVolunteer,address);
        }
        return verification;
    }

    public String updatePerson(Person person, String choice, String info){
        String verification = person.verifyInfo(info, Integer.parseInt(choice));
        if(verification.equals("")){
            person.update(person, choice, info);
        }
        return verification;
    }

    public void deletePerson(String id){
        person.delete(id);
    }

    public void printPersonList(boolean isVolunteer){
        person.printPersonList(isVolunteer);
    }
    public Person search(String info){
        return person.search(info);
    }
}
