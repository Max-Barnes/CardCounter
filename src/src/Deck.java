import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import java.util.Collections;
public class Deck extends Card{

    // make list of cards for each suit
    List<Card> deckOfCards = new ArrayList<>();


    /**
     * should return a shuffled deck of cards as a list, will most likely add another nested loop for # of decks
     * but that might run for awhile.
     * also need to set base deck?
     * @return
     */
    public List<Card> makeDeck() {
//        List<Card> deckOfCards = new ArrayList<>();


        for (int nOfSuit = 0; nOfSuit < 3; nOfSuit++) {

            for (int nOfRank = 1; nOfRank < 13; nOfRank++) {
                deckOfCards.add(new Card(nOfRank, nOfSuit));
            }
        }
        Collections.shuffle(deckOfCards);
        return deckOfCards;
    }


}
