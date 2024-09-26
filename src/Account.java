//This class implements the main logic of the system and functions

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class Account {
    private final String accountNumber;
    private String PIN;
    private double balance;
    private ArrayList<String> transactionHistory;
    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    public Account(String accountNumber, String pin, double initialBalance) {
        this.accountNumber = accountNumber;
        this.PIN = pin;
        this.balance = initialBalance;
        this.transactionHistory = new ArrayList<>();
        addTransaction("Account opened with balance: $" + initialBalance);
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public double getBalance() {
        return balance;
    }

    public boolean validatePin(String pin) {
        return this.PIN.equals(pin);
    }
    // Deposit function
    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            addTransaction("Deposited: $" + amount + " | New Balance: $" + balance);
        }
    }
    // Withdraw function
    public boolean withdraw(double amount) {
        if (amount > 0 && amount <= balance) {
            balance -= amount;
            addTransaction("Withdrew: $" + amount + " | New Balance: $" + balance);
            return true;
        }
        return false;
    }
    // Withdraw function with fees
    public boolean withdraw(double amount, double fees) {
        if (amount > 0 && amount <= balance + fees) {
            balance -= amount + fees;
            addTransaction("Withdrew: $" + amount + " (fees: $" + fees + ") successful | New Balance: $" + balance);
            return true;
        }
        return false;
    }
    // Transfer function
    public boolean transfer(Account recipient, double amount) {
        if (recipient != null && amount > 0 && withdraw(amount)) {
            recipient.deposit(amount);
            addTransaction("Transferred: $" + amount + " to " + recipient.getAccountNumber());
            recipient.addTransaction("Received: $" + amount + " from " + this.getAccountNumber());
            return true;
        }
        return false;
    }
    // Change PIN function
    public boolean changePin(String oldPin, String newPin) {
        if (validatePin(oldPin) && newPin.length() == 4 && newPin.matches("\\d+")) {
            this.PIN = newPin;
            addTransaction("PIN changed successfully.");
            return true;
        }
        return false;
    }
    // A function to append the transactions
    protected void addTransaction(String transaction) {
        transactionHistory.add("[" + LocalDateTime.now().format(formatter) + "] " + transaction);
    }
    // A function to print all user transaction
    public String getTransactionHistory() {
        StringBuilder history = new StringBuilder("Transaction History for account: " + accountNumber + "\n");
        for (String transaction : transactionHistory) {
            history.append(transaction).append("\n");
        }
        return history.toString();
    }
}