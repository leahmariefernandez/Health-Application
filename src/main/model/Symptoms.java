package model;

import java.util.ArrayList;

public class Symptoms {
    private ArrayList<String> symptoms;

    public Symptoms(ArrayList<String> symptoms) {
        this.symptoms = new ArrayList<>();
    }

    // MODIFIES: this
    // EFFECTS: add symptom to list of symptoms
    public void addSymptoms(String symptomName) {
        symptoms.add(symptomName);
    }

    // MODIFIES: this
    // EFFECTS: if list of symptoms contains "sore throat" AND "fever" prompt to book Covid-19 test
    // if list of symptoms are anything else, prompt to book Doctor's appointment
    // if no list of symptoms, print "Stay home and rest!"
    public void analyzeSymptoms() {
        if (symptoms.contains("sore throat") && (symptoms.contains("fever"))) {
            //stub : call covid test appointment
        } else if (symptoms.isEmpty()) {
            System.out.println("Stay home and rest!");
        } else {
            // stub: call book doctor's appointment
        }
    }
}