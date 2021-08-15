package inheritance;

/**
 * Base class for students at LTUC
 */
public class Student {

    private final String name;
    private String number;
    private String address;

    public Student(String name, String number) {
        this.name = name;
        this.number = number;
    }

    public Student(String name, String number, String address) {
        this.name = name;
        this.number = number;
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public String getNumber() {
        return number;
    }

    public String getAddress() {
        return address;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
