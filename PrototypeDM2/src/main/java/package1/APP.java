package package1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import static package1.UserRole.BENEVOLE;
import static package1.UserRole.EMPLOYEE;

public class APP {
	private static Page currentPage = Page.MAIN;

	public static void main(String[] args) throws ParseException {

		LoginController.login("argo","argopass");
//		AppointmentRespiratory.addAppointment(new Appointment("appointment1", new Date(), "status1"));
		while(true) {
			if (LoginController.loggedInUser != null) {
				switch (currentPage) {
					case MAIN:
						mainPage();
						break;
					case MANAGE_VISITORS:
						manageVisitor();
						break;
					case SEND_FOLLOWUP:
						followUpPage();
						break;
					case CALENDAR:
						calendarPage();
						break;
					case APPOINTMENT:
						makeAppointment();
						break;
					case SURVEY:
						surveyPage();
						break;
					case VISITOR_LIST:
						showVisitorList();
						break;

				}
			} else {
				System.out.println("Please log in this format: name password");
			}
			BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

			// Reading data using readLine
			String input = null;
			try {
				input = reader.readLine();
			} catch (IOException e) {
				e.printStackTrace();
			}
			if (LoginController.loggedInUser == null) {
				manageLogin(input);
			} else {
				switch (currentPage) {

					case MAIN:
						manageMainPage(input);
						break;
					case MANAGE_VISITORS:
						visitorPageOptions(input);
						break;
					case SEND_FOLLOWUP:
						followUpPageOption(input);
						break;
					case CALENDAR:
						calendarPageOption(input);
						break;
					case APPOINTMENT:
						makeAppointment();
						break;
					case SURVEY:
						surveyPage();
						break;
					case VISITOR_LIST:
						showVisitorList();
						break;
				}
			}
		}
	}

	private static void manageMainPage(String input) {
		if(LoginController.loggedInUser.getRole().equals(EMPLOYEE)){
			if (input.trim().equals("1")) {
				currentPage = Page.MANAGE_VISITORS;
			}
			else if (input.trim().equals("2")){
				currentPage = Page.SEND_FOLLOWUP;
			}
			else if (input.trim().equals("3")){
				currentPage = Page.CALENDAR;
			}
			else if (input.trim().equals("4")){
				currentPage = Page.APPOINTMENT;
			}
			else if (input.trim().equals("5")){
				currentPage = Page.SURVEY;
			}
			else{
				currentPage = Page.MAIN;
			}
		}
		else {
			if (input.trim().equals("1")) {
				currentPage = Page.CALENDAR;
			}
			else if(input.trim().equals("2")){
				currentPage = Page.VISITOR_LIST;
			}
			else if(input.trim().equals("3")){
				currentPage = Page.APPOINTMENT;
			}
			else{
				currentPage = Page.MAIN;
			}
		}
	}

//	private static void initAccounts() {
//		UserRespiratory.addAccount(new User(EMPLOYEE, "argo", "argopass"));
//		UserRespiratory.addAccount(new User(EMPLOYEE, "anna", "annapass"));
//		UserRespiratory.addAccount(new User(BENEVOLE, "benoit", "benoitpass"));
//		UserRespiratory.addAccount(new User(BENEVOLE, "viola", "violapass"));
//	}

	private static void mainPage() {
		if (LoginController.loggedInUser.getRole().equals(UserRole.EMPLOYEE)) {
			System.out.println(
					"- [1] Gestion des visiteurs: Accédez à la liste des visiteurs et ajouter, modifier ou supprimer un visiteur.\n" +
							"- [2] Envoi courriel de suivi: Envoyez un courriel de suivi à un visiteur pour lui rappeler\n" +
							"- [3] Consultation du calendrier: Accédez au calendrier et cherchez les rendez-vous à venir\n" +
							"- [4] Réserver un rendez-vous: Faire la réservation d'un rendez vous\n" +
							"- [5] Remplir questionnaire: Remplir le questionnaire pour avoir les informations personnelles du visiteur\n" +
							"- [0] Retour au menu principal");
		} else if (LoginController.loggedInUser.getRole().equals(UserRole.BENEVOLE)) {
			System.out.println(
					"- [1] Consultation du calendrier: Accédez au calendrier et cherchez les rendez-vous à venir\n" +
							"- [2] Consulation de la liste des visiteurs: Accédez à la liste des visiteurs.\n" +
							"- [3] Réserver un rendez-vous: faire une réservation pour un visiteur\n" +
							"- [0] Retour au menu principal");

		}
	}

