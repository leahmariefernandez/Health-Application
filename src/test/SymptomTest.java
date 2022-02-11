import model.Symptom;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SymptomTest {

    private Symptom testSymptom;

    @BeforeEach
    void runBefore() {
        testSymptom = new Symptom();
    }

    @Test
    void testConstructor() {
        assertEquals("",testSymptom.getSymptomName());
    }

    @Test
    void testConvertSymptomName() {
        testSymptom.convertSymptomName("cough");
        assertEquals("cough",testSymptom.getSymptomName());
        testSymptom.convertSymptomName("fever");
        assertEquals("fever",testSymptom.getSymptomName());
    }
}
