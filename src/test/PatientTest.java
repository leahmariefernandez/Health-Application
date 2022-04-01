import model.Patient;
import model.Symptom;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

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
        ArrayList<Symptom> symptoms = testPatient.getSymptoms();
        assertTrue(symptoms.isEmpty());
    }

    @Test
    void testUpdatedVaccinationRecord() {
        testPatient.updatedVaccinationRecord(1);
        assertEquals(1,testPatient.getVaccinationRecord());

        testPatient.updatedVaccinationRecord(3);
        assertEquals(3,testPatient.getVaccinationRecord());
    }


    @Test
    void testAddSymptom() {
        Symptom symptomA = new Symptom();
        symptomA.convertSymptomName("cough");
        testPatient.addToSearch(symptomA);
        ArrayList<Symptom> symptoms = testPatient.getSymptoms();
        assertEquals(symptomA,symptoms.get(0));
        assertEquals(1,symptoms.size());

        Symptom symptomB = new Symptom();
        symptomB.convertSymptomName("sore throat");
        testPatient.addToSearch(symptomB);
        assertEquals(symptomB,symptoms.get(1));
        assertEquals(2,symptoms.size());
    }

    @Test
    void testClearSymptom() {
        Symptom symptomA = new Symptom();
        symptomA.convertSymptomName("cough");
        testPatient.addToSearch(symptomA);
        ArrayList<Symptom> symptoms = testPatient.getSymptoms();


        testPatient.clearSymptoms();
        assertEquals(0,symptoms.size());
    }

    @Test
    void testAnalyzeSymptomWithSoreThroatOnly() {
        Symptom symptomSoreThroat = new Symptom();
        symptomSoreThroat.convertSymptomName("sore throat");
        testPatient.addToSearch(symptomSoreThroat);
        ArrayList<Symptom> symptoms = testPatient.getSymptoms();
        assertEquals(symptomSoreThroat,symptoms.get(0));
        assertEquals(1,symptoms.size());

        ArrayList<String> analyzeSoreThroat = new ArrayList<>();
        analyzeSoreThroat.add(symptomSoreThroat.getSymptomName());
        assertEquals(symptomSoreThroat.getSymptomName(),analyzeSoreThroat.get(0));
        assertEquals(1,analyzeSoreThroat.size());
        assertFalse(testPatient.analyzeSymptoms(symptoms));
    }

    @Test
    void testAnalyzeSymptomWithFeverOnly() {
        Symptom symptomSoreThroat = new Symptom();
        symptomSoreThroat.convertSymptomName("fever");
        testPatient.addToSearch(symptomSoreThroat);
        ArrayList<Symptom> symptoms = testPatient.getSymptoms();
        assertEquals(symptomSoreThroat,symptoms.get(0));
        assertEquals(1,symptoms.size());

        ArrayList<String> analyzeSoreThroat = new ArrayList<>();
        analyzeSoreThroat.add(symptomSoreThroat.getSymptomName());
        assertEquals(symptomSoreThroat.getSymptomName(),analyzeSoreThroat.get(0));
        assertEquals(1,analyzeSoreThroat.size());
        assertFalse(testPatient.analyzeSymptoms(symptoms));
    }

    @Test
    void testAnalyzeSymptomWithFeverAndNotSoreThroat() {
        Symptom symptomFever = new Symptom();
        symptomFever.convertSymptomName("fever");
        testPatient.addToSearch(symptomFever);
        ArrayList<Symptom> symptoms = testPatient.getSymptoms();
        assertEquals(symptomFever,symptoms.get(0));
        assertEquals(1,symptoms.size());

        Symptom symptomNotSoreThroat = new Symptom();
        symptomNotSoreThroat.convertSymptomName("cough");
        testPatient.addToSearch(symptomNotSoreThroat);
        assertEquals(symptomNotSoreThroat,symptoms.get(1));
        assertEquals(2,symptoms.size());

        ArrayList<String> analyzeSoreThroat = new ArrayList<>();
        analyzeSoreThroat.add(symptomFever.getSymptomName());
        analyzeSoreThroat.add(symptomNotSoreThroat.getSymptomName());
        assertEquals(symptomFever.getSymptomName(),analyzeSoreThroat.get(0));
        assertEquals(symptomNotSoreThroat.getSymptomName(),analyzeSoreThroat.get(1));
        assertEquals(2,analyzeSoreThroat.size());
        assertFalse(testPatient.analyzeSymptoms(symptoms));
    }

    @Test
    void testAnalyzeSymptomWithSoreThroatAndNotFever() {
        Symptom symptomSoreThroat = new Symptom();
        symptomSoreThroat.convertSymptomName("sore throat");
        testPatient.addToSearch(symptomSoreThroat);
        ArrayList<Symptom> symptoms = testPatient.getSymptoms();
        assertEquals(symptomSoreThroat,symptoms.get(0));
        assertEquals(1,symptoms.size());

        Symptom symptomNotFever = new Symptom();
        symptomNotFever.convertSymptomName("cough");
        testPatient.addToSearch(symptomNotFever);
        assertEquals(symptomNotFever,symptoms.get(1));
        assertEquals(2,symptoms.size());

        ArrayList<String> analyzeSoreThroat = new ArrayList<>();
        analyzeSoreThroat.add(symptomSoreThroat.getSymptomName());
        analyzeSoreThroat.add(symptomNotFever.getSymptomName());
        assertEquals(symptomSoreThroat.getSymptomName(),analyzeSoreThroat.get(0));
        assertEquals(symptomNotFever.getSymptomName(),analyzeSoreThroat.get(1));
        assertEquals(2,analyzeSoreThroat.size());
        assertFalse(testPatient.analyzeSymptoms(symptoms));
    }

    @Test
    void testAnalyzeSymptomWithFeverAndSoreThroat() {
        Symptom symptomSoreThroat = new Symptom();
        symptomSoreThroat.convertSymptomName("sore throat");
        testPatient.addToSearch(symptomSoreThroat);
        ArrayList<Symptom> symptoms = testPatient.getSymptoms();
        assertEquals(symptomSoreThroat,symptoms.get(0));
        assertEquals(1,symptoms.size());

        Symptom symptomFever = new Symptom();
        symptomFever.convertSymptomName("fever");
        testPatient.addToSearch(symptomFever);
        assertEquals(symptomFever,symptoms.get(1));
        assertEquals(2,symptoms.size());

        ArrayList<String> analyzeSoreThroat = new ArrayList<>();
        analyzeSoreThroat.add(symptomSoreThroat.getSymptomName());
        analyzeSoreThroat.add(symptomFever.getSymptomName());
        assertEquals(symptomSoreThroat.getSymptomName(),analyzeSoreThroat.get(0));
        assertEquals(symptomFever.getSymptomName(),analyzeSoreThroat.get(1));
        assertEquals(2,analyzeSoreThroat.size());
        assertTrue(testPatient.analyzeSymptoms(symptoms));
    }
}
