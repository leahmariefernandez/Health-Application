import model.Symptom;

import static org.junit.jupiter.api.Assertions.assertEquals;

// a class that checks the symptoms in the workroom
public class JsonCheck {
    // Code credit to JsonSerializationDemo
    // EFFECTS: checks if symptoms matches the name
    protected void checkSymptoms(String name, Symptom symptom) {
        symptom.convertSymptomName(name);
        assertEquals(name, symptom.getSymptomName());
    }
}
