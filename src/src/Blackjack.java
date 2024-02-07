import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Blackjack extends Card implements cardGame{


    Scanner userInput = new Scanner();
    public static void main(String[] args) {

        startGame();

    }

    @Override
    public void startGame() { // start with 2 player
        // make deck

        List playerHand = new ArrayList<>(2);
        List dealerHand = new ArrayList<>(2);

        Deck newDeck = new Deck();

        List Deck = newDeck.makeDeck();

        drawCard(playerHand, Deck);
        drawCard(playerHand, Deck);

        drawCard(dealerHand, Deck);
        drawCard(dealerHand, Deck);

        // deal cards form top

        System.out.println("Dealer has: " + dealerHand + "You have: " + playerHand);

        System.out.println("[1]: Hit");
        System.out.println("[2]: Stand");
        System.out.println("[3]: Help");
        System.out.println("[4]: Quit");

        System.out.print("What will you do: ");
        userInput.nextLine();

        

    }
}
