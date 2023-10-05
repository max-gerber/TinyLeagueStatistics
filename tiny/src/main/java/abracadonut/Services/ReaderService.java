package abracadonut.Services;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

import abracadonut.Objects.Deck;
import abracadonut.Services.Utils.ReaderServiceUtils;

public class ReaderService {
    
    public static List<Deck> getDecks(String decklistsFilePath) {
        List<String> deckIds = getDeckIds(decklistsFilePath);
        List<Deck> decks = new ArrayList<>();

        for (String deckId : deckIds) {
            try {
                decks.add(new Deck(deckId));
            } catch (Exception e) {
                System.err.println("There was an error adding a deck : https://www.moxfield.com/decks/"+ deckId +"\n\n" + e);
            }
        }
        return decks;
    }

    private static List<String> getDeckIds(String decklistsFilePath) {
        List<String> decks = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(decklistsFilePath))) {
            String deckUrl;

            while ((deckUrl = br.readLine()) != null) {
                String deckId = ReaderServiceUtils.extractDeckId(deckUrl);
                
                if (deckId != "") {
                    decks.add(deckId);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return decks;
    }
}
