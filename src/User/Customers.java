package User;

import java.sql.*;

public class Customers extends Users {
    public void insert(){
        // SQL statement for inserting
        String sql1 = "INSERT INTO loginInfo (username, password) VALUES ('" + this.getUsername() + "', '" + this.getPassword() + "');";
        String sql2 = "INSERT INTO customerInfo (name, username, address) VALUES ('" + this.getName() + "', '" + this.getUsername() + "', '" + this.getAddress() + "');";
        ExecuteSqlCommand.executeCommand(sql1);
        ExecuteSqlCommand.executeCommand(sql2);
    }

    public static Customers retrieveCustomer(String username){
        Customers customer = new Customers();
        String sql = "SELECT customerInfo.name, loginInfo.username, customerInfo.address, loginInfo.password "
                + "FROM loginInfo INNER JOIN customerInfo ON loginInfo.username = customerInfo.username "
        + "WHERE loginInfo.username = '" + username + "'";
        String url = "jdbc:sqlite:bankAtm.db";
        try (Connection conn = DriverManager.getConnection(url);
             Statement stmt  = conn.createStatement();
             ResultSet rs    = stmt.executeQuery(sql)){

            // result set
            if (rs.next()) {
                customer.setName(rs.getString("name"));
                customer.setAddress(rs.getString("address"));
                customer.setUsername(rs.getString("username"));
                customer.setPassword(rs.getString("password"));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return customer;
    }

    public void updatePassword(String newPwd){
        this.setPassword(newPwd);
        String sql = "UPDATE loginInfo "
                + "SET password = '" + this.getPassword() + "'"
                + " WHERE username = '" + this.getUsername() + "';";
        ExecuteSqlCommand.executeCommand(sql);
    }
}
