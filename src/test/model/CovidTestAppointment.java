package model;

import java.util.ArrayList;

public class CovidTestAppointment implements  BookAppointment {

    private ArrayList<Patient> bookings;
    private int careTimeAvailability;

    public CovidTestAppointment () {
        careTimeAvailability = 24;
        bookings = new ArrayList<>();
    }
    // MODIFIES: this and Patient
    // EFFECTS: books the patient into the requested timeslot if it is a valid timeslot
    // and let's the Patient know the booking time
    @Override
    public void makeNewBooking(Patient p, int bookingTime) {

    }

    @Override
    public void verifyBooking(Patient p, int bookingTime) {

    }

    @Override
    public void confirmedBooking(String pName, int bookingTime) {

    }
}
