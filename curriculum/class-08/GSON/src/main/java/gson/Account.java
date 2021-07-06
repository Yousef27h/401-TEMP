package gson;

public class Account {
    private String name;
    private String type;
    private double balance;

    public Account(String name, String type) {
        this.name = name;
        this.type = type;
        balance = 3000;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public double getBalance() {
        return balance;
    }
}
