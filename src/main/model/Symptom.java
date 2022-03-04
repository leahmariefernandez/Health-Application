package model;

import org.json.JSONObject;
import persistence.Writable;

// a class representing symptoms
public class Symptom implements Writable {
    private String symptomName;

    // MODIFIES: this
    // EFFECTS: symptom is symptom name
    public Symptom() {
        this.symptomName = "";
    }


    // MODIFIES: this
    // EFFECTS:
    public void convertSymptomName(String name) {
        symptomName = name;
    }

    // getter
    public String getSymptomName() {
        return symptomName;
    }

    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("symptoms:", symptomName);
        return json;
    }
}
