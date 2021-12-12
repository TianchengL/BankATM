import Database.Database;
import GUI.WelcomeFrame;
import LoginOrSignUp.SignUp;
import User.BankManager;
import User.User;

import java.sql.*;

public class Bank {


    public void run(){
        Database database = new Database();
        Database.addPreviousUsers();
        //default manager
        SignUp signUp = new SignUp("Cristine", "CPK", "admin",
                User.UserType.MANAGER, "admin", "admin");
        if(Database.checkUserExist("admin").isEmpty())
            signUp.addUser();
        WelcomeFrame.welcome();

//        Database.createNewDatabase();
//        createTable.createNewTables();
//        BankManager manager = new BankManager();
//        manager.setUsername("admin");
//        manager.setPassword("admin");
//        if(!this.select(manager.getUsername()))
//            manager.insert();
        //Welcome.welcome();
    }


}
