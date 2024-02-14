import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


/*
 * TODO: chip accounting system
 * TODO: more players
 * TODO: shuffle on command
 * TODO: more decks
 * TODO: ask madden
 *  TODO: clear hand after each round
  */
public class Blackjack extends Card {

    UserInputs userInput = new UserInputs();

    public static void main(String[] args) {
        Blackjack blackjack = new Blackjack();

        blackjack.startGame();

    }

    public void startGame() { // start with 2 player

        Hand playerHand = new Hand();
        Hand dealerHand = new Hand();

        Deck newDeck = new Deck();

        List Deck = newDeck.makeDeck();

        UserInterface player = new UserInterface();

        System.out.println("What is your name?");
        player.setName(userInput.newPlayer());


        boolean gameRunning = true;

        // how much would you like to bet? (max 100)
        System.out.println("Welcome " + player.getName() + "!");
        while (gameRunning) {
            System.out.println("You have " + player.getBank() + " chips.");
            System.out.println("How much would you like to bet?");
            int bet = userInput.startBet();

            boolean inHand = true;
            while (inHand) {
                // remove cards from both hands and place at the bottom of the deck

                if (playerHand.getHand().size() != 0) {
                    for (Card each : playerHand.getHand()) {
                        Deck.add(Deck.size() ,playerHand.getHand().removeAll(playerHand.getHand()));
                    }
                    for (Card each : dealerHand.getHand()) {
                        Deck.add(Deck.size() ,dealerHand.getHand().removeAll(dealerHand.getHand()));
                    }

                }

                drawCard(playerHand, Deck);
                drawCard(playerHand, Deck);

                drawCard(dealerHand, Deck);
                drawCard(dealerHand, Deck);


                System.out.println("Dealer has: A hidden card and a " + dealerHand.getHand().get(1) + " You have: " + playerHand.getHand());
                System.out.println();
                System.out.println("Dealer showing: " + dealerHand.getHand().get(1));
                System.out.println();
                System.out.println("Your hand is worth: " + playerHand.getHandValue(playerHand.getHand()));
                System.out.println();

                if (dealerHand.isBlackjack(dealerHand)) {
                    System.out.println("Dealer Blackjack!");
                    System.out.println("You lose!");
                    System.out.println();
                    break;
                }

                if (playerHand.isBlackjack(playerHand)) {
                    System.out.println("Blackjack!");
                    System.out.println("You win!");
                    break;
                }

                // TODO: check for blackjack


                System.out.println("Options: ");
                System.out.println("[1]: Hit");
                System.out.println("[2]: Stand");
                System.out.println("[3]: Ask Madden");
                System.out.println("[4]: Quit");

                System.out.print("What will you do: ");

                // TODO: mega switchcase for this part ^
            }
        }
    }
}
