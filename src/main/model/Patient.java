package model;


public class Patient {

    private String name;
    // private int age;
    private Integer vaccinationRecord;
    private int bookedTime;

    public Patient(String name, Integer vaccinationRecord) {
        this.name = name;
        this.vaccinationRecord = vaccinationRecord;
        this.bookedTime = 0;
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

    public void printName() {
        System.out.println("" + name + "");
    }

    // EFFECTS: returns the bookedTime of this customers appointment
    public int confirmBooking() {
        System.out.println(name + " is booked at " + bookedTime);
        return bookedTime;
    }


}


