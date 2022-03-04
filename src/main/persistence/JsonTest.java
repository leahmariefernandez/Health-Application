package persistence;

import model.Symptom;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class JsonTest {
    // Code credit to JsonSerializationDemo
    protected void checkSymptoms(String name, Symptom symptom) {
        symptom.convertSymptomName(name);
        assertEquals(name, symptom.getSymptomName());
    }
}
