// Second page of GUI that handles the Menu of ATM and main transactions

import javax.swing.*;
import java.awt.*;

public class MenuGUI extends JFrame {
    private ATMService atmService;
    private Account account;
    private JTextArea outputArea;
    private JTextField inputField;
    private StringBuilder currentInput;

    public MenuGUI(ATMService atmService, Account account) {
        this.atmService = atmService;
        this.account = account;
        currentInput = new StringBuilder();

        setTitle("ATM Main Menu");
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Setting output text area
        outputArea = new JTextArea();
        outputArea.setEditable(false);
        add(new JScrollPane(outputArea), BorderLayout.CENTER);

        //Setting KEy pad and input text area panel
        JPanel bottomPanel = new JPanel(new BorderLayout());

        inputField = new JTextField();
        inputField.setEditable(false);
        bottomPanel.add(inputField, BorderLayout.NORTH);

        //Creating ATM Key pad
        JPanel keypadPanel = new JPanel(new GridLayout(4, 3));
        for (int i = 1; i <= 9; i++) {
            int num = i;
            JButton button = new JButton(String.valueOf(i));
            button.addActionListener(_ -> appendNumber(num));
            keypadPanel.add(button);
        }

        //Setting action buttons in the keypad
        JButton zeroButton = new JButton("0");
        zeroButton.addActionListener(_ -> appendNumber(0));
        keypadPanel.add(zeroButton);

        JButton enterButton = new JButton("Enter");
        enterButton.addActionListener(_ -> handleEnter());
        keypadPanel.add(enterButton);

        JButton clearButton = new JButton("Clear");
        clearButton.addActionListener(_ -> clearInput());
        keypadPanel.add(clearButton);

        bottomPanel.add(keypadPanel, BorderLayout.CENTER);

        //Setting the location of key pad
        add(bottomPanel, BorderLayout.SOUTH);

        // Setting main transactions buttons
        JPanel buttonPanel = new JPanel(new GridLayout(3, 2));

        JButton depositButton = new JButton("Deposit");
        depositButton.addActionListener(_ -> handleDeposit());
        buttonPanel.add(depositButton);

        JButton withdrawButton = new JButton("Withdraw");
        withdrawButton.addActionListener(_ -> handleWithdraw());
        buttonPanel.add(withdrawButton);

        JButton transferButton = new JButton("Transfer");
        transferButton.addActionListener(_ -> handleTransfer());
        buttonPanel.add(transferButton);

        JButton changePinButton = new JButton("Change PIN");
        changePinButton.addActionListener(_ -> handleChangePin());
        buttonPanel.add(changePinButton);

        JButton transactionHistoryButton = new JButton("Transaction History");
        transactionHistoryButton.addActionListener(_ -> showTransactionHistory());
        buttonPanel.add(transactionHistoryButton);

        JButton checkBalanceButton = new JButton("Check Balance");
        checkBalanceButton.addActionListener(_ -> showBalance());
        buttonPanel.add(checkBalanceButton);

        JButton logoutButton = new JButton("Logout");
        logoutButton.addActionListener(_ -> logout());
        buttonPanel.add(logoutButton);

        add(buttonPanel, BorderLayout.NORTH);

        setVisible(true);
    }

    private void showBalance() {
        outputArea.setText("Your balance is: $" + account.getBalance());
    }

    private void handleDeposit() {
        clearInput();
        outputArea.setText("Please enter the amount to deposit");
    }

    private void handleWithdraw() {
        clearInput();
        outputArea.setText("Please enter the amount to withdraw");
    }

    private void handleTransfer() {
        clearInput();
        outputArea.setText("Please enter the recipient account number");
    }

    private boolean isWaitingForNewPin = false;
    private String oldPin;

    private void handleChangePin() {
        clearInput();
        outputArea.setText("Please enter your PIN");
        isWaitingForNewPin = false;
    }

    // Displaying transaction history
    private void showTransactionHistory() {
        outputArea.setText("Transaction History:\n" + account.getTransactionHistory());
    }

    // Handling logout
    private void logout() {
        JOptionPane.showMessageDialog(this, "Logging out...");
        new LoginGUI(atmService);
        dispose();
    }

    // Append numbers to the input field
    private void appendNumber(int num) {
        currentInput.append(num);
        inputField.setText(currentInput.toString());
    }

    //A method to clear the input
    private void clearInput() {
        currentInput.setLength(0);
        inputField.setText("");
    }

    // Handling the Enter button
    private void handleEnter() {
        String input = inputField.getText();

        if (outputArea.getText().contains("deposit")) {
            try {
                double amount = Double.parseDouble(input);
                if (atmService.deposit(account, amount)) {
                    outputArea.setText("Deposit successful. New balance: $" + account.getBalance());
                } else {
                    outputArea.setText("Deposit failed.");
                }
            } catch (NumberFormatException e) {
                outputArea.setText("Invalid amount entered.");
            }
        }

        else if (outputArea.getText().contains("withdraw")) {
            try {
                double amount = Double.parseDouble(input);
                if (atmService.withdraw(account, amount)) {
                    outputArea.setText("Withdrawal successful. New balance: $" + account.getBalance());
                } else {
                    outputArea.setText("Insufficient funds.");
                }
            } catch (NumberFormatException e) {
                outputArea.setText("Invalid amount entered.");
            }
        }

        else if (outputArea.getText().contains("account number")) {
            atmService.setReceiverAccount(input);
            outputArea.setText("Please enter the amount to transfer.");
            inputField.setText("");
        }

        else if (outputArea.getText().contains("transfer")) {
            try {
                double amount = Double.parseDouble(input);
                String receiverAccount = atmService.getReceiverAccount();
                if (atmService.transfer(account, receiverAccount, amount)) {
                    outputArea.setText("Transfer successful.");
                } else {
                    outputArea.setText("Transfer failed.");
                }
            } catch (NumberFormatException e) {
                outputArea.setText("Invalid amount entered.");
            }
        }
        // Changing PIN
        else if (outputArea.getText().contains("PIN")) {
            if (!isWaitingForNewPin) {
                oldPin = input;
                clearInput();
                outputArea.setText("Please enter your new PIN");
                isWaitingForNewPin = true;
            } else {
                if (atmService.changePin(account, oldPin, input)) {
                    outputArea.setText("PIN changed successfully.");
                } else {
                    outputArea.setText("PIN change failed\nPlease ensure the old PIN is correct.");
                }
                isWaitingForNewPin = false;
                clearInput();
            }
        }
        clearInput();
    }
}
