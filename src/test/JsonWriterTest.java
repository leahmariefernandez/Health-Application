import model.Symptom;
import model.MedicalSearch;
import org.junit.jupiter.api.Test;
import persistence.JsonReader;
import persistence.JsonTest;
import persistence.JsonWriter;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

// Code credit to JsonSerializationDemo
public class JsonWriterTest extends JsonTest {

    @Test
    void testWriterInvalidFile() {
        try {
            MedicalSearch wr = new MedicalSearch();
            JsonWriter writer = new JsonWriter("./data/my\0illegal:fileName.json");
            writer.open();
            fail("IOException was expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testWriterEmptyWorkroom() {
        try {
            MedicalSearch wr = new MedicalSearch();
            JsonWriter writer = new JsonWriter("./data/testWriterEmptyWorkroom.json");
            writer.open();
            writer.write(wr);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterEmptyWorkroom.json");
            wr = reader.read();
            assertEquals(0, wr.getSymptoms().size());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    void testWriterGeneralWorkroom() {
        try {
            MedicalSearch wr = new MedicalSearch();

            String headache = "headache";
            Symptom symptom1 = new Symptom();
            symptom1.convertSymptomName(headache);

            String cough = "cough";
            Symptom symptom = new Symptom();
            symptom.convertSymptomName(cough);

            wr.addToSearch(symptom);
            wr.addToSearch(symptom1);

            JsonWriter writer = new JsonWriter("./data/testWriterGeneralWorkroom.json");
            writer.open();
            writer.write(wr);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterGeneralWorkroom.json");
            wr = reader.read();
            List<Symptom> symptoms = wr.getSymptoms();
            assertEquals(2, symptoms.size());
            checkSymptoms("cough", symptoms.get(0));
            checkSymptoms("headache", symptoms.get(1));

        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }
}
