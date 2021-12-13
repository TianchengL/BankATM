package User;

import Account.Account;

import java.io.Serializable;
import java.util.ArrayList;

public abstract class User implements Serializable {
    private final String firstname;
    private final String lastname;
    private final String username;
    private int id;
    private String address;


    public enum UserType{ CUSTOMER, MANAGER}

    public abstract UserType getType();

    public User(String firstname, String lastname, String username){
        this.firstname = firstname;
        this.lastname = lastname;
        this.username = username;

    }

    public User(String firstname, String lastname, String address, String username){
        this(firstname, lastname, username);
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

    public String getUsername() {
        return username;
    }
}
