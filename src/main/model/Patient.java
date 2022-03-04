package model;

// a class representing a patient; their name, vaccination record and booked time
public class Patient {

    private final String name;
    private Integer vaccinationRecord;
    private int bookedTime;

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



}


