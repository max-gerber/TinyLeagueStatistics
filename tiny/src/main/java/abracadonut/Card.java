package abracadonut;

import java.util.*;

import org.json.JSONArray;
import org.json.JSONObject;

public class Card {

    String name;
    List<String> colours;
    List<String> superTypes;
    List<String> subTypes;
    int manaValue;

    public Card(JSONObject cardData) {
        setTypes(cardData.getString("type_line"));
        setColours(cardData.getJSONArray("color_identity"));
        setName(cardData.getString("name"));
        setManaValue(cardData.getDouble("cmc"));
    }

    private void setName(String cardName) {
        name = cardName;
    }

    private void setTypes(String typeLine) {
        String[] types = typeLine.split(" — ");
        superTypes = Arrays.asList(types[0].split(" "));
        if (types.length == 2) {
            subTypes = Arrays.asList(typeLine.split(" — ")[1].split(" "));
        }
    }

    private void setColours(JSONArray colourIdentity) {
        colours = new ArrayList<String>();
        for (Object colour : colourIdentity) {
            colours.add(colour.toString());
        }
    }
    
    private void setManaValue(Double cmc) {
        manaValue = cmc.intValue();
    }

    public String getName() {
        return name;
    }

    public List<String> getColours() {
        return colours;
    }

    public List<String> getSuperTypes() {
        return superTypes;
    }

    public List<String> getSubTypes() {
        return subTypes;
    }

    public int getManaValue() {
        return manaValue;
    }
}
