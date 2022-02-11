package model;

import java.util.ArrayList;

// code copied from HairSalon3
public class CovidTest {

    private final ArrayList<Patient> bookings;

    public CovidTest() {
        int maxTime = 19;
        bookings = new ArrayList<>();
        for (int i = 0; i <= maxTime; i++) {
            bookings.add(i, null);
        }
    }

    // REQUIRES: bookedTime must be between 6-19
    // MODIFIES: this and Patient
    // EFFECTS: books the Patient into the timeslot
    public void makeNewCovidBooking(Patient p, int bookingTime) {
        System.out.println("Patient " + p.getName() + " has been booked at " + bookingTime);
        bookings.set(bookingTime, p);
        p.setBookedTime(bookingTime);
    }

    // EFFECTS: returns true if Patient is found at booking time
    public boolean verifyCovidBooking(Patient p, int bookingTime) {
        Patient bookedCustomer = bookings.get(bookingTime);
        if (bookedCustomer.getName().equals(p.getName())) {
            System.out.println("See you then!");
            return true;
        } else {
            return false;
        }
    }

    // returns true if Patient is booked at that time
    public boolean confirmedCovidBooking(String p, int bookingTime) {
        Patient bookedPatient = bookings.get(bookingTime);
        String bookedPatientName = bookedPatient.getName();
        return bookedPatientName.equals(p);

    }

}