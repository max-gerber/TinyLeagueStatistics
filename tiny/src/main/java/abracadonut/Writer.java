package abracadonut;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;

public class Writer {

    public static void writeDeckInfoToCSV(List<Deck> decks) {
        Statistics statistics = new Statistics(decks);
        String csvFilePath = "statisticsPreTransposition.csv";

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(csvFilePath))) {
            writeStatistics(writer, statistics.getMainboardCards(), "Mainboard Cards");
            writeStatistics(writer, statistics.getSideboardCards(), "Sideboard Cards");
            writeStatistics(writer, statistics.getCommanders(), "Commanders");
            writeStatistics(writer, statistics.getColours(), "Colours");
            writeStatistics(writer, statistics.getColourCombinations(), "Colour Combinations");
            writeStatistics(writer, statistics.getCreatures(), "Creatures");
            writeStatistics(writer, statistics.getInstants(), "Instants");
            writeStatistics(writer, statistics.getSorceries(), "Sorceries");
            writeStatistics(writer, statistics.getEnchantments(), "Enchantments");
            writeStatistics(writer, statistics.getArtifacts(), "Artifacts");
            writeStatistics(writer, statistics.getLands(), "Lands");
            writeStatistics(writer, statistics.getPlaneswalkers(), "Planeswalkers");
            writeStatistics(writer, statistics.getBattles(), "Battles");
            writeStatistics(writer, statistics.getManaValues(), "Mana Values");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void writeStatistics(BufferedWriter writer, Map<String, Integer> data, String property) throws IOException {
        writeProperty(writer, data, property);
        writeQunatity(writer, data);
    }

    private static void writeProperty(BufferedWriter writer, Map<String, Integer> data, String property) throws IOException {
        writer.write(property + ",,");
        for (Map.Entry<String, Integer> entry : data.entrySet()) {
            writer.write(entry.getKey().replace(",", "") + ",");
        }
        writer.newLine();
    }

    private static void writeQunatity(BufferedWriter writer, Map<String, Integer> data) throws IOException {
        writer.write("Quantity,,");
        for (Map.Entry<String, Integer> entry : data.entrySet()) {
            writer.write(entry.getValue() + ",");
        }
        writer.newLine();
    }

    public static void writeDeckInfoToFile(List<Deck> decks) {
        Statistics stats = new Statistics(decks);
        writeMapToFile(stats.mainboardCards, "Mainboard Cards.txt");
        writeMapToFile(stats.sideboardCards, "Sideboard Cards.txt");
        writeMapToFile(stats.commanders, "Commanders.txt");
        writeMapToFile(stats.colours, "Colours.txt");
        writeMapToFile(stats.creatures, "Creatures.txt");
        writeMapToFile(stats.instants, "Instants.txt");
        writeMapToFile(stats.sorceries, "Sorceries.txt");
        writeMapToFile(stats.enchantments, "Enchantments.txt");
        writeMapToFile(stats.artifacts, "Artifacts.txt");
        writeMapToFile(stats.lands, "Lands.txt");
        writeMapToFile(stats.planeswalkers, "Planeswalkers.txt");
        writeMapToFile(stats.battles, "Battles.txt");
    }

    private static void writeMapToFile(Map<String, Integer> data, String fileName) {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
        try {
            ObjectWriter objectWriter = objectMapper.writerWithDefaultPrettyPrinter();
            objectWriter.writeValue(new FileWriter(fileName), data);
            System.out.println("Successfully wrote data to " + fileName);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
