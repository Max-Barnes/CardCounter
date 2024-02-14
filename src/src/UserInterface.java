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
}
