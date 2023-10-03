package abracadonut;

import java.util.*;

import abracadonut.Objects.Deck;
import abracadonut.Services.ReaderService;
import abracadonut.Services.WriterService;

public class Main {

    static String decklistsFilePath = "decklists.txt";

    public static void main(String[] args) {
        List<Deck> decks = new ArrayList<>();

        decks = ReaderService.getDecks(decklistsFilePath);

        WriterService.writeToCSV(decks);
    }
}