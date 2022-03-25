package ui;

import model.CovidTest;
import model.Patient;
//import model.MedicalSearch;
import model.DoctorAppointment;
import model.Symptom;
import persistence.JsonReader;
import persistence.JsonWriter;


import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

public class HealthApp {
    //private MedicalSearch medicalSearch = new MedicalSearch();
    private Patient vaccinationRecord = new Patient("Leah", 0, 0);
    private Scanner input;
    private DoctorAppointment doctorAppointment;
    private CovidTest covidTest;
    private Symptom first = new Symptom();
    private Symptom second = new Symptom();
    private Symptom third = new Symptom();
    private static final String JSON_STORE = "./data/medicalsearch.json";
    private JsonReader jsonReader;
    private JsonWriter jsonWriter;

    // EFFECTS: run the health application
    public HealthApp() {
        runApp();
    }

    // MODIFIES: this
    // EFFECTS: processes ui
    // credit to TellerApp
    private void runApp() {
        boolean keepRunning = true;
        String command;
        initializePatientRecords();
        while (keepRunning) {
            displayMenu();
            command = input.nextLine();
            command = command.toLowerCase();

            if (command.equals("q")) {
                keepRunning = false;
            } else if (command.equals("s")) {
                processCommand(command);
                keepRunning = false;
            } else {
                processCommand(command);
            }
        }
        System.out.println("Stay safe and healthy!");
    }

    // MODIFIES: this
    // EFFECTS: initializes patient records
    private void initializePatientRecords() {
       // this.vaccinationRecord = new Patient("Leah", 0, 0);
        this.doctorAppointment = new DoctorAppointment();
        this.covidTest = new CovidTest();
        //this.first = new Symptom();
       // this.second = new Symptom();
      //  this.third = new Symptom();
        input = new Scanner(System.in);
        jsonReader = new JsonReader(JSON_STORE);
        jsonWriter = new JsonWriter(JSON_STORE);
    }

    // MODIFIES: this
    // EFFECTS: processes user command
    private void processCommand(String command) {
        if (command.equals("u")) {
            changeVaccinationRecord();
        } else if (command.equals("s")) {
            insertSymptoms();
        } else if (command.equals("v")) {
            viewVaccinationRecord();
        } else if (command.equals("save")) {
            saveWorkRoom();
        } else if (command.equals("load")) {
            loadWorkRoom();
        } else if (command.equals("print")) {
            printSymptoms();
        } else {
            System.out.println("Input not valid. Please try again!");
        }
    }

    // EFFECTS: displays menu
    private void displayMenu() {
        System.out.println("Welcome! Select one of the following:");
        System.out.println("Type u to update your Vaccination Record");
        System.out.println("Type s to enter your symptoms");
        System.out.println("Type v to view your Vaccination Record");
        System.out.println("Type save to save");
        System.out.println("Type load to load symptoms");
        System.out.println("Type print to print symptoms");
        System.out.println("Type q to quit");
    }

    // REQUIRES: number must be [0,3]
    // MODIFIES: this and patient
    // EFFECTS: updates vaccination record
    private void changeVaccinationRecord() {
        Patient patient = vaccinationRecord;
        System.out.println("How many doses of the Covid-19 vaccine have you had?");
        int doses = input.nextInt();
        patient.updatedVaccinationRecord(doses);
        System.out.println("Your vaccination record is updated!");
        input.nextLine();
    }

    // MODIFIES: this and symptom
    // EFFECTS: insert symptoms into list of symptoms
    private void insertSymptoms() {
        Patient selected = vaccinationRecord;
        Symptom one = first;
        Symptom two = second;
        Symptom three = third;
        System.out.println("What is your first symptom?");
        String first = input.nextLine();
        one.convertSymptomName(first);
        selected.addToSearch(one);
        System.out.println("What is your second symptom?");
        String second = input.nextLine();
        two.convertSymptomName(second);
        selected.addToSearch(two);
        System.out.println("What is your third symptom?");
        String third = input.nextLine();
        three.convertSymptomName(third);
        selected.addToSearch(three);
        selected.analyzeSymptoms(selected.getSymptoms());
        if (selected.analyzeSymptoms(selected.getSymptoms())) {
            covAppointment();
        } else {
            docAppointment();
        }
    }

    // EFFECTS: View vaccination record
    private void viewVaccinationRecord() {
        Patient selected = vaccinationRecord;
        System.out.println("You have " + selected.getVaccinationRecord() + " doses");
    }

    // REQUIRES: symptoms have to include" sore throat" and "fever"
    // MODIFIES: this, patient, and doctorAppointment
    // EFFECTS: books a doctor appointment
    private void docAppointment() {
        System.out.println("Book a Doctor's Appointment. What time are you available?");
        int time = input.nextInt();
        doctorAppointment.makeNewBooking(vaccinationRecord, time);
        doctorAppointment.verifyBooking(vaccinationRecord, time);
        doctorAppointment.confirmedBooking(vaccinationRecord.getName(), time);
        runApp();
    }

    // MODIFIES: this, patient, and CovidAppointment
    // EFFECTS: books a COVID-19 appointment
    private void covAppointment() {
        System.out.println("You are eligible for a COVID-19 Test. What time are you available?");
        int time = input.nextInt();
        covidTest.makeNewCovidBooking(vaccinationRecord, time);
        covidTest.verifyCovidBooking(vaccinationRecord, time);
        covidTest.confirmedCovidBooking(vaccinationRecord.getName(), time);
        runApp();

    }

    // EFFECTS: saves the workroom to file
    private void saveWorkRoom() {
        try {
            jsonWriter.open();
            jsonWriter.write(vaccinationRecord);
            jsonWriter.close();
            System.out.println("Saved Symptoms to " + JSON_STORE);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + JSON_STORE);
        }
    }

    // MODIFIES: this
    // EFFECTS: loads workroom from file
    private void loadWorkRoom() {
        try {
            vaccinationRecord = jsonReader.read();
            System.out.println("Loaded from " + JSON_STORE);
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_STORE);
        }
    }


    // EFFECTS: prints all the thingies in workroom to the console
    private void printSymptoms() {
        List<Symptom> symptoms = vaccinationRecord.getSymptoms();

        for (Symptom t : symptoms) {
            System.out.println("symptom: " + t.getSymptomName());
        }

        System.out.println("vaccination record: " + vaccinationRecord.getVaccinationRecord());
        System.out.println("booking time: " + vaccinationRecord.getBookingTime());
    }

}

