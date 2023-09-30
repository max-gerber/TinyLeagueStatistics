package abracadonut;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class Writer {

    public static void writeCardInfoToCSV(List<Deck> decks) {
        String csvFilePath = "statistics.csv";
        List<CardStatistic> cardStatistics = CardStatisticsUtils.getCardStatistics(decks);

        //sort CardStatistics?

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(csvFilePath))) {
            writeHeader(writer);
            for (CardStatistic cardStatistic : cardStatistics) {
                writeCardStatisticData(writer, cardStatistic);
            }
            System.out.println("Successfully wrote data to " + csvFilePath);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void writeCardStatisticData(BufferedWriter writer, CardStatistic cardStatistic) throws IOException {
        Card card = cardStatistic.card;
        writer.write(card.getName() + ",");
        writer.write(cardStatistic.getMainboardCount() + ",");
        writer.write(cardStatistic.getSideboardCount() + ",");
        writer.write(card.getManaCost() + ",");
        writer.write(card.getManaValue() + ",");
        writer.write(card.getColours() + ",");
        writer.write(card.getColourIdentity() + ",");
        writer.write(card.getPower() + ",");
        writer.write(card.getToughness() + ",");
        writer.write(card.getSubTypes() + ",");
        writeSuperTypes(writer, card.superTypes);
        writer.newLine();
    }

    private static void writeHeader(BufferedWriter writer) throws IOException {
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
        writer.write("Is Instant,");
        writer.write("Is Sorcery,");
        writer.write("Is Enchantment,");
        writer.write("Is Artifact,");
        writer.write("Is Land,");
        writer.write("Is Planeswalker,");
        writer.write("Is Battle,");
        writer.newLine();
    }

    private static void writeSuperTypes(BufferedWriter writer, SuperTypes superTypes) throws IOException {
        writer.write((superTypes.isCreature() ? 1 : 0) + ",");
        writer.write((superTypes.isInstant() ? 1 : 0) + ",");
        writer.write((superTypes.isSorcery() ? 1 : 0) + ",");
        writer.write((superTypes.isEnchantment() ? 1 : 0) + ",");
        writer.write((superTypes.isArtifact() ? 1 : 0) + ",");
        writer.write((superTypes.isLand() ? 1 : 0) + ",");
        writer.write((superTypes.isPlaneswalker() ? 1 : 0) + ",");
        writer.write((superTypes.isBattle() ? 1 : 0) + ",");
    }
}
