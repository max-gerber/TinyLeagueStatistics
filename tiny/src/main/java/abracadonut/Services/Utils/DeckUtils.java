package abracadonut.Services.Utils;

import java.util.*;

import abracadonut.Objects.Card;
import abracadonut.Objects.CardStatistic;
import abracadonut.Objects.Deck;

public class DeckUtils {
    public static List<CardStatistic> getCardStatistics(List<Deck> decks) {
        Map<String, CardStatistic> cardStatisticsMap = new HashMap<String, CardStatistic>();

        for (Deck deck : decks) {
            List<CardStatistic> deckStatistics = getCardStatistics(deck);

            for (CardStatistic card : deckStatistics) {
                String cardName = card.getCard().getName();

                if (cardStatisticsMap.keySet().contains(cardName)) {
                    cardStatisticsMap.get(cardName).incrementMainboard(card.getMainboardCount());
                    cardStatisticsMap.get(cardName).incrementSideboard(card.getSideboardCount());
                } else {
                    cardStatisticsMap.put(cardName, card);
                }
            }
        }

        return new ArrayList<CardStatistic>(cardStatisticsMap.values());
    }

    public static List<CardStatistic> getCardStatistics(Deck deck) {
        Map<String, CardStatistic> cardStatisticsMap = new HashMap<String, CardStatistic>();

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

        return new ArrayList<CardStatistic>(cardStatisticsMap.values());
    }
}
