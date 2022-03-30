package model;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import java.util.ArrayList;

// a class representing a patient; their name, vaccination record and booked time
public class Patient implements Writable {

    public final String name;
    protected Integer vaccinationRecord;
    protected int bookedTime;
    public final ArrayList<Symptom> symptoms;

    // MODIFIES: this
    // EFFECTS: patient is a string, vaccination record and booked time
    public Patient(String name, int vaccinationRecord,int bookedTime) {
        this.name = name;
        this.vaccinationRecord = vaccinationRecord;
        this.bookedTime = bookedTime;
        this.symptoms = new ArrayList<>();
    }


    // MODIFIES: this
    // EFFECTS: updates their vaccination record
    public void updatedVaccinationRecord(Integer doses) {
        vaccinationRecord = doses;
        EventLog.getInstance().logEvent(
                new Event("Vaccination Record has been recorded"));
    }

    // getters
    public String getName() {
        return name;
    }

    public int getBookingTime() {
        return bookedTime;
    }

    public Integer getVaccinationRecord() {
        return vaccinationRecord;
    }

    // setters
    public void setBookedTime(int time) {
        bookedTime = time;
        EventLog.getInstance().logEvent(
                new Event("Appointment has been booked"));
    }

    // MODIFIES: this
    // EFFECTS: add symptom to list of symptoms
    public void addToSearch(Symptom symptomName) {
        symptoms.add(symptomName);
        EventLog.getInstance().logEvent(
                new Event("Added symptom to List of Symptoms"));
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
        EventLog.getInstance().logEvent(
                new Event("Symptoms have been analyzed"));
        return soreThroat && fever;

    }

    // getter
    public ArrayList<Symptom> getSymptoms() {
        return symptoms;
    }

    // Code credit to JsonSerializationDemo
    // EFFECTS: returns this object as a JSON object
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("vaccination record:", vaccinationRecord);
        json.put("booking time:", bookedTime);
        json.put("Symptoms", symptomsToJson());
        return json;
    }

    // Code credit to JsonSerializationDemo
    // EFFECTS: returns this object as a JSON Array, adds symptoms as JSON Objects
    private JSONArray symptomsToJson() {
        JSONArray jsonArray = new JSONArray();

        for (Symptom t : symptoms) {
            jsonArray.put(t.toJson());
        }
        return jsonArray;
    }


}


