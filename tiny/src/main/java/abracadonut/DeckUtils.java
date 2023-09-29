package abracadonut;

import java.util.*;

import org.json.JSONObject;

public class DeckUtils {

    public static Map<Card, Integer> extractCardsFromJson(JSONObject cardsJson) {
        Map<Card, Integer> cards = new HashMap<>();
        Iterator<String> keys = cardsJson.keys();
        while(keys.hasNext()) {
            String key = keys.next();
            JSONObject cardObject = cardsJson.getJSONObject(key);
            Card card = new Card(cardObject.getJSONObject("card"));
            cards.put(card, cards.getOrDefault(card, 0) + 1);
        }
        return cards;
    }

    public static Map<String, Float> extractColourPercentagesFromJson(JSONObject colourPercentagesJson) {
        Map<String, Float> colourPercentages = new HashMap<>();
        Iterator<String> keys = colourPercentagesJson.keys();
        while(keys.hasNext()) {
            String key = keys.next();
            Float value = Float.parseFloat(colourPercentagesJson.get(key).toString());
            colourPercentages.put(key, value);
        }
        return colourPercentages;
    }

    public static Card extractCompanionFromJson(JSONObject companions) {
        return new Card(companions.getJSONObject(companions.keys().next()).getJSONObject("card"));
    }

    public static List<Card> extractCommandersFromJson(JSONObject commanders) {
        List<Card> commanderCards = new ArrayList<Card>();
        List<String> commanderNames = new ArrayList<String>(commanders.keySet());
        JSONObject commanderObject = (JSONObject) commanders.get(commanderNames.get(0));
        commanderCards.add(new Card((JSONObject) commanderObject.get("card")));
        if (commanderNames.size() > 1) {
            JSONObject partnerObject = (JSONObject) commanders.get(commanderNames.get(1));
            commanderCards.add(new Card((JSONObject) partnerObject.get("card")));
        }
        return commanderCards;
    }

    public static String extractColourIdentityFromCommanders(List<Card> commanders) {
        String colourIdentity = "";
        for (Card commander : commanders) {
            for (String colour : commander.colours) {
                if (!colourIdentity.contains(colour)) {
                    colourIdentity += colour;
                }
            }
        }
        return colourIdentity;
    }
}
