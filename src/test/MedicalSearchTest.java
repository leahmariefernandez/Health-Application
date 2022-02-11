import model.MedicalSearch;
import model.Symptom;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class MedicalSearchTest {

    private MedicalSearch testMedicalSearch;

    @BeforeEach
    void runBefore() {
        testMedicalSearch = new MedicalSearch();
    }

    @Test
    void testConstructor() {
        ArrayList<Symptom> symptoms = testMedicalSearch.getSymptoms();
        assertTrue(symptoms.isEmpty());
    }

    @Test
    void testAddSymptom() {
        Symptom symptomA = new Symptom();
        symptomA.convertSymptomName("cough");
        testMedicalSearch.addToSearch(symptomA);
        ArrayList<Symptom> symptoms = testMedicalSearch.getSymptoms();
        assertEquals(symptomA,symptoms.get(0));
        assertEquals(1,symptoms.size());

        Symptom symptomB = new Symptom();
        symptomB.convertSymptomName("sore throat");
        testMedicalSearch.addToSearch(symptomB);
        assertEquals(symptomB,symptoms.get(1));
        assertEquals(2,symptoms.size());
    }

    @Test
    void testAnalyzeSymptomWithSoreThroatOnly() {
        Symptom symptomSoreThroat = new Symptom();
        symptomSoreThroat.convertSymptomName("sore throat");
        testMedicalSearch.addToSearch(symptomSoreThroat);
        ArrayList<Symptom> symptoms = testMedicalSearch.getSymptoms();
        assertEquals(symptomSoreThroat,symptoms.get(0));
        assertEquals(1,symptoms.size());

        ArrayList<String> analyzeSoreThroat = new ArrayList<>();
        analyzeSoreThroat.add(symptomSoreThroat.getSymptomName());
        assertEquals(symptomSoreThroat.getSymptomName(),analyzeSoreThroat.get(0));
        assertEquals(1,analyzeSoreThroat.size());
        assertFalse(testMedicalSearch.analyzeSymptoms(symptoms));
    }

    @Test
    void testAnalyzeSymptomWithFeverOnly() {
        Symptom symptomSoreThroat = new Symptom();
        symptomSoreThroat.convertSymptomName("fever");
        testMedicalSearch.addToSearch(symptomSoreThroat);
        ArrayList<Symptom> symptoms = testMedicalSearch.getSymptoms();
        assertEquals(symptomSoreThroat,symptoms.get(0));
        assertEquals(1,symptoms.size());

        ArrayList<String> analyzeSoreThroat = new ArrayList<>();
        analyzeSoreThroat.add(symptomSoreThroat.getSymptomName());
        assertEquals(symptomSoreThroat.getSymptomName(),analyzeSoreThroat.get(0));
        assertEquals(1,analyzeSoreThroat.size());
        assertFalse(testMedicalSearch.analyzeSymptoms(symptoms));
    }

    @Test
    void testAnalyzeSymptomWithFeverAndNotSoreThroat() {
        Symptom symptomFever = new Symptom();
        symptomFever.convertSymptomName("fever");
        testMedicalSearch.addToSearch(symptomFever);
        ArrayList<Symptom> symptoms = testMedicalSearch.getSymptoms();
        assertEquals(symptomFever,symptoms.get(0));
        assertEquals(1,symptoms.size());

        Symptom symptomNotSoreThroat = new Symptom();
        symptomNotSoreThroat.convertSymptomName("cough");
        testMedicalSearch.addToSearch(symptomNotSoreThroat);
        assertEquals(symptomNotSoreThroat,symptoms.get(1));
        assertEquals(2,symptoms.size());

        ArrayList<String> analyzeSoreThroat = new ArrayList<>();
        analyzeSoreThroat.add(symptomFever.getSymptomName());
        analyzeSoreThroat.add(symptomNotSoreThroat.getSymptomName());
        assertEquals(symptomFever.getSymptomName(),analyzeSoreThroat.get(0));
        assertEquals(symptomNotSoreThroat.getSymptomName(),analyzeSoreThroat.get(1));
        assertEquals(2,analyzeSoreThroat.size());
        assertFalse(testMedicalSearch.analyzeSymptoms(symptoms));
    }

    @Test
    void testAnalyzeSymptomWithSoreThroatAndNotFever() {
        Symptom symptomSoreThroat = new Symptom();
        symptomSoreThroat.convertSymptomName("sore throat");
        testMedicalSearch.addToSearch(symptomSoreThroat);
        ArrayList<Symptom> symptoms = testMedicalSearch.getSymptoms();
        assertEquals(symptomSoreThroat,symptoms.get(0));
        assertEquals(1,symptoms.size());

        Symptom symptomNotFever = new Symptom();
        symptomNotFever.convertSymptomName("cough");
        testMedicalSearch.addToSearch(symptomNotFever);
        assertEquals(symptomNotFever,symptoms.get(1));
        assertEquals(2,symptoms.size());

        ArrayList<String> analyzeSoreThroat = new ArrayList<>();
        analyzeSoreThroat.add(symptomSoreThroat.getSymptomName());
        analyzeSoreThroat.add(symptomNotFever.getSymptomName());
        assertEquals(symptomSoreThroat.getSymptomName(),analyzeSoreThroat.get(0));
        assertEquals(symptomNotFever.getSymptomName(),analyzeSoreThroat.get(1));
        assertEquals(2,analyzeSoreThroat.size());
        assertFalse(testMedicalSearch.analyzeSymptoms(symptoms));
    }

    @Test
    void testAnalyzeSymptomWithFeverAndSoreThroat() {
        Symptom symptomSoreThroat = new Symptom();
        symptomSoreThroat.convertSymptomName("sore throat");
        testMedicalSearch.addToSearch(symptomSoreThroat);
        ArrayList<Symptom> symptoms = testMedicalSearch.getSymptoms();
        assertEquals(symptomSoreThroat,symptoms.get(0));
        assertEquals(1,symptoms.size());

        Symptom symptomFever = new Symptom();
        symptomFever.convertSymptomName("fever");
        testMedicalSearch.addToSearch(symptomFever);
        assertEquals(symptomFever,symptoms.get(1));
        assertEquals(2,symptoms.size());

        ArrayList<String> analyzeSoreThroat = new ArrayList<>();
        analyzeSoreThroat.add(symptomSoreThroat.getSymptomName());
        analyzeSoreThroat.add(symptomFever.getSymptomName());
        assertEquals(symptomSoreThroat.getSymptomName(),analyzeSoreThroat.get(0));
        assertEquals(symptomFever.getSymptomName(),analyzeSoreThroat.get(1));
        assertEquals(2,analyzeSoreThroat.size());
        assertTrue(testMedicalSearch.analyzeSymptoms(symptoms));
    }

}
