import Database.Database;
import User.BankManager;

import java.sql.*;

public class Bank {
    public void run(){
//        Database.createNewDatabase();
//        createTable.createNewTables();
//        BankManager manager = new BankManager();
//        manager.setUsername("admin");
//        manager.setPassword("admin");
//        if(!this.select(manager.getUsername()))
//            manager.insert();
        //Welcome.welcome();
    }

    public boolean select(String username){
        String sql = "SELECT * "
                + "FROM loginInfo WHERE username = '" + username + "'";
//        ResultSet rs = SelectQuery.select(sql);
//        if (rs.next()) {
//            return true;
//        }
        String url = "jdbc:sqlite:bankAtm.db";
        try (Connection conn = DriverManager.getConnection(url);
             Statement stmt  = conn.createStatement();
             ResultSet rs    = stmt.executeQuery(sql)){

            // loop through the result set
            if (rs.next()) {
                return true;
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return false;
    }
}
