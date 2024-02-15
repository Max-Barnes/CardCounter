import java.util.Scanner;

public class UserInputs {
    Scanner userInput = new Scanner(System.in);

    public int startBet() { // "how much would you like to bet (max 100)"

        int betAmt = 0;
        boolean betValid = false;
        while (!betValid) {
            String bet = userInput.nextLine().trim();
            try {
                betAmt = Integer.parseInt(bet);
                betValid = true;
            } catch (NumberFormatException e) {
                System.out.println("Please only enter numbers");
            }
            if (betAmt > 100 || betAmt < 1) {
                System.out.println("bet must be between 1 and 100");
                betValid = false;
            }
        }



        return betAmt;

    }

    public String newPlayer() {

        return userInput.nextLine();
    }

    public String choice() {
        return userInput.nextLine();
    }
}
