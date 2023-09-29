package abracadonut;

import java.util.*;

import org.json.JSONObject;

public class Deck {
    private List<Card> commanders;
    private boolean hasPartner;
    private String colourIdentity;
    private Map<String, Float> colourPercentages;
    private Map<Card, Integer> mainboard;
    private Map<Card, Integer> sideboard;
    private Card companion;

    public Deck(String deckId) {
        JSONObject deckInfo = MoxfieldApi.getDecklist(deckId);

        try {
            commanders = DeckUtils.extractCommandersFromJson(deckInfo.getJSONObject("commanders"));
            hasPartner = commanders.size() == 2;
            colourIdentity = DeckUtils.extractColourIdentityFromCommanders(commanders);
            mainboard = DeckUtils.extractCardsFromJson(deckInfo.getJSONObject("mainboard"));
            sideboard = DeckUtils.extractCardsFromJson(deckInfo.getJSONObject("sideboard"));
            if (hasCompanions(deckInfo)) {
                companion = DeckUtils.extractCompanionFromJson(deckInfo.getJSONObject("companions"));
            }
        } catch (Exception e) {
            System.err.println("There was an error adding a deck: \n"+ deckInfo.toString(2) +"\n\n" + e);
        }
    }

    private boolean hasCompanions(JSONObject deckInfo) {
        return (Integer) deckInfo.get("companionsCount") != 0;
    }

    public List<Card> getCommanders() {
        return commanders;
    }

    public boolean hasPartner() {
        return hasPartner;
    }

    public Card getCompanion() {
        return companion;
    }

    public String getColourIdentity() {
        return colourIdentity;
    }

    public Map<String, Float> getColourPercentages() {
        return colourPercentages;
    }

    public Map<Card, Integer> getMainboard() {
        return mainboard;
    }

    public Map<Card, Integer> getSideboard() {
        return sideboard;
    }
}
