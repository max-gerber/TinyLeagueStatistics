package abracadonut;

import java.util.*;
import java.util.stream.Collectors;

public class Statistics {
    Map<String, Integer> mainboardCards = new HashMap<String, Integer>();
    Map<String, Integer> sideboardCards = new HashMap<String, Integer>();
    Map<String, Integer> commanders = new HashMap<String, Integer>();
    Map<String, Integer> colours = new HashMap<String, Integer>();
    Map<String, Integer> colourCombinations = new HashMap<String, Integer>();
    Map<String, Integer> creatures = new HashMap<String, Integer>();
    Map<String, Integer> instants = new HashMap<String, Integer>();
    Map<String, Integer> sorceries = new HashMap<String, Integer>();
    Map<String, Integer> enchantments = new HashMap<String, Integer>();
    Map<String, Integer> artifacts = new HashMap<String, Integer>();
    Map<String, Integer> lands = new HashMap<String, Integer>();
    Map<String, Integer> planeswalkers = new HashMap<String, Integer>();
    Map<String, Integer> battles = new HashMap<String, Integer>();
    Map<String, Integer> manaValues = new HashMap<String, Integer>();
    
    public Statistics(List<Deck> decks) {
        
        for (Deck deck : decks) {
            populateFields(deck);
        }

        sortFields();
    }

    private void populateFields(Deck deck) {
        for (Map.Entry<Card, Integer> entry : deck.getMainboard().entrySet()) {
            Card card = entry.getKey();
            int ammount = entry.getValue();
            String name = card.name;
            String mv = Integer.toString(card.manaValue);

            mainboardCards.put(name, mainboardCards.getOrDefault(name, 0) + ammount);
            if (!card.superTypes.contains("Land")) {
                manaValues.put(mv, manaValues.getOrDefault(mv, 0) + 1);
            }
            for (String colour : card.colours) {
                colours.put(colour, colours.getOrDefault(colour, 0) + 1); 
            }  
            if (card.superTypes.contains("Creature")) {
                creatures.put(name, creatures.getOrDefault(name, 0) + ammount);
            }
            if (card.superTypes.contains("Instant")) {
                instants.put(name, instants.getOrDefault(name, 0) + ammount);
            }
            if (card.superTypes.contains("Sorcery")) {
                sorceries.put(name, sorceries.getOrDefault(name, 0) + ammount);
            }
            if (card.superTypes.contains("Enchantment")) {
                enchantments.put(name, enchantments.getOrDefault(name, 0) + ammount);
            }
            if (card.superTypes.contains("Artifact")) {
                artifacts.put(name, artifacts.getOrDefault(name, 0) + ammount);
            }
            if (card.superTypes.contains("Land")) {
                lands.put(name, lands.getOrDefault(name, 0) + ammount);
            }
            if (card.superTypes.contains("Planeswalker")) {
                planeswalkers.put(name, planeswalkers.getOrDefault(name, 0) + ammount);
            }
            if (card.superTypes.contains("Battle")) {
                battles.put(name, battles.getOrDefault(name, 0) + ammount);
            }
        }

        for (Map.Entry<Card, Integer> entry : deck.getSideboard().entrySet()) {
            Card card = entry.getKey();
            int ammount = entry.getValue();
            sideboardCards.put(card.name, sideboardCards.getOrDefault(card.name, 0) + ammount);
        }

        String commanderName = deck.getCommanders().get(0).name;
        if (deck.hasPartner()) {
            commanderName += " + " + deck.getCommanders().get(1).name;
        }
        commanders.put(commanderName, commanders.getOrDefault(commanderName, 0) + 1);

        colourCombinations.put(deck.getColourIdentity(), colourCombinations.getOrDefault(deck.getColourIdentity(), 0) + 1);
    }

    private void sortFields() {
        mainboardCards = sortMap(mainboardCards);
        sideboardCards = sortMap(sideboardCards);
        commanders = sortMap(commanders);
        colours = sortMap(colours);
        colourCombinations = sortMap(colourCombinations);
        creatures = sortMap(creatures);
        instants = sortMap(instants);
        sorceries = sortMap(sorceries);
        enchantments = sortMap(enchantments);
        artifacts = sortMap(artifacts);
        planeswalkers = sortMap(planeswalkers);
        lands = sortMap(lands);
        battles = sortMap(battles);
        manaValues = sortMap(manaValues);
    }

    private Map<String, Integer> sortMap(Map<String, Integer> unsortedMap) {
        Map<String, Integer> sortedMap = unsortedMap.entrySet()
                .stream()
                .sorted(Map.Entry.<String, Integer>comparingByValue().reversed())
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue,
                        (e1, e2) -> e1, LinkedHashMap::new));
        return sortedMap;
    }

    public Map<String, Integer> getMainboardCards() {
        return mainboardCards;
    }

    public Map<String, Integer> getSideboardCards() {
        return sideboardCards;
    }

    public Map<String, Integer> getCommanders() {
        return commanders;
    }

    public Map<String, Integer> getColours() {
        return colours;
    }

    public Map<String, Integer> getColourCombinations() {
        return colourCombinations;
    }

    public Map<String, Integer> getCreatures() {
        return creatures;
    }

    public Map<String, Integer> getInstants() {
        return instants;
    }

    public Map<String, Integer> getSorceries() {
        return sorceries;
    }

    public Map<String, Integer> getEnchantments() {
        return enchantments;
    }

    public Map<String, Integer> getArtifacts() {
        return artifacts;
    }

    public Map<String, Integer> getLands() {
        return lands;
    }

    public Map<String, Integer> getPlaneswalkers() {
        return planeswalkers;
    }

    public Map<String, Integer> getBattles() {
        return battles;
    }

    public Map<String, Integer> getManaValues() {
        return manaValues;
    }
}
