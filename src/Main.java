public class Main {
    public static void main(String[] args) {
        // Instance to manage accounts
        AccountManager accountManager = new AccountManager();

        // Test accounts
        Account account1 = new Account("12345", "0000", 5000);
        accountManager.addAccount(account1);

        //Creating Saving account
        accountManager.createSavingsAccount("56789", "1111", 10000);

        // Creating checking account
        accountManager.createCheckingAccount("67890", "2222", 15000);

        ATMService atmService = new ATMService(accountManager);

        // Starts the GUI
        new LoginGUI(atmService);
    }
}
