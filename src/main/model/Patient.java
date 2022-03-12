package model;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

// a class representing a patient; their name, vaccination record and booked time
public class Patient implements Writable {

    public final String name;
    protected Integer vaccinationRecord;
    protected int bookedTime;

    // MODIFIES: this
    // EFFECTS: patient is a string, vaccination record and booked time
    public Patient(String name, int vaccinationRecord,int bookedTime) {
        this.name = name;
        this.vaccinationRecord = vaccinationRecord;
        this.bookedTime = bookedTime;
    }


    // MODIFIES: this
    // EFFECTS: updates their vaccination record
    public void updatedVaccinationRecord(Integer doses) {
        vaccinationRecord = doses;
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
    }

    // Code credit to JsonSerializationDemo
    // EFFECTS: returns this object as a JSON object
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("vaccination record:", vaccinationRecord);
        json.put("booking time:", bookedTime);
        return json;
    }


}


