import java.util.List;

public class Card {

    // card is two values, rank and suit
    private int rank; // 1 is ace 11 is jack 12 is queen, 13 is king

    private int suit; // 0 is spades, 1 is hearts, 2 is clubs, 3 is diamonds


    boolean isFaceUp = true;

    public Card() {

    }
    public Card(int rank, int suit) {
        this.rank = rank;
        this.suit = suit;
    }

    public int getRank() {
        return rank;
    }

    public int getSuitInt() {
        return suit;
    }

    public String getSuitString() {
        if (this.suit == 0) {
            return "Spades";
        } else if (this.suit == 1) {
            return "Hearts";
        } else if (this.suit == 2) {
            return "Clubs";
        } else return "Diamonds";
    }

    public String getRankString() {
        if (this.rank == 1) {
            return "Ace";
        } else if (this.rank == 11) {
            return "Jack";
        } else if (this.rank == 12) {
            return "Queen";
        } else if (this.rank == 13) {
            return "King";
        } else return Integer.toString(this.rank);
    }

    public boolean isFaceUp() {
        return isFaceUp;
    }

    public void setFaceUp(Card card) {
        isFaceUp = true;
    }

    public void setFaceDown(Card card) {
        isFaceUp = false;
    }

    /**
     * returns int of count after cards have been drawn
     * @param destination
     * @param deck
     * @return
     */

   public Card drawCard(Hand destination, List<Deck> deck) {
       Card cardDrawn = deck.remove(0);
       destination.addToHand(cardDrawn);
       return cardDrawn;
   }
    @Override
    public String toString() {
        if (!isFaceUp) {
            return "Hidden Card";
        }
        return (this.getRankString() + " of " + this.getSuitString() );
    }

    public int getCountVal() {
        if (this.getRank() == 1 || this.getRank() >= 10) {
            return 1;
        } else if (this.getRank() < 10 || this.getRank() > 6) {
            return 0;
        } else {
            return -1;
        }
    }
}
