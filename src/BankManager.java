public class BankManager extends Users{
    public void insert(){
        // SQL statement for inserting
        String sql = "INSERT INTO loginInfo (username, password) VALUES ('admin', 'admin');";
        ExecuteSqlCommand.executeCommand(sql);
    }
}
