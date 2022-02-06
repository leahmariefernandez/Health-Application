package ui;

import model.Patient;
import model.Symptoms;

import java.util.ArrayList;
import java.util.Scanner;

public class HealthApp {
    private Symptoms symptoms;
    private Patient vaccinationRecord;
    private Scanner input;

    // EFFECTS: run the health application
    public HealthApp() {
        runApp();
    }

    // MODIFIES: this
    // EFFECTS: processes ui
    // credit to:
    private void runApp() {
        boolean keepRunning = true;
        String command = null;
        initializePatientRecords();
        while (keepRunning) {
            displayMenu();
            command = input.nextLine();
            command = command.toLowerCase();

            if (command.equals("q")) {
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
        this.symptoms = new Symptoms(null);
        this.vaccinationRecord = new Patient("Leah", 0);
        input = new Scanner(System.in);
        // input.useDelimiter("/n");
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

    private void displayMenu() {
        System.out.println("Select one of the following:");
        System.out.println("Type u to update your Vaccination Record");
        System.out.println("Type s to enter your symptoms");
        System.out.println("Type v to view your Vaccination Record");
        System.out.println("Type q to quit");
    }

    // REQUIRES: number must be [0,3]
    // MODIFIES: this and patient
    // EFFECTS: updates vaccination record
    private void changeVaccinationRecord() {
        Patient selected = vaccinationRecord;
        System.out.println("How many doses of the Covid-19 vaccine have you had?");
        int doses = input.nextInt();
        selected.updatedVaccinationRecord(doses);
        System.out.println("Your vaccination record is updated!");
        input.nextLine();


       // if (doses <= 3) {
        //    System.out.println("Your vaccination record is updated!");
         //   selected.updatedVaccinationRecord(doses);
         //   selected.getVaccinationRecord();
       // } else {
        //    System.out.println("Input not valid");
       // }

      //  selected.getVaccinationRecord();
    }

    // REQUIRES: string
    // MODIFIES: this and symptom
    // EFFECTS: insert symptoms into list of symptoms
    private void insertSymptoms() {
        Symptoms selected = symptoms;
        System.out.println("Please enter your top 3 symptoms (type 'none' is no symptoms or 'empty' if "
                + "no more to list)");
        System.out.println("What is your first symptom?");
        String first = input.nextLine();
        selected.addSymptoms(first);

        if (first.equals("none")) {
            System.out.println("Stay home and rest!");
        } else {
            System.out.println("What is your second symptom?");
            String second = input.nextLine();
            selected.addSymptoms(second);
            System.out.println("What is your third symptom?");
            String third = input.nextLine();
            selected.addSymptoms(third);
            selected.analyzeSymptoms();
        }

    }

    private void viewVaccinationRecord() {
        Patient selected = vaccinationRecord;
        System.out.println("You have " + selected.getVaccinationRecord() + " doses");
    }

}

