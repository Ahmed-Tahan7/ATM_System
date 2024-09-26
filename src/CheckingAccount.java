// This class give the user the ability to overdraw up to a certain limit

public class CheckingAccount extends Account {
    private static final double maxDailyLimit = -1000; // daily limit

    public CheckingAccount(String accountNumber, String PIN, double initialBalance) {
        super(accountNumber, PIN, initialBalance);
    }
    // Max daily withdrawal method
    @Override
    public boolean withdraw(double amount) {
        if (getBalance() - amount >= maxDailyLimit) {
            return super.withdraw(amount);
        } else {
            addTransaction("Daily limit exceeded, withdrawal failed.");
            return false;
        }
    }
}
