package ie.gmit.sw.fyp;

import org.json.JSONException;
import org.json.JSONObject;

public class JSONParse {
    public String mainparse(JSONObject json) {
        String out="parse";
        try {
            out = json.getString("Value");
        } catch (JSONException e) {
            out = e.getMessage();
        }
        return out;
    }
}
