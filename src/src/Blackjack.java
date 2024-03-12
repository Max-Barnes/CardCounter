import java.sql.Driver;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.sql.Connection;
import java.sql.DriverManager;

/*
 * TODO: ask madden
  */
public class Blackjack extends Card {

    UserInputs userInput = new UserInputs();

    public static void main(String[] args) {
        String jdbcURL = "jdbc:postgresql://localhost:5432/BJLeaderboard";

        String username = "postgres";
        String password = "techelevator1";


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

        int count = 0;

        System.out.println("Welcome " + player.getName() + "!");
        while (gameRunning) {
            System.out.println("You have " + player.getBank() + " chips.");
            System.out.println();
            if (player.getBank() <= 0) {
                System.out.println("Sorry you have run out of chips");
                System.out.println("Goodbye, "+ player.getName()+ " you left with: " + player.getBank() + " chips.");
                gameRunning = false;
                break;
            }
            System.out.println("How much would you like to bet? (Q to quit)");
            int bet = userInput.startBet();
            if (bet == 0) {
                System.out.println("Goodbye, "+ player.getName()+ " you left with: " + player.getBank() + " chips.");
                player.handPayoutLose(bet);
                gameRunning = false;
                break;
            }

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

                // maintain count could do for each player in game in the future

                for (Card each : playerHand.getHand()) {
                    count += each.getCountVal();
                }
                for (Card each : dealerHand.getHand()) {
                    count += each.getCountVal();
                }


                System.out.println("Dealer has: A hidden card and a " + dealerHand.getHand().get(1) + " You have: " + playerHand.getHand());
                System.out.println();
                System.out.println("Dealer showing: " + dealerHand.getHand().get(1));
                System.out.println();
                System.out.println("Your hand is worth: " + handValueOf(playerHand));
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
                int timesHit = 0;
                while (choosing) {
                    System.out.println("Options: ");
                    System.out.println("[1]: Hit");
                    System.out.println("[2]: Stand");
                    System.out.println("[3]: Ask Madden");
                    System.out.println("[4]: Quit");
                    System.out.println();

                    System.out.print("What will you do: ");
                    System.out.println();

                    String choice = userInput.choice();

                        switch (choice) {

                            case "1":
                                timesHit++;
                                Card drawnCard = playerHand.drawCard(playerHand, Deck);

                                count += drawnCard.getCountVal();

                                System.out.println();
                                System.out.println("The card drawn is: " + playerHand.getHand().get(1 + timesHit));
                                System.out.println();
                                System.out.println("Your hand is worth: " + handValueOf(playerHand));
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
                                    drawnCard = drawCard(dealerHand, Deck);

                                    count += drawnCard.getCountVal();

                                    System.out.println("Dealer has: " + dealerHand.getHandValue(dealerHand.getHand()));
                                    System.out.println();
                                }
                                if (dealerHand.getHandValue(dealerHand.getHand()) > 21) {
                                    System.out.println("Dealer bust!");
                                    System.out.println("You won " + (bet * 1.5) +" chips");
                                    System.out.println();
                                    player.handPayoutWin(bet);
                                    inHand = false;
                                    choosing = false;

                                }
                                else if (handValueOf(dealerHand) >= handValueOf(playerHand) && handValueOf(dealerHand) <= 21) {

                                    System.out.println("Dealer has" + handValueOf(dealerHand));
                                    System.out.println("Dealer wins!");
                                    System.out.println("You lost " + bet + " chips");
                                    System.out.println();
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
                                if (count == 0) {
                                    System.out.println("The count is 0");
                                    System.out.println("You should bet conservatively until count is high");
                                    System.out.println("Assume the dealer has a high card (value 10) as their hidden card");
                                } else if (count > 0) {
                                    System.out.println("The count is +" + Math.abs(count));
                                    System.out.println("You should bet high and expect high value cards!");
                                    System.out.println("Assume the dealer could also have a high card");
                                } else {
                                    System.out.println("The count is -" + Math.abs(count));
                                    System.out.println("You should bet low until count is high again");
                                    System.out.println("The dealer may not have an ace or 10 as their hidden card since the count is low");
                                }

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


    public int handValueOf(Hand playerHand) {
        return playerHand.getHandValue(playerHand.getHand());
    }

}
