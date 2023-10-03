package abracadonut.Services.Utils;

import java.util.*;

import abracadonut.Objects.Card;
import abracadonut.Objects.CardStatistic;
import abracadonut.Objects.Deck;

public class DeckUtils {
    public static List<CardStatistic> getCardStatistics(List<Deck> decks) {
        Map<String, CardStatistic> cardStatisticsMap = new HashMap<String, CardStatistic>();

        for (Deck deck : decks) {
            for (Card mainboardCard : deck.getMainboard()) {
                String cardName = mainboardCard.getName();
                if (cardStatisticsMap.keySet().contains(cardName)) {
                    cardStatisticsMap.get(cardName).incrementMainboard();;
                } else {
                    cardStatisticsMap.put(cardName, new CardStatistic(mainboardCard, true));
                }
            }
            for (Card sideboardCard : deck.getSideboard()) {
                String cardName = sideboardCard.getName();
                if (cardStatisticsMap.keySet().contains(cardName)) {
                    cardStatisticsMap.get(cardName).incrementSideboard();
                } else {
                    cardStatisticsMap.put(cardName, new CardStatistic(sideboardCard, false));
                }
            }
        }

        return new ArrayList<CardStatistic>(cardStatisticsMap.values());
    }
}
