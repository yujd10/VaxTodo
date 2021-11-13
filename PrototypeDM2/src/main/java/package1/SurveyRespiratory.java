package package1;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class SurveyRespiratory {
    public static void addSurvey(Survey survey) {
        List<Survey> currentSurveys = read();
        currentSurveys.add(survey);
        JSONArray employeeList = new JSONArray();
        for (Survey u : currentSurveys) {
            JSONObject employeeDetails = new JSONObject();
            employeeDetails.put("account number", u.accountNumber);
            employeeDetails.put("last name", u.lastName);
            employeeDetails.put("first name", u.firstName);
            employeeDetails.put("birthday", u.birthday);
            employeeDetails.put("medical card number", u.medicalCardNumber);
            employeeDetails.put("visit date", u.visiteDate);
            employeeDetails.put("is second dose", u.isSecondDose);
            employeeDetails.put("is covid", u.isCovid);
            employeeDetails.put("has symptom", u.hasSymptome);
            employeeDetails.put("has allergy", u.hasAllergy);
            employeeDetails.put("dose type", u.doseType);
            employeeList.add(employeeDetails);
        }

        //Write JSON file
        try (FileWriter file = new FileWriter("surveys.json")) {
            //We can write any JSONArray or JSONObject instance to the file
            file.write(employeeList.toJSONString());
            file.flush();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static List<Survey> read() {
        //JSON parser object to parse read file
        JSONParser jsonParser = new JSONParser();
        List<Survey> results = new ArrayList<>();

        try (FileReader reader = new FileReader("surveys.json")) {
            //Read JSON file
            Object obj = jsonParser.parse(reader);

            JSONArray employeeList = (JSONArray) obj;

            employeeList.forEach(emp -> {
                results.add(parseSurveyObject((JSONObject) emp));
            });

        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }

        return results;
    }

    private static Survey parseSurveyObject(JSONObject employee) {
        Survey survey = new Survey();
        //Get employee object within list
        survey.accountNumber = (String) employee.get("account number");
        survey.lastName = (String) employee.get("last name");
        survey.firstName = (String) employee.get("first name");
        survey.birthday = (String) employee.get("birthday");
        survey.medicalCardNumber = (String) employee.get("medical card number");
        survey.visiteDate = (String) employee.get("visit date");
        survey.isSecondDose = (String) employee.get("is second dose");
        survey.isCovid = (String) employee.get("is covid");
        survey.hasSymptome = (String) employee.get("has symptom");
        survey.hasAllergy = (String) employee.get("has allergy");
        survey.doseType = (String) employee.get("dose type");

        return survey;
    }
}
