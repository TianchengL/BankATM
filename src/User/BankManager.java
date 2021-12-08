package User;


public class BankManager extends User {
    public BankManager(String firstname, String lastname) {
        super(firstname, lastname);
    }


    @Override
    UserType getType() {
        return UserType.MANAGER;
    }

//    public void insert(){
//        // SQL statement for inserting
//        String sql = "INSERT INTO loginInfo (username, password) VALUES ('admin', 'admin');";
//        ExecuteSqlCommand.executeCommand(sql);
//    }
}
