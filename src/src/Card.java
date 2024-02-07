import java.util.List;

public class Card {

    // card is two values, rank and suit
    private int rank; // 1 is ace 11 is jack 12 is queen, 13 is king

    private int suit; // 0 is spades, 1 is hearts, 2 is clubs, 3 is diamonds

    boolean isFaceUp = false;

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

    public void setFaceUp(boolean faceUp) {
        isFaceUp = true;
    }

    public void setFaceDown(boolean faceDown) {
        isFaceUp = false;
    }


   public void drawCard(List<Card> destination, List<Deck> deck) {
        destination.add(deck.remove(0));
   }
    @Override
    public String toString() {
        return (this.getSuitString() + this.getRankString());
    }




}
