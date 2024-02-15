import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


/*
 * TODO: chip accounting system
 * TODO: ask madden
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

                    player.handPayoutLose(bet);
                    break;
                }

                if (playerHand.isBlackjack(playerHand)) {
                    System.out.println("Blackjack!");
                    System.out.println("You win!");

                    player.blackjackPayout(bet);
                    break;
                }
                boolean choosing = true;
                while (choosing) {
                    System.out.println("Options: ");
                    System.out.println("[1]: Hit");
                    System.out.println("[2]: Stand");
                    System.out.println("[3]: Ask Madden");
                    System.out.println("[4]: Quit");

                    System.out.print("What will you do: ");

                    String choice = userInput.choice();

                        switch (choice) {

                            case "1":
                                playerHand.drawCard(playerHand, Deck);
                                System.out.println("Your hand is worth: " + playerHand.getHandValue(playerHand.getHand()));
                                if (playerHand.getHandValue(playerHand.getHand()) > 21) {
                                    System.out.println("Bust!");
                                    System.out.println("You lost " + bet + " chips");
                                    player.handPayoutLose(bet);
                                    inHand = false;
                                    choosing = false;

                                    break;

                                }
                                break;
                            case "2":
                                while (isDealerHandContinuing(dealerHand)) {
                                    System.out.println("Dealer drawing...");
                                    drawCard(dealerHand, Deck);
                                    System.out.println("Dealer has: " + dealerHand.getHandValue(dealerHand.getHand()));
                                    if (dealerHand.getHandValue(dealerHand.getHand()) > 21) {
                                        System.out.println("Dealer bust!");
                                        System.out.println("You won " + (bet * 1.5) +" chips");
                                        player.handPayoutWin(bet);
                                        inHand = false;
                                        choosing = false;

                                        break;
                                    }
                                }
                                if (dealerHand.getHandValue(dealerHand.getHand()) >= playerHand.getHandValue(playerHand.getHand()) && dealerHand.getHandValue(dealerHand.getHand()) <= 21) {
                                    System.out.println("Dealer has" + dealerHand.getHandValue(dealerHand.getHand()));
                                    System.out.println("Dealer wins!");
                                    System.out.println("You lost " + bet + " chips");
                                    player.handPayoutLose(bet);
                                    inHand = false;
                                    choosing = false;

                                } else {
                                    System.out.println("You win!");
                                    System.out.println("You won " + (bet * 1.5) +" chips");
                                    player.handPayoutWin(bet);
                                    inHand = false;
                                    choosing = false;

                                }

                                break;
                            case "3": // TODO: ask madden
                                break;
                            case "4":
                                player.handPayoutLose(bet);
                                inHand = false;
                                choosing = false;
                                gameRunning = false;
                                break;
                        }
                }
            }
        }
    }

    public boolean isHandContinuing(Hand hand) {
        int handVal = hand.getHandValue(hand.getHand());

        if (handVal >= 21) {
            return false;
        } else return true;

    }

    public boolean isDealerHandContinuing(Hand hand) {
        int handval = hand.getHandValue(hand.getHand());

        if (handval >= 17) {
            return false;
        } else return true;
    }
}
