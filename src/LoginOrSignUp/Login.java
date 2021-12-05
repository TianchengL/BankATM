package LoginOrSignUp;

import GUI.LoginFrame;

import javax.swing.*;

public class Login {

    private final String username;
    private final String password;

    public Login(String username, String password){
        this.password = password;
        this.username = username;
    }

    /**needs to have a method to check with the database to see if there is a match user**/


    //Every GUI staff needs to put into GUI directory
    public static void login(){
        LoginFrame frame=new LoginFrame();
        frame.setTitle("Login.Login Form");
        frame.setVisible(true);
        frame.setBounds(10,10,600,600);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setResizable(false);

    }

}