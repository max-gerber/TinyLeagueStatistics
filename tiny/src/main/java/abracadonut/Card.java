package abracadonut;

import java.util.*;

import org.json.JSONArray;
import org.json.JSONObject;

public class Card {

    String name;
    String manaCost;
    int manaValue;
    String colours = "";
    String colourIdentity = "";
    String power = "";
    String toughness = "";
    SuperTypes superTypes;
    String subTypes = "";

    public Card(JSONObject cardData) {
        setName(cardData.getString("name"));
        setManaValue(cardData.getDouble("cmc"));
        setColours(cardData.getJSONArray("colors"));
        setColourIdentity(cardData.getJSONArray("color_identity"));
        if (isDoubleFacedCard(cardData)) {
            handleDoubleFacedCardData(cardData);
        } else {
            handleSingleFacedCardData(cardData);
        }
    }

    private void handleSingleFacedCardData(JSONObject cardData) {
        setManaCost(cardData.getString("mana_cost"));
        setTypes(cardData.getString("type_line"));
        if (superTypes.isCreature()){
            setPower(cardData.getString("power"));
            setToughness(cardData.getString("toughness"));
        }
    }

    private void handleDoubleFacedCardData(JSONObject cardData) {
        setManaCost(cardData.getJSONArray("card_faces").getJSONObject(0).getString("mana_cost"));
        setTypes(cardData.getString("type_line"));
        if (superTypes.isCreature()){
            setPower(cardData.getJSONArray("card_faces").getJSONObject(0).getString("power"));
            setToughness(cardData.getJSONArray("card_faces").getJSONObject(0).getString("toughness"));
        }
    }

    private boolean isDoubleFacedCard(JSONObject cardData) {
        if (cardData.getJSONArray("card_faces").length() > 1) {
            return true;
        }
        return false;
    }

    private void setName(String name) {
        this.name = name.replaceAll(",", "");
    }

    private void setManaCost(String manaCost) {
        this.manaCost = manaCost;
    }
    
    private void setManaValue(Double manaValue) {
        this.manaValue = manaValue.intValue();
    }

    private void setPower(String power) {
        this.power = power;
    }

    private void setToughness(String toughness) {
        this.toughness = toughness;
    }

    private void setColourIdentity(JSONArray colourIdentity) {
        for (Object colour : colourIdentity) {
            this.colourIdentity += colour.toString();
        }
    }

    private void setColours(JSONArray colours) {
        for (Object colour : colours) {
            this.colours += colour.toString();
        }
    }

    private void setTypes(String typeLine) {
        String[] types = typeLine.split(" — ");
        List<String> superTypeList = Arrays.asList(types[0].split(" "));
        superTypes = new SuperTypes(superTypeList);
        if (types.length == 2) {
            subTypes = typeLine.split(" — ")[1];
        }
    }

    public String getName() {
        return name;
    }

    public String getColours() {
        return colours;
    }

    public SuperTypes getSuperTypes() {
        return superTypes;
    }

    public String getSubTypes() {
        return subTypes;
    }

    public int getManaValue() {
        return manaValue;
    }
    public String getManaCost() {
        return manaCost;
    }

    public String getColourIdentity() {
        return colourIdentity;
    }

    public String getPower() {
        return power;
    }

    public String getToughness() {
        return toughness;
    }
}
