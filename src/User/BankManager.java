package User;


public class BankManager extends User {
    public BankManager(int id, String firstname, String lastname) {
        super(id, firstname, lastname);
    }

    public void insert(){
        // SQL statement for inserting
        String sql = "INSERT INTO loginInfo (username, password) VALUES ('admin', 'admin');";
        ExecuteSqlCommand.executeCommand(sql);
    }
}
