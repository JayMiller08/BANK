# Multi-Account Banking System

This is a simple command-line based multi-account banking system implemented in Java. The system allows users to create multiple bank accounts, switch between them, and perform operations such as deposits, withdrawals, and viewing transaction histories independently for each account.

## Features

- **Create Multiple Accounts**: Allows the creation of multiple bank accounts.
- **Select Active Account**: Enables switching between different accounts.
- **Independent Account Management**: Manages deposits, withdrawals, and transaction histories independently for each account.
- **Account Security**: Users must enter an account number and PIN to access an account, with a maximum of three incorrect PIN attempts before returning to the main menu.
- **Age Calculation**: Calculates and displays the user's age based on their birth date.
- **Coupon Offer**: Offers a coupon option to users during account creation.

## Getting Started

### Prerequisites

- Java Development Kit (JDK) installed on your machine.
- A text editor or IDE (such as Notepad++, Eclipse, or IntelliJ IDEA).

### Installation

1. **Clone the repository**:
    ```bash
    git clone https://github.com/yourusername/banking-system.git
    ```

2. **Navigate to the project directory**:
    ```bash
    cd banking-system
    ```

3. **Compile the Java files**:
    ```bash
    javac *.java
    ```

4. **Run the application**:
    ```bash
    java MultiAccountBankingSystem
    ```

## Usage

1. **Main Menu**:
    - Create Account
    - Select Account
    - Deposit Funds
    - Withdraw Funds
    - View Account Details
    - View Transaction History
    - Exit

2. **Create Account**:
    - Enter Last Name
    - Enter Initials
    - Create a 4-digit PIN
    - Enter Birth Date (DDMMYYYY)
    - Option to receive a coupon
    - Account number is generated and displayed
    - Age is calculated and displayed

3. **Select Account**:
    - Enter Account Number
    - Enter PIN (up to three attempts)

4. **Deposit/Withdraw Funds**:
    - Enter amount to deposit or withdraw

5. **View Account Details**:
    - Displays account number, last name, initials, and balance

6. **View Transaction History**:
    - Displays the list of transactions (deposits and withdrawals)

## Code Structure

- **MultiAccountBankingSystem.java**: Contains the main method, menu, and handles user input for various operations.
- **BankAccount.java**: Represents a bank account, including fields for last name, initials, account number, PIN, balance, birth date, and transaction history. Contains methods for depositing, withdrawing, and viewing transactions.

## Example

Here is an example of how the system might be used:

