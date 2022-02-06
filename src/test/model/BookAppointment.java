package model;

public interface BookAppointment {
    void makeNewBooking(Patient p, int bookingTime);
    void verifyBooking(Patient p, int bookingTime);
    void confirmedBooking(String pNAme, int bookingTime);
}
