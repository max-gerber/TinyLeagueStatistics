package abracadonut.Services;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

import abracadonut.Objects.CardStatistic;
import abracadonut.Objects.Deck;
import abracadonut.Services.Utils.DeckUtils;
import abracadonut.Services.Utils.WriterServiceUtils;

public class WriterService {

    public static void writeToCSV(List<Deck> decks) {
        writeCardStatistics(decks);
        writeDeckData(decks);
    }

    private static void writeCardStatistics(List<Deck> decks) {
        String csvFilePath = "Card Statistics.csv";
        List<CardStatistic> cardStatistics = DeckUtils.getCardStatistics(decks);

        Collections.sort(cardStatistics);

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(csvFilePath))) {
            WriterServiceUtils.writeCardsHeader(writer);

            for (CardStatistic cardStatistic : cardStatistics) {
                WriterServiceUtils.writeCardData(writer, cardStatistic);
            }
            System.out.println("Successfully wrote data to " + csvFilePath);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void writeDeckData(List<Deck> decks) {
        String csvFilePath = "Deck Data.csv";

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(csvFilePath))) {
            WriterServiceUtils.writeDecksHeader(writer);
            int placementCount = 0;

            for (Deck deck : decks) {
                WriterServiceUtils.writeDeckData(writer, deck, ++placementCount);
            }
            System.out.println("Successfully wrote data to " + csvFilePath);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
