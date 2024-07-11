import java.util.ArrayList; // Import ArrayList for storing transactions
import java.util.Random; // Import Random for generating random account numbers
import java.util.Scanner; // Import Scanner for user input
import java.time.LocalDate; // Import LocalDate for date operations
import java.time.format.DateTimeFormatter; // Import DateTimeFormatter for formatting dates
import java.time.Period; // Import Period for calculating the difference between dates

class BankAccount {
    String lastName; // Last name of the account holder
    String initials; // Initials of the account holder
    int accountNumber; // Account number of the bank account
    int pin; // Pin for the bank account
    double balance; // Balance of the bank account
    ArrayList<String> transactions; // List of transactions for the bank account
    LocalDate birthDate; // Birth date of the account holder

    public BankAccount(String lastName, String initials, int pin, int accountNumber, LocalDate birthDate) {
        this.lastName = lastName; // Initialize last name
        this.initials = initials; // Initialize initials
        this.accountNumber = accountNumber; // Initialize account number
        this.pin = pin; // Initialize pin
        this.balance = 0.0; // Initialize balance to 0
        this.transactions = new ArrayList<>(); // Initialize the transactions list
        this.birthDate = birthDate; // Initialize birth date
    }

    public void deposit(double amount) {
        balance += amount; // Add deposit amount to balance
        transactions.add("Deposited: R" + amount); // Add deposit transaction to history
        System.out.println("Deposited R" + amount + " into account " + accountNumber); // Print confirmation
    }

    public void withdraw(double amount) {
        if (amount <= balance) { // Check if balance is sufficient for withdrawal
            balance -= amount; // Subtract withdrawal amount from balance
            transactions.add("Withdrew: R" + amount); // Add withdrawal transaction to history
            System.out.println("Withdrew R" + amount + " from account " + accountNumber); // Print confirmation
        } else {
            System.out.println("Insufficient funds."); // Print insufficient funds message
        }
    }

    public void viewBalance() {
        System.out.println("Account " + accountNumber + " balance: R" + balance); // Print account balance
    }

    public void viewTransactions() {
        System.out.println("Transaction history for account " + accountNumber + ":"); // Print transaction history header
        for (String transaction : transactions) { // Loop through each transaction
            System.out.println(transaction); // Print each transaction
        }
    }
}

class MultiAccountBankingSystem {
    ArrayList<BankAccount> accounts; // List of all bank accounts
    BankAccount activeAccount; // Currently selected bank account
    Scanner scanner; // Scanner for user input
    Random random; // Random for generating account numbers

    public MultiAccountBankingSystem() {
        accounts = new ArrayList<>(); // Initialize the accounts list
        scanner = new Scanner(System.in); // Initialize the scanner
        random = new Random(); // Initialize the random number generator
    }

    public void createAccount() {
        if (accounts.size() >= 100) { // Check if account limit has been reached
            System.out.println("Account limit reached. Cannot create more accounts."); // Print limit reached message
            return; // Exit the method
        }

        System.out.print("Enter last name: "); // Prompt for last name
        String lastName = scanner.next(); // Read last name
        System.out.print("Enter initials: "); // Prompt for initials
        String initials = scanner.next(); // Read initials
        System.out.print("Create a 4-digit pin: "); // Prompt for pin
        int pin = scanner.nextInt(); // Read pin

        // Prompt for birth date
        System.out.print("Enter your birth date (DDMMYYYY): "); // Prompt for birth date
        String birthDateString = scanner.next(); // Read birth date
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("ddMMyyyy"); // Define date format
        LocalDate birthDate = LocalDate.parse(birthDateString, formatter); // Parse birth date

        // Calculate age
        LocalDate today = LocalDate.now(); // Get today's date
        Period age = Period.between(birthDate, today); // Calculate the difference between birth date and today
        System.out.println("You are " + age.getYears() + " years old."); // Print age

        // Ask if the user wants a coupon
        System.out.print("Would you like a coupon to use in any grocery store of your choice? (yes/no): "); // Prompt for coupon
        String couponChoice = scanner.next(); // Read coupon choice
        if (couponChoice.equalsIgnoreCase("yes")) {
            System.out.println("Coupon generated! You can use it in any grocery store."); // Print coupon confirmation
        }

        int accountNumber = 10000000 + random.nextInt(90000000); // Generate random 8-digit account number
        BankAccount account = new BankAccount(lastName, initials, pin, accountNumber, birthDate); // Create a new bank account
        accounts.add(account); // Add the new account to the accounts list
        System.out.println("Account created successfully. Your account number is: " + accountNumber); // Print success message
    }

