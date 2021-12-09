package User;

public abstract class User {
    private final String firstname;
    private final String lastname;
    private int id;
    private String address;

    public enum UserType{ CUSTOMER, MANAGER}

    public abstract UserType getType();

    public User(String firstname, String lastname){
        this.firstname = firstname;
        this.lastname = lastname;
    }

    public User(String firstname, String lastname, String address){
        this(firstname, lastname);
        this.address = address;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }


}
