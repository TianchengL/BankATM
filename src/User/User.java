package User;

public abstract class User {
    private String firstname, lastname;
    private int id;
    private String address;

    public User(int id, String firstname, String lastname){
        this.id = id;
        this.firstname = firstname;
        this.lastname = lastname;
    }

    public User(int id, String firstname, String lastname, String address){
        this(id, firstname, lastname);
        this.address = address;
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

    public abstract void insert();

}
