package model;

public class Symptom {
    private String symptomName;

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
}
