//This class manages account types

import java.util.HashMap;

public class AccountManager {
    private final HashMap<String, Account> accounts;
    // Creating Hash Map to navigate the accounts more efficiently
    public AccountManager() {
        this.accounts = new HashMap<>();
    }
    // Adding Accounts method
    public void addAccount(Account account) {
        accounts.put(account.getAccountNumber(), account);
    }
    // Finding accounts method
    public Account findAccount(String accountNumber) {
        return accounts.get(accountNumber);
    }
//    Creating saving accounts method
    public void createSavingsAccount(String accountNumber, String PIN, double initialBalance) {
        addAccount(new SavingsAccount(accountNumber, PIN, initialBalance));
    }
//    Creating checking accounts method
    public void createCheckingAccount(String accountNumber, String PIN, double initialBalance) {
        addAccount(new CheckingAccount(accountNumber, PIN, initialBalance));
    }
}

