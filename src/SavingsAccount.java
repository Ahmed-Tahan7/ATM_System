//This class gives the user three free withdrawals

public class SavingsAccount extends Account {
    private int withdrawalCount;
    private static final int freeWithdrawals = 3;
    private static final double withdrawalFee = 5;

    public SavingsAccount(String accountNumber, String PIN, double initialBalance) {
        super(accountNumber, PIN, initialBalance);
        this.withdrawalCount = 0;
    }
//    Handling free withdrawals
    @Override
    public boolean withdraw(double amount) {
        if (withdrawalCount < freeWithdrawals) {
            withdrawalCount++;
            return super.withdraw(amount);
        } else if (super.withdraw(amount, withdrawalFee)) {
//            addTransaction("Withdrawal fee of $" + withdrawalFee + " applied.");
            return true;
        }
        return false;
    }
}
