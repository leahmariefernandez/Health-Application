package model;



public class Patient {

    private final String name;
    private Integer vaccinationRecord;
    private int bookedTime;

    public Patient(String name, Integer vaccinationRecord,int bookedTime) {
        this.name = name;
        this.vaccinationRecord = vaccinationRecord;
        this.bookedTime = bookedTime;
    }

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


