package abracadonut;

import java.util.*;

import org.json.JSONObject;

public class Deck {
    private List<Card> commanders;
    private boolean hasPartner;
    private String colourIdentity;
    private List<Card> mainboard;
    private List<Card> sideboard;

    public Deck(String deckId) {
        JSONObject deckInfo = MoxfieldApi.getDecklist(deckId);

        try {
            commanders = DeckUtils.extractCommandersFromJson(deckInfo.getJSONObject("commanders"));
            hasPartner = commanders.size() == 2;
            colourIdentity = DeckUtils.extractColourIdentityFromCommanders(commanders);
            mainboard = DeckUtils.extractCardsFromJson(deckInfo.getJSONObject("mainboard"));
            sideboard = DeckUtils.extractCardsFromJson(deckInfo.getJSONObject("sideboard"));
        } catch (Exception e) {
            System.err.println("There was an error adding a deck: \n"+ deckInfo.toString(2) +"\n\n" + e);
        }
    }

    public List<Card> getCommanders() {
        return commanders;
    }

    public boolean hasPartner() {
        return hasPartner;
    }

    public String getColourIdentity() {
        return colourIdentity;
    }

    public List<Card> getMainboard() {
        return mainboard;
    }

    public List<Card> getSideboard() {
        return sideboard;
    }
}
