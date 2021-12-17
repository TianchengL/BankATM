import Database.Database;
import GUI.WelcomeFrame;
import LoginOrSignUp.SignUp;
import User.BankManager;
import User.User;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.*;
import java.util.Scanner;

//The purpose of this class is to initialize the application
//and check some internal logic
public class Bank {

    //run the program
    public void run(){
        Database database = new Database();
        //Database.addPreviousUsers();
        //default manager
        SignUp signUp = new SignUp("Cristine", "CPK", "admin",
                User.UserType.MANAGER, "admin", "admin");
        if(Database.checkUserExist("admin").isEmpty())
            signUp.addUser();
        File bankProfit = new File("src/Data/bankProfit.txt");
        if(!bankProfit.exists()) {
            try {
                if (bankProfit.createNewFile()) {
                    FileWriter myWriter = new FileWriter("src/Data/bankProfit.txt");
                    myWriter.write("0");
                    myWriter.close();
                    BankManager.setProfit(0.0);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        else{
            Double profit = 0.0;
            try {
                File myObj = new File("src/Data/bankProfit.txt");
                Scanner myReader = new Scanner(myObj);
                while (myReader.hasNextLine()) {
                    profit = Double.parseDouble(myReader.nextLine());
                }
                myReader.close();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            BankManager.setProfit(profit);
        }
        new WelcomeFrame();
    }


}
