package ui;

import model.CovidTest;
import model.Patient;
import model.MedicalSearch;
import model.DoctorAppointment;
import model.Symptom;


import java.util.Scanner;

public class HealthApp {
    private MedicalSearch medicalSearch;
    private Patient vaccinationRecord;
    private Scanner input;
    private DoctorAppointment doctorAppointment;
    private CovidTest covidTest;
    private Symptom first;
    private Symptom second;
    private Symptom third;

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
        this.medicalSearch = new MedicalSearch();
        this.vaccinationRecord = new Patient("Leah", 0, 0);
        this.doctorAppointment = new DoctorAppointment();
        this.covidTest = new CovidTest();
        this.first = new Symptom();
        this.second = new Symptom();
        this.third = new Symptom();
        input = new Scanner(System.in);
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
        MedicalSearch selected = medicalSearch;
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
    }

    // MODIFIES: this, patient, and CovidAppointment
    // EFFECTS: books a COVID-19 appointment
    private void covAppointment() {
        System.out.println("You are eligible for a COVID-19 Test. What time are you available?");
        int time = input.nextInt();
        covidTest.makeNewCovidBooking(vaccinationRecord, time);
        covidTest.verifyCovidBooking(vaccinationRecord, time);
        covidTest.confirmedCovidBooking(vaccinationRecord.getName(), time);
    }
}

