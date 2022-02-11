import model.Patient;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PatientTest {
    private Patient testPatient;

    @BeforeEach
    void runBefore() {
        testPatient = new Patient("Leah", 0, 0);
    }

    @Test
    void testConstructor() {
        assertEquals("Leah",testPatient.getName());
        assertEquals(0,testPatient.getVaccinationRecord());
        assertEquals(0,testPatient.getBookingTime());
    }

    @Test
    void testUpdatedVaccinationRecord() {
        testPatient.updatedVaccinationRecord(1);
        assertEquals(1,testPatient.getVaccinationRecord());

        testPatient.updatedVaccinationRecord(3);
        assertEquals(3,testPatient.getVaccinationRecord());
    }
}
