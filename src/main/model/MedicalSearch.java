package model;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import java.util.ArrayList;

// a class that adds symptoms to the list and analyzes what it contains
public class MedicalSearch implements Writable {
    public final ArrayList<Symptom> symptoms;

    // MODIFIES: this
    // EFFECTS: symptoms is a new ArrayList
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

    // Code credit to JsonSerializationDemo
    // EFFECTS: constructs json object symptoms
    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("symptoms", symptomsToJson());
        return json;
    }

    // Code credit to JsonSerializationDemo
    // EFFECTS: constructs json array and adds json object symptoms to it
    private JSONArray symptomsToJson() {
        JSONArray jsonArray = new JSONArray();

        for (Symptom t : symptoms) {
            jsonArray.put(t.toJson());
        }
        return jsonArray;
    }


}