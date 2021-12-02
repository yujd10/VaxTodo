package package1;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

public class Appointment {
	public String id;
	public String lastName;
	public String firstName;
	public String date;
	public String hour;
	public String dose;

	public Appointment() {
	}

	public Appointment(String id,String lastName,String firstName,String date, String hour, String dose) throws ParseException {
		this.id = id;
		this.lastName = lastName;
		this.firstName = firstName;
		this.date = date;
		this.hour = hour;

		this.dose = dose;
	}
	public void printAppointment(){
		System.out.printf(id+";"+lastName+";"+firstName+";"+date+";"+hour+";"+dose+"\n");
	}

	public int countCurrentPeople(String date, String hour){
		int count = 0;
		if(this.date.equals(date)&&this.hour.equals(hour)){
			count++;
		}

		return count;
	}
	public void getVaccinProof(){
		List<Survey> surveysList = SurveyRespiratory.read();
		for (Survey survey : surveysList) {
			if (survey.visiteDate.equals(this.date) && survey.lastName.equals(this.lastName) && survey.firstName.equals(this.firstName)) {
				System.out.println("Preuve de vaccination pour: "+this.firstName+" "+this.lastName+" né le "+survey.birthday+
						" a reçu le dose de "+survey.doseType+" au jour de "+survey.visiteDate);
			}
		}
	}

}
