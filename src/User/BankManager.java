package User;


public class BankManager extends User {
    public BankManager(String firstname, String lastname, String username) {
        super(firstname, lastname, username);
    }


    @Override
    public UserType getType() {
        return UserType.MANAGER;
    }

}
