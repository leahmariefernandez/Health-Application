import model.CovidTest;
import model.Patient;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;


public class CovidTestTest {

    private CovidTest testCovidTest;
    private Patient testPatient;

    @BeforeEach
    void runBefore() {
        testCovidTest = new CovidTest();
        testPatient = new Patient("Leah",0,0);
    }


    @Test
    void testMakeNewBooking() {
        testCovidTest.makeNewCovidBooking(testPatient,11);
        assertTrue(testCovidTest.verifyCovidBooking(testPatient,11));
        assertTrue(testCovidTest.confirmedCovidBooking("Leah",11));
    }

    @Test
    void testEarliestMakeNewBooking() {
        testCovidTest.makeNewCovidBooking(testPatient,6);
        assertTrue(testCovidTest.verifyCovidBooking(testPatient,6));
        assertTrue(testCovidTest.confirmedCovidBooking("Leah",6));
    }

    @Test
    void testLatestMakeNewBooking() {
        testCovidTest.makeNewCovidBooking(testPatient,19);
        assertTrue(testCovidTest.verifyCovidBooking(testPatient,19));
        assertTrue(testCovidTest.confirmedCovidBooking("Leah",19));
    }

    @Test
    void testMakeBookingWithMultiplePatients() {
        Patient newPatient = new Patient("Ella",0,0);
        testCovidTest.makeNewCovidBooking(testPatient,12);
        assertTrue(testCovidTest.verifyCovidBooking(testPatient,12));
        assertTrue(testCovidTest.confirmedCovidBooking("Leah",12));

        testCovidTest.makeNewCovidBooking(newPatient,14);
        assertTrue(testCovidTest.verifyCovidBooking(newPatient,14));
        assertTrue(testCovidTest.verifyCovidBooking(newPatient,14));
    }

    @Test
    void testBookingWithFalseVerification() {
        testCovidTest.makeNewCovidBooking(testPatient,11);
        assertTrue(testCovidTest.verifyCovidBooking(testPatient,11));
        assertTrue(testCovidTest.confirmedCovidBooking("Leah",11));

        Patient sahib = new Patient("Sahib",0,15);
        testCovidTest.makeNewCovidBooking(sahib,15);
        assertFalse(testCovidTest.verifyCovidBooking(sahib,11));
        assertFalse(testCovidTest.confirmedCovidBooking("Sahib",11));
    }

}
