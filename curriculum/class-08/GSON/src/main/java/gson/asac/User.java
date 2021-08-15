package gson.asac;

import gson.Account;

import java.util.List;

// POJO
public class User {
    private String firstName;
    private String lastName;
    private String number;
    private String email;
    private String password;
    private int age;
    private List<Account> accounts;

    public User(String email) {
        this.email = email;
    }

    public User(String firstName, String lastName, String number, String email, String password, int age) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.number = number;
        this.email = email;
        this.password = password;
        this.age = age;
    }

    public void attendClass(String asacClass) {
        System.out.println("I am attending -> " + asacClass);
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getNumber() {
        return number;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public int getAge() {
        return age;
    }

    public void setAccounts(List<Account> accounts) {
        this.accounts = accounts;
    }

    @Override
    public String toString() {
        return String.format("I am %s %s and I am %d years old", firstName, lastName, age);
    }
}
