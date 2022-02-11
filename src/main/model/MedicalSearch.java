package model;

import java.util.ArrayList;


public class MedicalSearch {
    private final ArrayList<Symptom> symptoms;

    public MedicalSearch() {
        this.symptoms = new ArrayList<>();
    }

    // MODIFIES: this
    // EFFECTS: add symptom to list of symptoms
    public void addToSearch(Symptom symptomName) {
        symptoms.add(symptomName);
    }

    // MODIFIES: this
    // EFFECTS: if list of symptoms contains "sore throat" AND "fever" prompt to book Covid-19 test
    // if list of symptoms are anything else, prompt to book Doctor's appointment
    public boolean analyzeSymptoms(ArrayList<Symptom> md) {
        ArrayList<String> newStringList = new ArrayList<>();
        for (Symptom s : md) {
            newStringList.add(s.getSymptomName());
        }
        boolean soreThroat = false;
        boolean fever = false;
        for (String s : newStringList) {
            if (s.equals("sore throat")) {
                soreThroat = true;
            } else if (s.equals("fever")) {
                fever = true;
            }
        }
        return soreThroat && fever;
    }

    // getter
    public ArrayList<Symptom> getSymptoms() {
        return symptoms;
    }
}