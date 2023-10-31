package abracadonut.Services.Utils;

import java.io.BufferedWriter;
import java.io.IOException;

import abracadonut.Objects.Card;
import abracadonut.Objects.CardStatistic;
import abracadonut.Objects.Deck;
import abracadonut.Objects.SuperTypes;

public class WriterServiceUtils {

    public static void writeCardDataHeader(BufferedWriter writer) throws IOException {
        writer.write("Name,");
        writer.write("Mainboard Count,");
        writer.write("Sideboard Count,");
        writer.write("Mana Cost,");
        writer.write("Mana Value,");
        writer.write("Colours,");
        writer.write("Colour Identity,");
        writer.write("Power,");
        writer.write("Toughness,");
        writer.write("Subtypes,");
        writer.write("Is Creature,");
        writer.write("Is Planeswalker,");
        writer.write("Is Artifact,");
        writer.write("Is Enchantment,");
        writer.write("Is Instant,");
        writer.write("Is Sorcery,");
        writer.write("Is Battle,");
        writer.write("Is Land,");
        writer.newLine();
    }

    public static void writeCommanderDataHeader(BufferedWriter writer) throws IOException {
        writer.write("Placement,");
        writer.write("Deck Name,");
        writer.write("User,");
        writer.write("Moxfield Link,");
        writer.write("Colour Identity,");
        writer.write("Commander,");
        writer.write("Mana Cost,");
        writer.write("Mana Value,");
        writer.write("Colours,");
        writer.write("Colour Identity,");
        writer.write("Power,");
        writer.write("Toughness,");
        writer.write("Subtypes,");
        writer.write("Is Creature,");
        writer.write("Is Planeswalker,");
        writer.write("Is Artifact,");
        writer.write("Is Enchantment,");
        writer.newLine();
    }

    public static void writeCardData(BufferedWriter writer, CardStatistic cardStatistic) throws IOException {
        Card card = cardStatistic.getCard();
        writer.write(cleanName(card.getName()) + ",");
        writer.write(cardStatistic.getMainboardCount() + ",");
        writer.write(cardStatistic.getSideboardCount() + ",");
        writer.write(card.getManaCost() + ",");
        writer.write(card.getManaValue() + ",");
        writer.write(card.getColours() + ",");
        writer.write(card.getColourIdentity() + ",");
        writer.write(card.getPower() + ",");
        writer.write(card.getToughness() + ",");
        writer.write(card.getSubTypes() + ",");
        writeSuperTypes(writer, card.getSuperTypes(), false);
        writer.newLine();
    }

    public static void writeCommandersData(BufferedWriter writer, Deck deck, int placementCount) throws IOException {
        writer.write(placementCount + ",");
        writer.write(cleanName(deck.getName()) + ",");
        writer.write(cleanName(deck.getCreator()) + ",");
        writer.write(deck.getUrl() + ",");
        writer.write(deck.getColourIdentity() + ",");
        writeCommanderData(writer, deck.getCommanders().get(0), false);
        if (deck.hasPartner()) {
            writer.newLine();
            writeCommanderData(writer, deck.getCommanders().get(1), true);
        }
        writer.newLine();
    }

    private static void writeCommanderData(BufferedWriter writer, Card commander, boolean isPartner) throws IOException {
        if (isPartner) {
            writer.write(",,,,,");
        }
        writer.write(cleanName(commander.getName()) + ",");
        writer.write(commander.getManaCost() + ",");
        writer.write(commander.getManaValue() + ",");
        writer.write(commander.getColours() + ",");
        writer.write(commander.getColourIdentity() + ",");
        writer.write(commander.getPower() + ",");
        writer.write(commander.getToughness() + ",");
        writer.write(commander.getSubTypes() + ",");
        writeSuperTypes(writer, commander.getSuperTypes(), true);
    }

    private static void writeSuperTypes(BufferedWriter writer, SuperTypes superTypes, boolean isCommander) throws IOException {
        writer.write((superTypes.isCreature() ? 1 : 0) + ",");
        writer.write((superTypes.isPlaneswalker() ? 1 : 0) + ",");
        writer.write((superTypes.isArtifact() ? 1 : 0) + ",");
        writer.write((superTypes.isEnchantment() ? 1 : 0) + ",");
        if (!isCommander) {
            writer.write((superTypes.isInstant() ? 1 : 0) + ",");
            writer.write((superTypes.isSorcery() ? 1 : 0) + ",");
            writer.write((superTypes.isBattle() ? 1 : 0) + ",");
            writer.write((superTypes.isLand() ? 1 : 0) + ",");
        }
    }

    private static String cleanName(String name) {
        return name.replaceAll(",", "");
    }
}