	private static void visitorPageOptions(String input) {
		if (input.trim().equals("1")) {
			System.out.println("Entrer les informations du visiteurs en format de \n" +
					"numéro de compte; nom de famille; prénom ; date de naissance YYYY-MM-DD ; courriel ; numéro de téléphone");
			BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

			try {
				input = reader.readLine();
			} catch (IOException e) {
				e.printStackTrace();
			}
			String[] visitorInfo = input.split(";");
			VisitorRespiratory.addAccount(new Visitor(visitorInfo[0], visitorInfo[1], visitorInfo[2], visitorInfo[3],visitorInfo[4],visitorInfo[5],visitorInfo[6],visitorInfo[7],visitorInfo[8]));
			currentPage = Page.MAIN;
		} else if (input.trim().equals("2")) {
			System.out.println("change name in this format: x y; where x name will be changed to y.");
			BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

			try {
				input = reader.readLine();
			} catch (IOException e) {
				e.printStackTrace();
			}

			String[] splits = input.split(" ");
			VisitorRespiratory.modify(splits[0], splits[1],splits[2]);
			currentPage = Page.MAIN;
		} else if (input.trim().equals("3")) {
			System.out.println("specify the visitor account number to delete");
			BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

			try {
				input = reader.readLine();
			} catch (IOException e) {
				e.printStackTrace();
			}

			VisitorRespiratory.delete(input);
			currentPage = Page.MAIN;
		} else if (input.trim().equals("4")) {
			System.out.println("specify the visitor account number to search for");
			BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

			try {
				input = reader.readLine();
			} catch (IOException e) {
				e.printStackTrace();
			}

			VisitorRespiratory.search(input);
			currentPage = Page.MAIN;
		} else if (input.trim().equals("0")) {
			currentPage = Page.MAIN;
		}
	}

	private static void manageVisitor() {
		System.out.println(
				"- [1] Créer un compte\n" +
						"- [2] Modifier un compte\n" +
						"- [3] Supprimer un compte\n" +
						"- [4] Chercher un compte\n" +
						"- [0] Retour au menu principal");
	}

	public static void followUpPageOption(String input){
		if (input.trim().equals("1")){
			System.out.println("Entrer le courriel du visiteur pour envoyé un rappel de son prochain rendez-vous");
			BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

			try {
				input = reader.readLine();
			} catch (IOException e) {
				e.printStackTrace();
			}
			List<Visitor> visitorList = VisitorRespiratory.read();
			for (Visitor visitor : visitorList) {
				if(visitor.getEmail().equals(input)){
					List<Appointment> list = visitor.getAppointment();
					if(list.size() != 0){
						Appointment appointment = list.get(list.size()-1);
					System.out.println("Un rappel est envoyé à "+visitor.getFirstName()+" "+visitor.getLastName()+" pour son " +
							"prochain rendez-vous à "+appointment.date+" "+appointment.hour+ " pour la dose "+appointment.dose+"\n");
					}else{
						System.out.println("Pas de rendez-vous trouvé");
						return;
					}
				}
			}
			currentPage = Page.MAIN;
		}
		else if(input.trim().equals("2")){
			System.out.println("Entrer le courriel du visiteur pour envoyé un preuve de vaccination");
			BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

			try {
				input = reader.readLine();
			} catch (IOException e) {
				e.printStackTrace();
			}
			List<Visitor> visitorList = VisitorRespiratory.read();
			for (Visitor visitor : visitorList) {
				if(visitor.getEmail().equals(input)){
					List<Appointment> list = visitor.getAppointment();
					if(list.size() != 0){
						Appointment appointment = list.get(list.size()-1);
						System.out.println("Un preuve de vaccination est envoyé à "+visitor.getFirstName()+" "+visitor.getLastName());
						appointment.getVaccinProof();
					}else{
						System.out.println("Pas de rendez-vous trouvé");
						return;
					}
				}
			}
			currentPage = Page.MAIN;
		}
		else if(input.trim().equals("0")){
			currentPage = Page.MAIN;
		}
	}
	private static  void followUpPage(){
		System.out.println(
				"[1] Enovyer un rappel\n" +
				"[2] Enovyer un preuve de vaccination\n" +
				"[0] Retour au menu principal");
	}

