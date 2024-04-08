package atm;

import java.util.Scanner;

import java.util.ArrayList;
import java.util.List;

public class ATM {
	
	static
	{
		System.out.println("Welcome to the ATM.");
	}
    private List<BankAccount> accounts;
    private Scanner scanner;

    public ATM() {
        this.accounts = new ArrayList<>();
        this.scanner = new Scanner(System.in);
    }

    public void addAccount(BankAccount account) {
        accounts.add(account);
    }

    public void displayMenu() {
        
        System.out.println("Enter your account number:");

        int accountNumber = scanner.nextInt();
        BankAccount currentAccount = findAccount(accountNumber);

        if (currentAccount == null) {
            System.out.println("Account not found. Please try again.");
            displayMenu();
            return;
        }
        
        System.out.println("Account Holder: " + currentAccount.getAccountHolderName());
        System.out.println("Account Type: " + currentAccount.getAccountType());
       while(true) {
        System.out.println("1. Check Balance");
        System.out.println("2. Deposit");
        System.out.println("3. Withdraw");
        System.out.println("4. Exit");

        int choice = scanner.nextInt();

        switch (choice) {
            case 1:
                checkBalance(currentAccount);
                break;
            case 2:
                System.out.println("Enter deposit amount:");
                double depositAmount = scanner.nextDouble();
                currentAccount.deposit(depositAmount);
                break;
            case 3:
                System.out.println("Enter withdrawal amount:");
                double withdrawalAmount = scanner.nextDouble();
                currentAccount.withdraw(withdrawalAmount);
                break;
            case 4:
                System.out.println("Thank you for using the ATM. Have a good day!!");
                System.exit(0);
            default:
                System.out.println("Invalid choice. Please try again.");
        }
       }

//        displayMenu();
        // Display menu again after completing an operation
    }

    private BankAccount findAccount(int accountNumber) {
        for (BankAccount account : accounts) {
            if (account.getAccountNumber()==accountNumber) {
                return account;
            }
        }
        return null;
    }

    private void checkBalance(BankAccount account) {
        double balance = account.getBalance();
        System.out.println("Your balance is: " + balance);
    }

    public static void main(String[] args) {
        ATM atm = new ATM();

        atm.addAccount(new BankAccount(123, "LEKHA", 1000.0, BankAccount.AccountType.SAVINGS));
        atm.addAccount(new BankAccount(944, "SELVAM", 500.0, BankAccount.AccountType.CURRENT));

        atm.displayMenu();
    }
}

