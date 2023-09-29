package abracadonut;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class DecklistReader {
    
    public static List<Deck> getDecks(String decklistsFilePath) {
        List<String> deckIds = getDeckIds(decklistsFilePath);
        List<Deck> decks = new ArrayList<>();

        for (String deckId : deckIds) {
            decks.add(new Deck(deckId));
        }
        return decks;
    }

    private static List<String> getDeckIds(String decklistsFilePath) {
        List<String> decks = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(decklistsFilePath))) {
            String deckUrl;

            while ((deckUrl = br.readLine()) != null) {
                String deckId = extractDeckId(deckUrl);
                
                if (deckId != "") {
                    decks.add(deckId);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return decks;
    }

    private static String extractDeckId(String deckUrl) {
        int lastIndex = deckUrl.lastIndexOf("/");

        if (lastIndex != -1) {
            return deckUrl.substring(lastIndex + 1);
        } else {
            System.out.println(deckUrl + " is not a valid URL");
            return "";
        }
    }
}
