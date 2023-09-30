package abracadonut;

import java.util.*;

public class Main {

    static String decklistsFilePath = "decklists.txt";

    public static void main(String[] args) {
        List<Deck> decks = new ArrayList<>();

        decks = DecklistReader.getDecks(decklistsFilePath);

        // DeckWriter.writeDeckInfoToFile(decks);
        Writer.writeCardInfoToCSV(decks);
        // CSVTransposer.transposeCSV();
    }
}