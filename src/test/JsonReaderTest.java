import model.MedicalSearch;
import model.Symptom;
import org.junit.jupiter.api.Test;
import persistence.JsonReader;
import persistence.JsonTest;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

// Code credit to JsonSerializationDemo
public class JsonReaderTest extends JsonTest {

    @Test
    void testReaderNonExistentFile() {
        JsonReader reader = new JsonReader("./data/noSuchFile.json");
        try {
            MedicalSearch wr = reader.read();
            fail("IOException expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testReaderEmptyWorkRoom() {
        JsonReader reader = new JsonReader("./data/testReaderEmptyWorkRoom.json");
        try {
            MedicalSearch wr = reader.read();
            assertEquals(0, wr.symptoms.size());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    void testReaderGeneralWorkRoom() {
        JsonReader reader = new JsonReader("./data/testReaderGeneralWorkRoom.json");
        try {
            MedicalSearch wr = reader.read();
            List<Symptom> symptoms = wr.getSymptoms();
            assertEquals(2, symptoms.size());
            checkSymptoms("cough", symptoms.get(0));
            checkSymptoms("headache", symptoms.get(1));
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }
}