    public void selectAccount() {
        System.out.print("Enter the account number to select: "); // Prompt for account number
        int accountNumber = scanner.nextInt(); // Read account number
        BankAccount account = null; // Initialize account variable

        for (BankAccount acc : accounts) { // Loop through all accounts
            if (acc.accountNumber == accountNumber) { // Check if account number matches
                account = acc; // Set account variable
                break; // Exit loop
            }
        }

        if (account == null) { // Check if account was found
            System.out.println("Account not found."); // Print not found message
            return; // Exit the method
        }

        int attempts = 0; // Initialize attempts counter
        while (attempts < 3) { // Allow up to 3 attempts
            System.out.print("Enter your pin: "); // Prompt for pin
            int pin = scanner.nextInt(); // Read pin
            if (account.pin == pin) { // Check if pin matches
                activeAccount = account; // Set active account
                System.out.println("Account " + accountNumber + " selected."); // Print success message
                return; // Exit the method
            } else {
                System.out.println("Incorrect pin."); // Print incorrect pin message
                attempts++; // Increment attempts counter
            }
        }

        System.out.println("Too many incorrect attempts. Returning to main menu."); // Print too many attempts message
    }

    public void depositFunds() {
        if (activeAccount != null) { // Check if an account is selected
            System.out.print("Enter the amount to deposit: "); // Prompt for deposit amount
            double amount = scanner.nextDouble(); // Read deposit amount
            activeAccount.deposit(amount); // Deposit the amount
        } else {
            System.out.println("No active account selected."); // Print no account selected message
        }
    }

    public void withdrawFunds() {
        if (activeAccount != null) { // Check if an account is selected
            System.out.print("Enter the amount to withdraw: "); // Prompt for withdrawal amount
            double amount = scanner.nextDouble(); // Read withdrawal amount
            activeAccount.withdraw(amount); // Withdraw the amount
        } else {
            System.out.println("No active account selected."); // Print no account selected message
        }
    }

    public void viewAccountDetails() {
        if (activeAccount != null) { // Check if an account is selected
            activeAccount.viewBalance(); // View account balance
        } else {
            System.out.println("No active account selected."); // Print no account selected message
        }
    }

    public void viewTransactionHistory() {
        if (activeAccount != null) { // Check if an account is selected
            activeAccount.viewTransactions(); // View transaction history
        } else {
            System.out.println("No active account selected."); // Print no account selected message
        }
    }

    public void showMenu() {
        while (true) { // Infinite loop for menu
            System.out.println("\nMenu:"); // Print menu header
            System.out.println("1. Create Account"); // Option 1
            System.out.println("2. Select Account"); // Option 2
            System.out.println("3. Deposit Funds"); // Option 3
            System.out.println("4. Withdraw Funds"); // Option 4
            System.out.println("5. View Account Details"); // Option 5
            System.out.println("6. View Transaction History"); // Option 6
            System.out.println("7. Exit"); // Option 7

            System.out.print("Enter your choice: "); // Prompt for choice
            int choice = scanner.nextInt(); // Read choice

            if (choice == 1) {
                createAccount(); // Call createAccount
            } else if (choice == 2) {
                selectAccount(); // Call selectAccount
            } else if (choice == 3) {
                depositFunds(); // Call depositFunds
            } else if (choice == 4) {
                withdrawFunds(); // Call withdrawFunds
            } else if (choice == 5) {
                viewAccountDetails(); // Call viewAccountDetails
            } else if (choice == 6) {
                viewTransactionHistory(); // Call viewTransactionHistory
            } else if (choice == 7) {
                System.out.println("Exiting..."); // Print exiting message
                break; // Exit the loop
            } else {
                System.out.println("Invalid choice. Please try again."); // Print invalid choice message
            }
        }
    }

    public static void main(String[] args) {
        MultiAccountBankingSystem system = new MultiAccountBankingSystem(); // Create the banking system
        system.showMenu(); // Show the menu
    }
}
