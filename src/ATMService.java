//This class handles the limitation logic of the system

public class ATMService {
    private AccountManager accountManager;
    private String receiverAccount;

    public void setReceiverAccount(String account) {
        this.receiverAccount = account;
    }

    public String getReceiverAccount() {
        return this.receiverAccount;
    }

    public ATMService(AccountManager accountManager) {
        this.accountManager = accountManager;
    }
    // Account authentication
    public Account login(String accountNumber, String pin) {
        Account account = accountManager.findAccount(accountNumber);
        if (account != null && account.validatePin(pin)) {
            return account;
        }
        return null;
    }
    //Checking deposit input
    public boolean deposit(Account account, double amount) {
        if (account != null && amount > 0) {
            account.deposit(amount);
            return true;
        }
        return false;
    }
    // Checking withdraw input
    public boolean withdraw(Account account, double amount) {
        if (account != null) {
            return account.withdraw(amount);
        }
        return false;
    }
    // Checking transfer input
    public boolean transfer(Account sender, String receiverAccountNumber, double amount) {
        Account receiver = accountManager.findAccount(receiverAccountNumber);
        if (receiver != null && sender != null && !sender.getAccountNumber().equals(receiverAccountNumber)) {
            return sender.transfer(receiver, amount);
        }
        return false;
    }

    public boolean changePin(Account account, String oldPin, String newPin) {
        if (account != null) {
            return account.changePin(oldPin, newPin);
        }
        return false;
    }
}