	private static void makeAppointment() throws ParseException {
		System.out.println("Entrer les informations du rendez-vous en format de \n" +
				"numéro de réservation; nom de famille; prénom ; date réservation YYYY-MM-DD ; heure de réservation HH:hh ; numéro de dose:");
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		String input = "";
		try {
			input = reader.readLine();
		} catch (IOException e) {
			e.printStackTrace();
		}
		String[] appointmentInfo = input.split(";");
		AppointmentRespiratory.addAppointment(new Appointment(appointmentInfo[0], appointmentInfo[1], appointmentInfo[2], appointmentInfo[3],appointmentInfo[4],appointmentInfo[5] ));
		currentPage = Page.MAIN;
		System.out.println("Entrer [0] pour retourner au menu principal.");
	}

	public static void surveyPage(){
		System.out.println("Entrer les informations du questionnaire.");
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		String input = "";
		try {
			System.out.println("Entrer le nom du visiteur en format de: Nom de famille;Prénom");
			input = reader.readLine();
			VisitorRespiratory visitorRespiratory = new VisitorRespiratory();
			Visitor visitor = visitorRespiratory.search(input);
			if(visitor != null){
				System.out.printf("Entrer le numéro carte assurance maladie(sans)espace: ");
				String medicalCardNumber = reader.readLine();
				System.out.printf("Date de visite (YYYY-MM-DD): ");
				String date = reader.readLine();
				System.out.printf("Avez-vous déjà reçu une première dose? [Oui, Non]: ");
				String isSecondDose = reader.readLine();
				System.out.printf("Avez-vous déjà contracté la COVID? [Oui, Non]: ");
				String isCovid = reader.readLine();
				System.out.printf("Avez-vous des symptômes de la COVID? [Oui, Non]: ");
				String hasSymptome = reader.readLine();
				System.out.printf("Avez-vous des allergies? [Oui, Non]: ");
				String hasAllergy = reader.readLine();
				System.out.printf("Quel vaccin souhaitez-vous recevoir? [Moderna, Pfizer, AstraZeneca, Janssen]: ");
				String doseType = reader.readLine();
				Survey survey = new Survey(visitor,medicalCardNumber,date,isSecondDose,isCovid,hasSymptome,hasAllergy,doseType);
				SurveyRespiratory.addSurvey(survey);
				visitor.setSurvey(survey);
				System.out.println("Questionnaire enregistré");
				System.out.println("Entrer [0] pour retourner au menu principal.");
				currentPage = Page.MAIN;
			}else{
				System.out.println("Visiteur non trouvé!");
				currentPage = Page.MAIN;
			}
		} catch (IOException | ParseException e) {
			e.printStackTrace();
		}
	}
	private static void calendarPage(){
		System.out.println("" +
				"[1] Consulter le calendrier\n" +
				"[2] Consulter le nombre de personnes présents\n" +
				"[0] Retour au menu principal");
	}
	private static void calendarPageOption(String input) throws ParseException {
		if (input.trim().equals("1")) {
			System.out.println("Entrer la date en forme de YYYY-MM-DD: ");
			BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

			try {
				input = reader.readLine();
			} catch (IOException e) {
				e.printStackTrace();
			}
			List<Appointment> appointmentList = AppointmentRespiratory.read();
			for (Appointment appointment : appointmentList) {
				if (appointment.date.equals(input)) {
					appointment.printAppointment();
				}
			}
			currentPage = Page.MAIN;
		}
		else if (input.trim().equals("2")) {
			System.out.println("Entrer la date et heure en forme de YYYY-MM-DD;HH:mm : ");
			BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

			try {
				input = reader.readLine();
			} catch (IOException e) {
				e.printStackTrace();
			}
			String[] split = input.split(";");
			int count = 0;
			List<Appointment> appointmentList = AppointmentRespiratory.read();
			for (Appointment appointment : appointmentList) {
				count += appointment.countCurrentPeople(split[0], split[1]);
			}
			System.out.println("Nombre de personne acutel est: "+count);
			currentPage = Page.MAIN;
		}
		else if (input.trim().equals("0")) {
			currentPage = Page.MAIN;
		}
	}
	private static void showVisitorList(){
		List<Visitor> visitorList = VisitorRespiratory.read();
		for (Visitor visitor : visitorList) {
			System.out.println(visitor.getAccountNumber()+";"+visitor.getLastName()+";"+visitor.getFirstName()+";"+visitor.getEmail()+visitor.getTelephone());
		}
	}
	private static void manageLogin(String input) {
		String[] res = input.split(" ");
		LoginController.login(res[0], res[1]);
	}
}
