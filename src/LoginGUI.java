// Login GUI class handles the first page of the GUI

import javax.swing.*;
import java.awt.*;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

public class LoginGUI extends JFrame {
    private JTextField accountNumberField;
    private JPasswordField pinField;
    private StringBuilder currentInput;
    private ATMService atmService;
    private JTextField focusedField; // Keeps track of which input field is focused

    public LoginGUI(ATMService atmService) {
        this.atmService = atmService;
        currentInput = new StringBuilder();

        // GUI setup
        setTitle("ATM Login");
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Input fields panel
        JPanel inputPanel = new JPanel(new GridLayout(2, 2));
        accountNumberField = new JTextField();
        pinField = new JPasswordField();

        inputPanel.add(new JLabel("Account Number:"));
        inputPanel.add(accountNumberField);
        inputPanel.add(new JLabel("PIN:"));
        inputPanel.add(pinField);
        add(inputPanel, BorderLayout.NORTH);

        // put the focus on Account Number field when the program runs
        focusedField = accountNumberField;

        accountNumberField.addFocusListener(new FocusAdapter() {
            public void focusGained(FocusEvent e) {
                focusedField = accountNumberField;
                currentInput.setLength(0);
            }
        });

        pinField.addFocusListener(new FocusAdapter() {
            public void focusGained(FocusEvent e) {
                focusedField = pinField;
                currentInput.setLength(0); // Clear current input when switching fields
            }
        });

        // Numbers Pad implementations
        JPanel keypadPanel = new JPanel(new GridLayout(4, 3));
        for (int i = 1; i <= 9; i++) {
            int num = i;
            JButton button = new JButton(String.valueOf(i));
            button.addActionListener( _ -> appendNumber(num));
            keypadPanel.add(button);
        }

        JButton zeroButton = new JButton("0");
        zeroButton.addActionListener( _ -> appendNumber(0));
        keypadPanel.add(zeroButton);

        JButton loginButton = new JButton("Login");
        loginButton.addActionListener( _ -> handleLogin());
        keypadPanel.add(loginButton);

        JButton clearButton = new JButton("Clear");
        clearButton.addActionListener( _ -> clearInput());
        keypadPanel.add(clearButton);

        JPanel bottomPanel = new JPanel(new BorderLayout());
        bottomPanel.add(keypadPanel, BorderLayout.CENTER);
        add(bottomPanel, BorderLayout.CENTER);

        setVisible(true);
    }

// Appends numbers to the focused field
    private void appendNumber(int num) {
        currentInput.append(num);
        focusedField.setText(currentInput.toString());
    }

    // Clears the input
    private void clearInput() {
        currentInput.setLength(0);
        accountNumberField.setText("");
        pinField.setText("");
    }

//    Login handling
    private void handleLogin() {
        String accountNumber = accountNumberField.getText();
        String pin = new String(pinField.getPassword());
        Account account = atmService.login(accountNumber, pin);

        if (account != null) {
            JOptionPane.showMessageDialog(this, "Login Successful");
            new MenuGUI(atmService, account);
            dispose();
        } else {
            JOptionPane.showMessageDialog(this, "Invalid Account or PIN");
        }
    }
}
