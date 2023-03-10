package persistence;

import model.Patient;
import model.Symptom;
//import model.MedicalSearch;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

import org.json.*;

// Represents a reader that reads workroom from JSON data stored in file
// Code credit to JsonSerializationDemo
public class JsonReader {
    private String source;

    // EFFECTS: constructs reader to read from source file
    public JsonReader(String source) {
        this.source = source;
    }

    // EFFECTS: reads workroom from file and returns it;
    // throws IOException if an error occurs reading data from file
    public Patient read() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseWorkRoom(jsonObject);
    }

    // EFFECTS: reads source file as string and returns it
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }

        return contentBuilder.toString();
    }

    // EFFECTS: analyzes workroom from JSON object and returns it
    private Patient parseWorkRoom(JSONObject jsonObject) {
        int record = jsonObject.getInt("vaccination record:");
        int bookedTime = jsonObject.getInt("booking time:");

        Patient search = new Patient("Leah", record, bookedTime);

        addSymptoms(search, jsonObject);

        return search;
    }

    // MODIFIES: wr
    // EFFECTS: analyzes symptoms from JSON object and adds them to workroom
    private void addSymptoms(Patient wr, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("Symptoms");
        for (Object json : jsonArray) {
            JSONObject next = (JSONObject) json;
            addSymptom(wr, next);
        }
    }

    // MODIFIES: wr
    // EFFECTS: analyzes symptom from JSON object and adds it to workroom
    private void addSymptom(Patient wr, JSONObject jsonObject) {
        String symptoms = jsonObject.getString("symptoms:");
        Symptom symptom = new Symptom();
        symptom.convertSymptomName(symptoms);
        wr.addToSearch(symptom);
    }



}

