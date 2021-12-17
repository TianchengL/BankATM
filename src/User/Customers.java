package User;

import java.sql.*;

//concrete class for customer
public class Customers extends User {


    public Customers(String firstname, String lastname, String username) {
        super(firstname, lastname, username);
    }

    @Override
    public UserType getType() {
        return UserType.CUSTOMER;
    }

}
