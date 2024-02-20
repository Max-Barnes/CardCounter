public class UserInterface {


    private final int DEFAULT_BANK_AMOUNT = 1000;
    private int bank = DEFAULT_BANK_AMOUNT;

    private String name;

    public UserInterface() {
        this.bank = DEFAULT_BANK_AMOUNT;
    }

    public int getBank() {
        return bank;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void handPayoutWin(int bet) {
        this.bank += (int)bet * 1.5;
    }

    public void handPayoutLose(int bet) {
        this.bank -= bet;
    }

    public void blackjackPayout(int bet) {
        this.bank += 3 * (bet) + bet;
    }


}
