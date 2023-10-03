package abracadonut.Services.Utils;

import java.util.*;

import org.json.JSONObject;

import abracadonut.Objects.Card;

public class ReaderServiceUtils {

    public static List<Card> extractCards(JSONObject cardsJson) {
        List<Card> cards = new ArrayList<Card>();
        Iterator<String> keys = cardsJson.keys();
        while(keys.hasNext()) {
            String key = keys.next();
            JSONObject cardObject = cardsJson.getJSONObject(key);
            Card card = new Card(cardObject.getJSONObject("card"));
            if (!cards.contains(card)) {
                cards.add(card);
            }
        }
        return cards;
    }

    public static Card extractCompanion(JSONObject companions) {
        return new Card(companions.getJSONObject(companions.keys().next()).getJSONObject("card"));
    }

    public static List<Card> extractCommanders(JSONObject commanders) {
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

    public static String extractColourIdentity(List<Card> commanders) {
        String colourIdentity = "";
        for (Card commander : commanders) {
            for (char colour : commander.getColours().toCharArray()) {
                if (colourIdentity.indexOf(String.valueOf(colour)) == -1) {
                    colourIdentity += colour;
                }
            }
        }
        return colourIdentity;
    }

    public static String extractDeckId(String deckUrl) {
        int lastIndex = deckUrl.lastIndexOf("/");

        if (lastIndex != -1) {
            return deckUrl.substring(lastIndex + 1);
        } else {
            System.out.println(deckUrl + " is not a valid URL");
            return "";
        }
    }
}
