import model.DoctorAppointment;
import model.Patient;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class DoctorAppointmentTest {

    private DoctorAppointment testDoctorAppointment;
    private Patient testPatient;

    @BeforeEach
    void runBefore() {
        testDoctorAppointment = new DoctorAppointment();
        testPatient = new Patient("Leah",0,0);
    }

    @Test
    void testMakeNewBooking() {
        testDoctorAppointment.makeNewBooking(testPatient,11);
        assertTrue(testDoctorAppointment.verifyBooking(testPatient,11));
        assertTrue(testDoctorAppointment.confirmedBooking("Leah",11));
    }

    @Test
    void testEarliestMakeNewBooking() {
        testDoctorAppointment.makeNewBooking(testPatient,8);
        assertTrue(testDoctorAppointment.verifyBooking(testPatient,8));
        assertTrue(testDoctorAppointment.confirmedBooking("Leah",8));
    }

    @Test
    void testLatestMakeNewBooking() {
        testDoctorAppointment.makeNewBooking(testPatient,15);
        assertTrue(testDoctorAppointment.verifyBooking(testPatient,15));
        assertTrue(testDoctorAppointment.confirmedBooking("Leah",15));
    }

    @Test
    void testMakeBookingWithMultiplePatients() {
        Patient newPatient = new Patient("Simon",0,0);
        testDoctorAppointment.makeNewBooking(testPatient,12);
        assertTrue(testDoctorAppointment.verifyBooking(testPatient,12));
        assertTrue(testDoctorAppointment.confirmedBooking("Leah",12));

        testDoctorAppointment.makeNewBooking(newPatient,11);
        assertTrue(testDoctorAppointment.verifyBooking(newPatient,11));
        assertTrue(testDoctorAppointment.verifyBooking(newPatient,11));
    }
}
