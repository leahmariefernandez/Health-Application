package model;

import java.util.ArrayList;

// code copied from HairSalon3
public class DoctorAppointment {

    private final ArrayList<Patient> bookings;

    public DoctorAppointment() {
        int maxTime = 15;
        bookings = new ArrayList<>();
        for (int i = 0; i <= maxTime; i++) {
            bookings.add(i, null);
        }
    }

    // REQUIRES: booked time must be between 8-15
    // MODIFIES: this and Patient
    // EFFECTS: books the Patient into the timeslot
    public void makeNewBooking(Patient p, int bookingTime) {
        System.out.println("Patient " + p.getName() + " has been booked at " + bookingTime);
        bookings.set(bookingTime, p);
        p.setBookedTime(bookingTime);
    }

    // EFFECTS: returns true if patient is found at the timeslot
    public boolean verifyBooking(Patient p, int bookingTime) {
        Patient bookedCustomer = bookings.get(bookingTime);
        if (bookedCustomer == null) {
            return false;
        }
        if (bookedCustomer.getName().equals(p.getName())) {
            System.out.println("See you then!");
        }
        return true;
    }

    // EFFECTS: returns true if patient is booking at the booking time
    public boolean confirmedBooking(String p, int bookingTime) {
        Patient bookedPatient = bookings.get(bookingTime);
        String bookedPatientName = bookedPatient.getName();
        return bookedPatientName.equals(p);

    }
}

