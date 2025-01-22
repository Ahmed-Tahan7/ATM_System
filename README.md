# ATM Management System

**Version:** 1.0  
**Developed by:** Ahmed Abdalla El-Tahan  
**Date:** February 2024  

## Overview
The ATM Management System is a standalone Java application that facilitates basic banking functionalities. It includes account management, deposits, withdrawals, transfers, and PIN management through a user-friendly graphical user interface (GUI) built with Swing.

## Features
- **Account Management:**
  - Account creation (Savings and Checking).
  - Account login and authentication.
  - PIN change functionality with validation.

- **Transactions:**
  - Deposits and withdrawals with constraints.
  - Transfers between accounts.
  - Transaction history view.

- **GUI Interface:**
  - Login screen with validation.
  - Main menu for transaction handling.
  - Numeric keypad for user input.

- **Account Types:**
  - **Savings Account:** Up to three free withdrawals, with fees applied thereafter.
  - **Checking Account:** Allows overdraft up to a predefined limit.

## Technologies
- **Programming Language:** Java (Java SE 8+)
- **Framework:** Swing (for GUI)
- **Storage:** Local HashMap (for account management)

## System Requirements
- **Java Version:** 8 or higher
- **Operating System:** Any desktop environment supporting Java Runtime Environment (JRE)

## How to Run
1. **Compile the code:**
   ```bash
   javac Main.java
   ```
2. **Run the application:**
   ```bash
   java Main
   ```

## Classes and Responsibilities
- **Account:** Manages core account functionalities (balance management, transaction history, etc.).
- **SavingsAccount & CheckingAccount:** Extend account functionality with specific rules (e.g., withdrawal limits).
- **AccountManager:** Handles account creation and lookup.
- **ATMService:** Implements transaction logic and user authentication.
- **LoginGUI:** Provides a login interface.
- **MenuGUI:** Facilitates interaction with account services post-login.

## Project Structure
```
src/
├── Account.java
├── AccountManager.java
├── ATMService.java
├── CheckingAccount.java
├── LoginGUI.java
├── Main.java
├── MenuGUI.java
└── SavingsAccount.java
```

## Key Functional Requirements
- Validate user login credentials.
- Enforce withdrawal limits and fees.
- Allow seamless transfers between accounts.
- Ensure secure PIN change with validation.

## Documentation
For detailed requirements, refer to the [Software Requirements Specification](Software%20Requirements%20Specification.pdf). The class structure is illustrated in the [Class Diagram](ATM%20Class%20Diagram.drawio.pdf).
