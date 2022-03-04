package persistence;

import org.json.JSONObject;

public interface Writable {
    // EFFECTS: returns this as JSON object
    // Code credit to JsonSerializationDemo
    JSONObject toJson();
}
