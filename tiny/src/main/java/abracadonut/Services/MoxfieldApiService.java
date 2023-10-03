package abracadonut.Services;

import org.json.JSONObject;

import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

public class MoxfieldApiService {

    static String baseUrl = "https://api2.moxfield.com/v2/decks";

    public static JSONObject getDecklist(String deckId) {
        String endpoint = "/all/" + deckId;
        return doRequest(endpoint);
    }

    private static JSONObject doRequest(String endpoint) {
        try {
            return Unirest.get(baseUrl + endpoint).asJson().getBody().getObject();
        } catch (UnirestException e) {
            e.printStackTrace();
        }
        System.err.println("Something went wrong performing the request on this endpoint:\n" + endpoint);
        return new JSONObject();
    }
}
