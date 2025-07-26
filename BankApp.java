// Abstraction: Defining common methods for all accounts
abstract class BankAccount {
    protected int accountNumber;
    protected double balance;

    public BankAccount(int accountNumber, double balance) {
        this.accountNumber = accountNumber;
        this.balance = balance;
    }

    public abstract void withdraw(double amount);  // Abstract method

    public void deposit(double amount) {
        balance += amount;
        System.out.println("Deposited: $" + amount);
    }

    public void showBalance() {
        System.out.println("Balance: $" + balance);
    }
}

// Inheritance: SavingsAccount and CurrentAccount inherit BankAccount
class SavingsAccount extends BankAccount {
    public SavingsAccount(int accountNumber, double balance) {
        super(accountNumber, balance);
    }

    @Override
    public void withdraw(double amount) {
        if (amount <= balance) {
            balance -= amount;
            System.out.println("Savings withdrawal: $" + amount);
        } else {
            System.out.println("Insufficient funds in Savings Account");
        }
    }
}

class CurrentAccount extends BankAccount {
    public CurrentAccount(int accountNumber, double balance) {
        super(accountNumber, balance);
    }

    @Override
    public void withdraw(double amount) {
        balance -= amount;  // Allows overdraft
        System.out.println("Current withdrawal (may overdraft): $" + amount);
    }
}

// Encapsulation: Customer data hidden and accessed via getters/setters
class Customer {
    private String name;
    private String email;

    public Customer(String name, String email) {
        this.name = name;
        this.email = email;
    }

    public String getName() { return name; }
    public String getEmail() { return email; }
}

// Polymorphism in action
public class BankApp { 
    public static void main(String[] args) {
        Customer customer = new Customer("John Doe", "john@example.com");

        BankAccount account1 = new SavingsAccount(101, 5000);
        BankAccount account2 = new CurrentAccount(102, 3000);

        System.out.println("Customer: " + customer.getName());

        // Polymorphism: same method calls, different behavior
        account1.deposit(1000);
        account1.withdraw(2000);
        account1.showBalance();

        account2.deposit(500);
        account2.withdraw(4000);  // overdraft allowed in current account
        account2.showBalance();
    }
}
