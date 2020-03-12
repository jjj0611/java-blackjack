package blackjack.domain.gamer;

public class Dealer extends Gamer {

    private static final String DEALER_NAME = "딜러";
    private static final int DRAW_THRESHOLD = 16;
    private static final int FIRST_CARD_INDEX = 0;

    public boolean shouldDrawCard() {
        return calculateSum() <= DRAW_THRESHOLD;
    }

    @Override
    public String getName() {
        return DEALER_NAME;
    }

    public String getInitialCardStatus() {
        return hand.getHand().get(FIRST_CARD_INDEX).toString();
    }
}