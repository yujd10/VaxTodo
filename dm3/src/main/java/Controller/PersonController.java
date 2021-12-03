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
    public void createPerson(String[] personInfo, boolean isVolunteer){
        person.createPerson(personInfo, isVolunteer);
    }

    public void updatePerson(Person person, String choice, String info){
        person.update(person, choice, info);
    }

    public void deletePerson(String id){
        person.delete(id);
    }

    public Person search(String info){
        return person.search(info);
    }
}
