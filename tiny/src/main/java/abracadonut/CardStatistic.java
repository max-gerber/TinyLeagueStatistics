package abracadonut;

public class CardStatistic {

    Card card;
    int mainboardCount = 0;
    int sideboardCount = 0;

    public CardStatistic(Card card, boolean mainboard) {
        this.card = card;
        if (mainboard) {
            incrementMainboard();
        } else {
            incrementSideboard();
        }
    }

    public void incrementMainboard() {
        mainboardCount++;
    }

    public void incrementSideboard() {
        sideboardCount++;
    }
    
    public Card getCard() {
        return card;
    }

    public int getMainboardCount() {
        return mainboardCount;
    }

    public int getSideboardCount() {
        return sideboardCount;
    }
}
