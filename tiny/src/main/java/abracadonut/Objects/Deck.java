package abracadonut.Objects;

import java.util.*;

import org.json.JSONObject;

import abracadonut.Services.MoxfieldApiService;
import abracadonut.Services.Utils.ReaderServiceUtils;

public class Deck {
    private String url;
    private String name;
    private String creator;
    private List<Card> commanders;
    private boolean hasPartner;
    private String colourIdentity;
    private List<Card> mainboard;
    private List<Card> sideboard;

    public Deck(String deckId) throws Exception{
        JSONObject deckInfo = MoxfieldApiService.getDecklist(deckId);

        try {
            url = "moxfield.com/decks/" + deckId;
            name = deckInfo.getString("name");
            creator = deckInfo.getJSONObject("createdByUser").getString("userName");
            commanders = ReaderServiceUtils.extractCommanders(deckInfo.getJSONObject("commanders"));
            hasPartner = commanders.size() == 2;
            colourIdentity = ReaderServiceUtils.extractColourIdentity(commanders);
            mainboard = ReaderServiceUtils.extractCards(deckInfo.getJSONObject("mainboard"));
            sideboard = ReaderServiceUtils.extractCards(deckInfo.getJSONObject("sideboard"));
        } catch (Exception e) {
            throw e;
        }
    }

    public String getUrl() {
        return url;
    }

    public String getName() {
        return name;
    }

    public String getCreator() {
        return creator;
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
