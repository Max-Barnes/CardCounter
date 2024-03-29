import java.io.PrintWriter;
import java.util.LinkedList;
import java.util.List;

public class Hand extends Card{

    private List<Card> hand = new LinkedList<>();

    private int handValue = 0;

    public List<Card> getHand() {
        return hand;
    }

    public void addToHand(Card card) {
        hand.add(card);
    }

    public int getHandValue(List<Card> hand) {
        int handVal = 0;
        int cardVal = 0;
        int acesInHand = 0;
        for (Card each : hand) {

            cardVal = each.getRank();
            if (cardVal == 1) {
                acesInHand++;
                cardVal = 11;


                handVal += cardVal;

            } else if (cardVal >= 11) {
                // card is face card and worth 10
                cardVal = 10;
                handVal += cardVal;
            } else {
                handVal += cardVal;
            }

        }
        while (handVal > 21 && acesInHand > 0) {
            handVal -= 10;
            acesInHand--;
        }

        return handVal;
    }
    public boolean isBlackjack(Hand checkHand) {
        if (checkHand.getHandValue(checkHand.getHand()) == 21) {
            return true;
        } else {
            return false;
        }
    }


}
