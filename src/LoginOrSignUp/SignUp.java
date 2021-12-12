package LoginOrSignUp;

import Database.Database;
import User.*;
import Collection.UserCollection;

public class SignUp {

    private final String firstName;
    private final String lastName;
    private final String userName;
    private final String password;
    private final String passwordConfirm;
    private final User.UserType type;

    public SignUp(String firstName, String lastName, String userName, User.UserType type, String password, String confirmPW){
        this.firstName = firstName;
        this.lastName = lastName;
        this.userName = userName;
        this.password = password;
        this.passwordConfirm = confirmPW;
        this.type = type;
    }

    //add a new user to database and set its id
    public void addUser(){
        User user;
        int id;
        if(type == User.UserType.MANAGER){
            user = new BankManager(this.firstName, this.lastName, this.userName);
//            System.out.println(user.getType());
            Database.addUser(user);
            id = Database.getUserId();
            user.setId(id);
            //add credential info into credential table
            Database.addToCredentialTable(user, userName, password);
            UserCollection.getInstance().addUser(user);
        }else if(type == User.UserType.CUSTOMER){
            user = new Customers(this.firstName, this.lastName, this.userName);
            Database.addUser(user);
            id = Database.getUserId();
            user.setId(id);
            Database.addToCredentialTable(user, userName, password);
            UserCollection.getInstance().addUser(user);
        }
    }


   /** Might need to provide some method to check if input password meet some constraints
    * such as at least one Capital letter. ect...**/

    //Added condition to check this during signup
    //check if password and confirm password the same
    //return true if its same
    public boolean checkPassword(){
        return false;
    }



}