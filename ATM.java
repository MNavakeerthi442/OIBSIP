import java.util.ArrayList;
import java.util.Scanner;

class Account {
    private String userId;
    private String userPin;
    private double balance;
    private ArrayList<String> transactionHistory;

    public Account(String userId, String userPin) {
        this.userId = userId;
        this.userPin = userPin;
        this.balance = 0.0;
        this.transactionHistory = new ArrayList<>();
    }

    public String getUserId() {
        return userId;
    }

    public boolean validatePin(String pin) {
        return this.userPin.equals(pin);
    }

    public double getBalance() {
        return balance;
    }

    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            transactionHistory.add("Deposited: " + amount);
            System.out.println("Deposit successful!");
        } else {
            System.out.println("Invalid deposit amount.");
        }
    }

    public void withdraw(double amount) {
        if (amount > 0 && amount <= balance) {
            balance -= amount;
            transactionHistory.add("Withdrew: " + amount);
            System.out.println("Withdrawal successful!");
        } else {
            System.out.println("Invalid withdrawal amount or insufficient balance.");
        }
    }

    public void transfer(Account recipient, double amount) {
        if (amount > 0 && amount <= balance) {
            balance -= amount;
            recipient.balance += amount;
            transactionHistory.add("Transferred: " + amount + " to " + recipient.getUserId());
            recipient.transactionHistory.add("Received: " + amount + " from " + userId);
            System.out.println("Transfer successful!");
        } else {
            System.out.println("Invalid transfer amount or insufficient balance.");
        }
    }

    public void showTransactionHistory() {
        if (transactionHistory.isEmpty()) {
            System.out.println("No transactions yet.");
        } else {
            System.out.println("Transaction History:");
            for (String transaction : transactionHistory) {
                System.out.println(transaction);
            }
        }
    }
}

public class ATM {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Create sample accounts
        Account user1 = new Account("user1", "1234");
        Account user2 = new Account("user2", "5678");

        Account currentUser = null;

        // Authentication
        System.out.println("Welcome to the ATM!");
        System.out.print("Enter User ID: ");
        String userId = scanner.nextLine();
        System.out.print("Enter PIN: ");
        String userPin = scanner.nextLine();

        if (user1.getUserId().equals(userId) && user1.validatePin(userPin)) {
            currentUser = user1;
        } else if (user2.getUserId().equals(userId) && user2.validatePin(userPin)) {
            currentUser = user2;
        } else {
            System.out.println("Invalid credentials. Exiting...");
            return;
        }

        // ATM functionalities
        while (true) {
            System.out.println("\nATM Menu:");
            System.out.println("1. Transaction History");
            System.out.println("2. Withdraw");
            System.out.println("3. Deposit");
            System.out.println("4. Transfer");
            System.out.println("5. Quit");
            System.out.print("Choose an option: ");

            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    currentUser.showTransactionHistory();
                    break;
                case 2:
                    System.out.print("Enter amount to withdraw: ");
                    double withdrawAmount = scanner.nextDouble();
                    currentUser.withdraw(withdrawAmount);
                    break;
                case 3:
                    System.out.print("Enter amount to deposit: ");
                    double depositAmount = scanner.nextDouble();
                    currentUser.deposit(depositAmount);
                    break;
                case 4:
                    System.out.print("Enter recipient User ID: ");
                    scanner.nextLine(); // consume newline
                    String recipientId = scanner.nextLine();
                    System.out.print("Enter amount to transfer: ");
                    double transferAmount = scanner.nextDouble();

                    if (recipientId.equals(user1.getUserId())) {
                        currentUser.transfer(user1, transferAmount);
                    } else if (recipientId.equals(user2.getUserId())) {
                        currentUser.transfer(user2, transferAmount);
                    } else {
                        System.out.println("Recipient not found.");
                    }
                    break;
                case 5:
                    System.out.println("Thank you for using the ATM. Goodbye!");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }
}
