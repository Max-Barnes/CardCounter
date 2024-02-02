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

    public int getSuit() {
        return suit;
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



}
