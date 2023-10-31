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
        writeCards(decks);
        writeCommanders(decks);
        writeDecks(decks);
    }

    private static void writeCards(List<Deck> decks) {
        String csvFilePath = "Card Data.csv";
        List<CardStatistic> cardStatistics = DeckUtils.getCardStatistics(decks);

        Collections.sort(cardStatistics);

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(csvFilePath))) {
            WriterServiceUtils.writeCardDataHeader(writer);

            for (CardStatistic cardStatistic : cardStatistics) {
                WriterServiceUtils.writeCardData(writer, cardStatistic);
            }

            System.out.println("Successfully wrote data to " + csvFilePath);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void writeCommanders(List<Deck> decks) {
        String csvFilePath = "Commander Data.csv";

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(csvFilePath))) {
            WriterServiceUtils.writeCommanderDataHeader(writer);
            int placementCount = 0;

            for (Deck deck : decks) {
                WriterServiceUtils.writeCommandersData(writer, deck, ++placementCount);
            }

            System.out.println("Successfully wrote data to " + csvFilePath);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void writeDecks(List<Deck> decks) {
        for (Deck deck : decks) {
            String csvFilePath = cleanName(deck.getName()) + " - " + cleanName(deck.getCreator()) + ".csv";
            List<CardStatistic> cardStatistics = DeckUtils.getCardStatistics(deck);

            try (BufferedWriter writer = new BufferedWriter(new FileWriter(csvFilePath))) {
                WriterServiceUtils.writeCardDataHeader(writer);
                
                for (CardStatistic cardStatistic : cardStatistics) {
                    WriterServiceUtils.writeCardData(writer, cardStatistic);
                }

                System.out.println("Successfully wrote data to " + csvFilePath);

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private static String cleanName(String name) {
        return name.replaceAll("/", "").replaceAll(":", "");
    }
}
